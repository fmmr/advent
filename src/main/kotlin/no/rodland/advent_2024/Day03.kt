package no.rodland.advent_2024

import no.rodland.advent.Day

// template generated: 03/12/2024
// Fredrik Rødland 2024

class Day03(val input: List<String>) : Day<Long, Long, List<Pair<Int, Int>>> {
    private val multRegEx = "mul\\((\\d{1,3}),(\\d{1,3})\\)".toRegex()

    private val parsed = input.parse()

    override fun partOne(): Long {
        return parsed.sumOf { (a, b) -> a * b }.toLong()
    }

    override fun partTwo(): Long {
        return 2
    }

    override fun List<String>.parse(): List<Pair<Int, Int>> {
        return flatMap { line ->
            multRegEx.findAll(line).toList().map { mr ->
                mr.groupValues[1].toInt() to mr.groupValues[2].toInt()
            }
        }
    }

    override val day = "03".toInt()
}
