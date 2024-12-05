package no.rodland.advent_2024

import no.rodland.advent.Day

// template generated: 05/12/2024
// Fredrik Rødland 2024

class Day05(val input: List<String>) : Day<Int, Int, Pair<Map<Int, Set<Int>>, List<List<Int>>>> {

    private val parsed = input.parse()
    private val rules = parsed.first
    private val pages = parsed.second

    private val comparator = Comparator<Int> { i1, i2 ->
        when {
            i1 in rules && rules[i1]!!.contains(i2) -> -1
            i2 in rules && rules[i2]!!.contains(i1) -> 1
            else -> 0
        }
    }

    override fun partOne(): Int {
        return pages
            .filter { it.valid() }
            .sumOf { it[it.size / 2] }
    }

    override fun partTwo(): Int {
        return pages
            .filterNot { it.valid() }
            .map { it.sort() }
            .sumOf { it[it.size / 2] }
    }

    private fun List<Int>.valid(): Boolean = sort() == this
    private fun List<Int>.sort(): List<Int> = sortedWith(comparator)


    override fun List<String>.parse(): Pair<Map<Int, Set<Int>>, List<List<Int>>> {
        val (rulesStr, pagesStr) = this.joinToString("\n").split("\n\n").map { it.split("\n") }

        val pages = pagesStr.map { line ->
            line.split(",").map { it.toInt() }
        }
        val rules = rulesStr
            .map { line ->
                line.split("|").map { it.toInt() }.let { it[0] to it[1] }
            }
            .groupBy({ it.first }, { it.second })
            .mapValues { it.value.toSet() }
        return rules to pages
    }

    override val day = "05".toInt()
}




