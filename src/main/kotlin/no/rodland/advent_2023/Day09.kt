package no.rodland.advent_2023

import no.rodland.advent.Day

// template generated: 09/12/2023
// Fredrik Rødland 2023


// can probably be calculated directly (think something along the lines of Pascal's Triangle)
// instead of looping through everything (twice for part 2)
class Day09(val input: List<String>) : Day<Int, Int, List<List<Int>>> {

    private val parsed = input.parse()

    override fun partOne(): Int {
        return parsed.sumOf { list -> allDiffs(list).sumOf { it.last() } }
    }

    override fun partTwo(): Int {
        return parsed.sumOf { list ->
            val append2 = mutableListOf<List<Int>>()
            allDiffs(list).reversed().map { next ->
                append2.add(diffFirst(append2.lastOrNull(), next))
            }
            append2.last().first()
        }
    }

    private fun allDiffs(list: List<Int>): List<List<Int>> {
        val append = mutableListOf(list)
        while (append.last().any { it != 0 }) {
            append.add(append.last().diffs())
        }
        return append
    }

    private fun diffFirst(last: List<Int>?, next: List<Int>): List<Int> {
        return listOf(next.first() - (last?.first() ?: 0)) + next
    }

    private fun List<Int>.diffs(): List<Int> = zipWithNext { a, b -> b - a }

    override fun List<String>.parse(): List<List<Int>> {
        return map { line ->
            line.split(" ").map { it.toInt() }
        }
    }

    override val day = "09".toInt()
}

