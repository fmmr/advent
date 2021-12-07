package no.rodland.advent_2021

import kotlin.math.abs
import kotlin.math.absoluteValue
import kotlin.math.ceil
import kotlin.math.floor

object Day07 {
    fun partOne(list: List<Int>): Int {
        // loop through list: return solve(list, ::costPart1)
        val pos = list.sorted()[list.size / 2]
        return list.sumOf { crab -> costPart1(crab, pos) }
    }


    fun partTwo(list: List<Int>): Int {
        // loop through list: return solve(list, ::costPart2)
        val potentialPositions = list.average().let { setOf(floor(it).toInt(), ceil(it).toInt()) }
        return potentialPositions.minOfOrNull { list.sumOf { crab -> costPart2(crab, it) } }!!
    }

    @Suppress("unused")
    private fun solve(list: List<Int>, costCalc: (Int, Int) -> Int): Int {
        val max = list.maxOrNull()!!
        val min = list.minOrNull()!!
        return (min..max).minOfOrNull { pos ->
            list.sumOf { crab -> costCalc(crab, pos) }
        }!!
    }

    private fun costPart1(crab: Int, pos: Int) = abs(pos - crab)

    fun costPart2(crab: Int, pos: Int) = (pos - crab).absoluteValue.let { it * (it + 1) / 2 }

}
