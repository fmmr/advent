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

    fun partTwo(list: List<String>): Int {
        return 2
    }

}
