package no.rodland.advent_2019

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import no.rodland.advent.Pos
import kotlin.math.sign

object Day13 {
    fun partOne(list: List<String>): Int {
        return runProgram(list).first.filter { it.value == Tile.BLOCK }.size
    }

    fun partTwo(list: List<String>, numQuarters: String): Int {
        val program = list.toMutableList()
        program[0] = numQuarters
        return runProgram(program).second.toInt()
    }

    private fun runProgram(program: List<String>): Pair<MutableMap<Pos, Tile>, Long> {
        val input = Channel<Long>(20)
        val output = Channel<Long>(20)
        val job = IntCodeComputer(program, input, output).run()
        val tiles: MutableMap<Pos, Tile> = mutableMapOf()

        var score = 0L
        var xCoordBall: Long
        var xCoordPaddle = 0L

        val game = GlobalScope.launch {
            //            repeat(8) { input.send(0L) }
            while (true) {
                try {
                    val x = output.receive()
                    val y = output.receive()
                    val thirdValue = output.receive()
                    if (x == -1L && y == 0L) {
                        score = thirdValue
                        println("new score: $score")
                    } else {
                        val tile = tile(thirdValue)
                        tiles[Pos(x.toInt(), y.toInt())] = tile
                        if (tile == Tile.BALL) {
                            xCoordBall = x
                            val joy = (xCoordBall - xCoordPaddle).sign.toLong()
                            input.send(joy)
                            // println("sent joy: $joy - xball: $xCoordBall, xpaddle: $xCoordPaddle")
                        }
                        if (tile == Tile.PADDLE) {
                            xCoordPaddle = x
                        }
                    }
                } catch (e: Exception) {
                    println("Channel closed - computer stopped")
                    break
                }
            }
        }
        runBlocking {
            job.join()
            game.join()
        }
        return tiles to score
    }

    fun tile(value: Long): Tile = when (value) {
        0L -> Tile.EMPTY
        1L -> Tile.WALL
        2L -> Tile.BLOCK
        3L -> Tile.PADDLE
        4L -> Tile.BALL
        else -> error("unable to draw tile: $value")
    }

    enum class Tile(val id: Int) {
        EMPTY(0), WALL(1), BLOCK(2), PADDLE(3), BALL(4);
    }
}