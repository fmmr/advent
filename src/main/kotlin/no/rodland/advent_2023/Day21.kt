package no.rodland.advent_2023

import no.rodland.advent.Day

// template generated: 21/12/2023
// Fredrik Rødland 2023

class Day21(val input: List<String>) : Day<Long, Long, List<String>> {

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

    override val day = "21".toInt()
}
