package no.rodland.advent_2024

import no.rodland.advent.Day

// template generated: 09/12/2024
// Fredrik Rødland 2024

class Day09(val input: List<String>) : Day<Long, Long, Pair<List<Int>, List<Int>>> {

    private val parsed = input.parse()
    private val files = parsed.first
    private val space = parsed.second

    override fun partOne(): Long {
        parsed
        return 2
    }

    override fun partTwo(): Long {
        return 2
    }

    override fun List<String>.parse(): Pair<List<Int>, List<Int>> {
        return first()
            .map { c -> c.digitToInt() }
            .chunked(2) { it.first() to it.last() }
            .unzip()
    }

    override val day = "09".toInt()
}
