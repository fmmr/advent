package no.rodland.advent_2023

import no.rodland.advent.Day

// template generated: 09/12/2023
// Fredrik Rødland 2023

class Day09(val input: List<String>) : Day<Int, Long, List<List<Int>>> {

    private val parsed = input.parse()

    override fun partOne(): Int {
        return parsed.sumOf { diffList(it) }
    }

    override fun partTwo(): Long {
        return 2
    }

    private fun diffList(list: List<Int>): Int {
        val append = mutableListOf(list)
        while (append.last().any { it != 0 }) {
            append.add(append.last().diffs())
        }
        return append.sumOf { it.last() }
    }

    private fun List<Int>.diffs(): List<Int> = windowed(2) { it.last() - it.first() }

    override fun List<String>.parse(): List<List<Int>> {
        return map { line ->
            line.split(" ").map { it.toInt() }
        }
    }

    override val day = "09".toInt()
}

