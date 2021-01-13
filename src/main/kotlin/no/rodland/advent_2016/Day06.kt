package no.rodland.advent_2016

import increment

@Suppress("UNUSED_PARAMETER")
object Day06 {
    fun partOne(list: List<String>): String {
        return getString(list) { value -> value == values.maxOrNull()!! }
    }

    fun partTwo(list: List<String>): String {
        return getString(list) { value -> value == values.minOrNull()!! }
    }

    private fun getString(list: List<String>, letterPick: Map<Char, Int>.(Int) -> Boolean): String {
        val counters = (list[0].indices).map { mutableMapOf<Char, Int>() }
        list.forEach { str ->
            str.forEachIndexed { index, c ->
                counters[index].increment(c)
            }
        }
        return counters.map { map -> map.filterValues { map.letterPick(it) }.keys.first() }.joinToString("")
    }
}
