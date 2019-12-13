package no.rodland.advent_2019

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

object Day13 {
    fun partOne(list: List<String>): Int {
        return runProgram(list).filter { it.tile == Tile.BLOCK }.size
    }

    fun partTwo(list: List<String>): Int {
        return 2
    }

    private fun runProgram(program: List<String>): MutableList<Event> {

        val input = Channel<Long>(20)
        val output = Channel<Long>(20)
        val job = IntCodeComputer(program, input, output).run()

        val tiles: MutableList<Event> = mutableListOf()
        GlobalScope.launch {
            while (job.isActive) {
                try {
                    val x = output.receive()
                    val y = output.receive()
                    val tile = tile(output.receive())
                    tiles.add(Event(x, y, tile))
                } catch (e: Exception) {
                    println("Channel closed - computer stopped")
                }
            }
        }
        runBlocking {
            job.join()
        }
        return tiles
    }

    data class Event(val x: Long, val y: Long, val tile: Tile)

    fun tile(value: Long): Tile = when (value) {
        0L -> Tile.EMPTY
        1L -> Tile.WALL
        2L -> Tile.BLOCK
        3L -> Tile.PADDLE
        4L -> Tile.BALL
        else -> error("unable to draw tile: $value")
    }

    enum class Tile(val id: Int) {
        EMPTY(0), WALL(1), BLOCK(2), PADDLE(3), BALL(4)

    }

}