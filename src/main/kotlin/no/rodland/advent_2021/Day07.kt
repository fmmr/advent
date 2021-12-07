package no.rodland.advent_2021

import kotlin.math.abs

@Suppress("UNUSED_PARAMETER")
object Day07 {
    fun partOne(list: List<Int>): Int {
        val max = list.maxOrNull()!!
        val min = list.minOrNull()!!
        return (min..max).minOfOrNull { pos ->
            list.sumOf { crab -> abs(pos - crab) }
        }!!
    }

    fun partTwo(list: List<Int>): Int {
        return 2
    }
}
