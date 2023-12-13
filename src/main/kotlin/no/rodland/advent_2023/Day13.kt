package no.rodland.advent_2023

import no.rodland.advent.Day
import java.lang.Integer.min

// template generated: 13/12/2023
// Fredrik Rødland 2023

class Day13(val input: List<String>) : Day<Int, Int, List<Day13.GroundMap>> {

    private val parsed = input.parse()

    override fun partOne(): Int {
        return run(::partOneCompare)
    }

    override fun partTwo(): Int {
        return run(::partTwoCompare)
    }

    private fun run(compare: (List<String>, List<String>) -> Boolean) = parsed.sumOf { groundMap ->
        val rowsHorizontal = isMirror(groundMap, compare)
        val rowsVertical = if (rowsHorizontal == 0) {
            isMirror(groundMap.transpose(), compare)
        } else {
            0
        }
        rowsVertical + 100 * rowsHorizontal
    }

    private fun isMirror(groundMap: GroundMap, compare: (List<String>, List<String>) -> Boolean): Int {
        (1..<groundMap.size).forEach { i ->
            val map = groundMap.grounds

            val rev = map.subList(0, i).asReversed()
            val forw = map.subList(i, map.size)

            // println("rev:   $rev    for:    $forw")
            val b = compare(rev, forw)
            if (b) return i
        }
        return 0
    }

    private fun partOneCompare(reverseList: List<String>, forwardList: List<String>): Boolean {
        val size = min(reverseList.size, forwardList.size)
        return reverseList.subList(0, size) == forwardList.subList(0, size)
    }

    // https://github.com/ephemient/aoc2023/blob/main/kt/aoc2023-lib/src/commonMain/kotlin/com/github/ephemient/aoc2023/Day13.kt
    private fun partTwoCompare(reverseList: List<String>, forwardList: List<String>): Boolean {
        val size = min(reverseList.size, forwardList.size)
        var almostEqual = false

        // hm - this didn't work for some reason - so...
        // var diffCount = 0
        // for (i in 0..<size) {
        //     if (reverseList[i] != forwardList[i]) {
        //         diffCount++
        //     }
        // }
        // ..."borrowing" the followign code from ephemient
        for (i in 0..<size) {
            val a = reverseList[i]
            val b = forwardList[i]
            val delta = a.indices.count { a[it] != b[it] }
            if (delta > 1) return false
            if (delta == 1) if (almostEqual) return false else almostEqual = true
        }
        return almostEqual
    }

    override fun List<String>.parse(): List<GroundMap> {
        return joinToString("\n").split("\n\n").map { it.split("\n") }.map { l -> GroundMap(l) }
    }

    data class GroundMap(val grounds: List<String>) {
        fun transpose() = GroundMap(grounds.transpose())
        val size = grounds.size
        val reversed = grounds.reversed()

        fun print() {
            grounds.map { line ->
                println(line)
            }

        }
    }

    override val day = "13".toInt()
}

private fun List<String>.transpose(): List<String> {
    val width = first().length
    val height = size
    return (0..<width).map { j ->
        (0..<height).map { i ->
            get(i)[j]
        }.joinToString("")
    }
}


