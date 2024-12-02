package no.rodland.advent_2024

import no.rodland.advent.Day

// template generated: 02/12/2024
// Fredrik Rødland 2024

class Day02(val input: List<String>) : Day<Long, Long, List<List<Int>>> {

    private val parsed = input.parse()

    override fun partOne(): Long {


        return parsed.count { it.isSafe() }.toLong()
    }

    override fun partTwo(): Long {
        val expanded = parsed.expand()
        return expanded.count { candidates ->
            candidates.any { list -> list.isSafe() }
        }.toLong()
    }

    override fun List<String>.parse(): List<List<Int>> {
        return map { line ->
            line.split(" ").map { it.toInt() }
        }
    }

    private fun List<Int>.isSafe(): Boolean {
        val inc = this[1] > this[0]
        val diff = windowed(2).map {
            if (inc) {
                it.last() - it.first()
            } else {
                it.first() - it.last()
            }
        }
        val all = diff.all { it in (1..3) }
        return all
    }

    private fun List<List<Int>>.expand(): List<List<List<Int>>> {
        return map { org ->
            List(org.size) { index -> org.withoutItemAt(index) }
        }
    }

    private fun List<Int>.withoutItemAt(index: Int) = filterIndexed { i, _ -> i != index }

    override val day = "02".toInt()
}



