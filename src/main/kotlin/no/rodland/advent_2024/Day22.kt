package no.rodland.advent_2024

import no.rodland.advent.Day

// template generated: 22/12/2024
// Fredrik Rødland 2024

class Day22(val input: List<String>) : Day<Long, Long, List<Int>> {

    private val parsed = input.parse()

    override fun partOne(): Long {
        return 2
    }

    override fun partTwo(): Long {
        return 2
    }

    override fun List<String>.parse(): List<Int> = map { it.toInt() }

    override val day = "22".toInt()
}
