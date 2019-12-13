package no.rodland.advent_2019

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import no.rodland.advent.Pos

object Day13 {
    var score = 0L
    fun partOne(list: List<String>): Int {
        return runProgram(list).filter { it.tile == Tile.BLOCK }.size
    }

    fun partTwo(list: List<String>, numQuarters: String): Int {
        val program = list.toMutableList()
        program[0] = numQuarters
        return runProgram(list).filter { it.tile == Tile.BLOCK }.size
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
                    val thirdValue = output.receive()
                    if (x == -1L && y == 0L) {
                        score = thirdValue
                    } else {
                        tiles.add(Event(x, y, tile(thirdValue)))
                    }
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

    fun tile(value: Long): Tile = when (value) {
        0L -> Tile.EMPTY
        1L -> Tile.WALL
        2L -> Tile.BLOCK
        3L -> Tile.PADDLE
        4L -> Tile.BALL
        else -> error("unable to draw tile: $value")
    }

    data class Event(val pos: Pos, val tile: Tile) {
        constructor(x: Long, y: Long, tile: Tile) : this(Pos(x.toInt(), y.toInt()), tile)
    }

    enum class Tile(val id: Int) {
        EMPTY(0), WALL(1), BLOCK(2), PADDLE(3), BALL(4);
    }

    enum class Joystick(val id: Int) {
        NEUTRAL(0), LEFT(-1), RIGHT(1)
    }
}