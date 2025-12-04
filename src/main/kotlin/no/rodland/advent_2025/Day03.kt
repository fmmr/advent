package no.rodland.advent_2025

import no.rodland.advent.Day

// https://todd.ginsberg.com/post/advent-of-code/2025/day3/
class Day03(val input: List<String>) : Day<Long, Long, List<List<IndexedValue<Int>>>> {

    private val parsed = input.parse()

    override fun partOne(): Long {
        return parsed.sumOf { joltage(it, 2) }
    }

    override fun partTwo(): Long {
        return parsed.sumOf { joltage(it, 12) }
    }

    private fun joltage(bank: List<IndexedValue<Int>>, batteries: Int): Long =
        (1..batteries).fold(0L to 0) { (total, leftIndex), offset ->
            bank.subList(leftIndex, bank.size - batteries + offset)
                .maxBy { it.value }
                .let { (total * 10) + it.value to it.index + 1 }
        }.first


    override fun List<String>.parse(): List<List<IndexedValue<Int>>> = map {
        it.map { d -> d.digitToInt() }.withIndex().toList()
    }


    override val day = "03".toInt()
}

