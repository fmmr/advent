package no.rodland.advent_2024

import no.rodland.advent.Day

// template generated: 05/12/2024
// Fredrik Rødland 2024

class Day05(val input: List<String>) : Day<Int, Long, Pair<List<Pair<Int, Int>>, List<List<Int>>>> {

    private val parsed = input.parse()
    private val rules = parsed.first
    private val pages = parsed.second

    override fun partOne(): Int {
       return  pages
           .filter { list ->
           val pairs = list.pairs()
           pairs.all { it in rules }
       }
           .sumOf { it.middle() }
    }

    override fun partTwo(): Long {
        return 2
    }

    private fun List<Int>.middle(): Int = get(size / 2)

    private fun List<Int>.pairs(): List<Pair<Int, Int>> {
        return flatMapIndexed { index: Int, i: Int ->
            subList(index + 1, size).map { i to it }
        }
    }


    override fun List<String>.parse(): Pair<List<Pair<Int, Int>>, List<List<Int>>> {
        val (rulesStr, pagesStr) = this.joinToString("\n").split("\n\n").map { it.split("\n") }

        val pages = pagesStr.map { line ->
            line.split(",").map { it.toInt() }
        }
        val rules = rulesStr.map { line ->
            line.split("|").map { it.toInt() }.let { it[0] to it[1] }
        }
        return rules to pages
    }

    override val day = "05".toInt()
}


