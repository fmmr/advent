package no.rodland.advent_2023

import no.rodland.advent.Day

// template generated: 09/12/2023
// Fredrik Rødland 2023

class Day09(val input: List<String>) : Day<Int, Int, List<List<Int>>> {

    private val parsed = input.parse()

    override fun partOne(): Int {
        return parsed.sumOf { it.next() }
    }

    override fun partTwo(): Int {
        return parsed.sumOf { it.previous() }
    }

    private fun List<Int>.next(): Int = if (all { it == 0 }) 0
    else last() + diffs().next()

    private fun List<Int>.previous(): Int = if (all { it == 0 }) 0
    else first() - diffs().previous()

    private fun List<Int>.diffs(): List<Int> = zipWithNext { a, b -> b - a }

    override fun List<String>.parse(): List<List<Int>> {
        return map { line ->
            line.split(" ").map { it.toInt() }
        }
    }

    override val day = "09".toInt()
}

