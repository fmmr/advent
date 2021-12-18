package no.rodland.advent_2021

import kotlin.math.round

@Suppress("UNUSED_PARAMETER")
object Day18 {
    fun partOne(list: List<String>): Int {
        return 2
    }

    fun partTwo(list: List<String>): Int {
        return 2
    }

    fun Int.split(): Pair<Int, Int> = floorDiv(2) to round(toDouble() / 2.0).toInt()
    fun Pair<Int, Int>.magnitude(): Int = first * 3 + second * 2
}
