package no.rodland.advent_2020

import takeWhileInclusive

object Day13 {
    fun partOne(list: List<String>): Int {
        val earliestDeparture = list[0].toInt()
        val busses = list[1].split(",").filterNot { it == "x" }.map { it.toInt() }
        val int: Int? = null
        val minuteBus = generateSequence(earliestDeparture to int) { (i, _) ->
            (i + 1).let { nextI ->
                val bus = busses.firstOrNull { nextI % it == 0 }
                nextI to bus
            }
        }
            .takeWhileInclusive { it.second == null }
            .last()
        return (minuteBus.second!! * (minuteBus.first - earliestDeparture))
    }

    // thanks to https://todd.ginsberg.com/post/advent-of-code/2020/day13/
    fun partTwo(list: List<String>): Long {
        val bussDiffs = list[1]
            .split(",")
            .mapIndexed { index: Int, bus: String -> index to bus }
            .filterNot { it.second == "x" }
            .map { it.second.toInt() to it.first }
            .map { it.first.toLong() to it.second.toLong() }
        var currentJump = 1L
        var num = 0L
        var iterations = 0
        bussDiffs.forEach { (bus, diff) ->
            while ((num + diff) % bus != 0L) {
                iterations++
                num += currentJump
            }
            currentJump *= bus
        }
        println("Found it iterating $iterations times")
        return num
    }


    private fun isValid(num: Long, bus: Long, diff: Long) = num % bus == if (diff == 0L) 0 else (bus - diff)
}
