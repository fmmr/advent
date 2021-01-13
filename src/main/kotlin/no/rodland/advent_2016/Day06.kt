package no.rodland.advent_2016

import increment

@Suppress("UNUSED_PARAMETER")
object Day06 {
    fun partOne(list: List<String>): String {
        val counters = (list[0].indices).map { mutableMapOf<Char, Int>() }
        list.forEach { str ->
            str.forEachIndexed { index, c ->
                counters[index].increment(c)
            }
        }
        return counters.map { map -> map.filterValues { value -> value == map.values.maxOrNull()!! }.keys.first() }.joinToString("")
    }

    fun partTwo(list: List<String>): String {
        return "2"
    }
}
