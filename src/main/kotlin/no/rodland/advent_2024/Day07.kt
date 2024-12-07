package no.rodland.advent_2024

import no.rodland.advent.Day

// template generated: 07/12/2024
// Fredrik Rødland 2024

class Day07(val input: List<String>) : Day<Long, Long, List<Pair<Long, List<Long>>>> {

    private val parsed = input.parse()

    override fun partOne(): Long {
        return 2
    }

    override fun partTwo(): Long {
        return 2
    }

    // 3267: 81 40 27
    override fun List<String>.parse(): List<Pair<Long, List<Long>>> {
        return map { line ->
            line.substringBefore(":").toLong() to line.substringAfter(": ").split(" ").map { it.toLong() }
        }
    }

    override val day = "07".toInt()
}
