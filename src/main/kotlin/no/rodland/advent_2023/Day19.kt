package no.rodland.advent_2023

import no.rodland.advent.Day

// template generated: 19/12/2023
// Fredrik Rødland 2023

class Day19(val input: List<String>) : Day<Long, Long, List<String>> {

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

    override val day = "19".toInt()
}
