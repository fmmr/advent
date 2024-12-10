package no.rodland.advent_2024

import no.rodland.advent.Day

// template generated: 10/12/2024
// Fredrik Rødland 2024

class Day10(val input: List<String>) : Day<Long, Long, List<String>> {

    private val parsed = input.parse()

    override fun partOne(): Long {
        return 2
    }

    override fun partTwo(): Long {
        return 2
    }

    override fun List<String>.parse(): List<String> {
        return map { line ->
            line
        }
    }

    override val day = "10".toInt()
}
