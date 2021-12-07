package no.rodland.advent_2021

import kotlin.math.abs
import kotlin.math.absoluteValue

@Suppress("UNUSED_PARAMETER")
object Day07 {
    fun partOne(list: List<Int>): Int {
        return solve(list, ::costPart1)
    }


    fun partTwo(list: List<Int>): Int {
        return solve(list, ::costPart2)
    }

    private fun solve(list: List<Int>, costCalc: (Int, Int) -> Int): Int {
        val max = list.maxOrNull()!!
        val min = list.minOrNull()!!
        return (min..max).minOfOrNull { pos ->
            list.sumOf { crab -> costCalc(crab, pos) }
        }!!
    }

    private fun costPart1(crab: Int, pos: Int) = abs(pos - crab)

    fun costPart2(crab: Int, pos: Int) = triangular(pos - crab)

    private fun triangular(diff: Int) = diff.absoluteValue.let { it * (it + 1) / 2 }

}
