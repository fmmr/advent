package no.rodland.advent_2025

import no.rodland.advent.Day

// template generated: 06/12/2025
// Fredrik Rødland 2025

class Day05(val input: List<String>) : Day<Long, Long, List<String>> {

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

    override val day = "05".toInt()
}
