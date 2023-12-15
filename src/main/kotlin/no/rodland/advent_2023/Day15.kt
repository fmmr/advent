package no.rodland.advent_2023

import no.rodland.advent.Day

// template generated: 15/12/2023
// Fredrik Rødland 2023

class Day15(val input: List<String>) : Day<Int, Long, List<CharArray>> {

    private val parsed = input.parse()

    override fun partOne(): Int {
        return parsed.sumOf { it.hash() }
    }

    private fun CharArray.hash() = fold(0) { acc, c -> ((c.code + acc) * 17).mod(256) }

    override fun partTwo(): Long {
        return 2
    }

    override fun List<String>.parse(): List<CharArray> {
        return first().split(",").map { it.toCharArray() }
    }

    override val day = "15".toInt()
}

