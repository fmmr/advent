package no.rodland.advent_2019

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.runBlocking

object Day19 {
    fun partOne(program: List<String>): Long {
//        printMap(program, 100, 100)
        var minX = 0L
        return (0..49).flatMap { y ->
            var foundAffected = false
            var skipCheck = false
            val numAffected = (minX..49).map { x ->
                val affected = if (!skipCheck) getPos(program, x, y.toLong()) else 0L
                if (affected == 1L) {
                    if (!foundAffected) {
                        minX = x + 1
                    }
                    foundAffected = true
                } else {
                    if (foundAffected) {
                        skipCheck = true
                    }
                }
                affected
            }
            numAffected
        }.sum()
    }

    fun printMap(program: List<String>, minX: Int, maxX: Int, minY: Int, maxY: Int) {
        return (minY..maxY).forEach { y ->
            (minX..maxX).forEach { x ->
                print(getPos(program, x.toLong(), y.toLong()))
            }
            println()
        }
    }

    fun partTwo(program: List<String>): Long {
        var found = false
        // picked 2 reasonably high start values
        var y = 800L
        var x = 800L
        while (!found) {
            y++
            while (getPos(program, x, y) != 1L) {
                x++
            }
            found = getPos(program, x + 99, y - 99) == 1L
        }
        return x * 10000 + (y - 99)
    }

    private fun getPos(liprogramt: List<String>, x: Long, y: Long): Long {
        val input = Channel<Long>(20)
        val output = Channel<Long>(20)
        val program = liprogramt.toMutableList()

        IntCodeComputer().launch(program, input, output)
        return runBlocking {
            input.send(x)
            input.send(y)
            output.receive()
        }
    }

}