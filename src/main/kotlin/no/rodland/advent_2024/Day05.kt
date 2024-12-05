package no.rodland.advent_2024

import no.rodland.advent.Day

// template generated: 05/12/2024
// Fredrik Rødland 2024

class Day05(val input: List<String>) : Day<Long, Long, Pair<List<Pair<Int, Int>>, List<List<Int>>>> {

    private val parsed = input.parse()
    private val rules = parsed.first
    private val pages = parsed.second

    override fun partOne(): Long {
        return 2
    }

    override fun partTwo(): Long {
        return 2
    }

    override fun List<String>.parse(): Pair<List<Pair<Int, Int>>, List<List<Int>>> {
        val (rulesStr, pagesStr) = this.joinToString("\n").split("\n\n").map { it.split("\n") }

        val pages = pagesStr.map { line ->
            line.split(",").map { it.toInt() }
        }
        val rules = rulesStr.map { line ->
            val (first, second) = line.split("|").map { it.toInt() }
            first to second
        }
        return rules to pages
    }

    override val day = "05".toInt()
}
