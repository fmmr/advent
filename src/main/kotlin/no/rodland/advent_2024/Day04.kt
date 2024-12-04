package no.rodland.advent_2024

import no.rodland.advent.Day
import no.rodland.advent.Dir
import no.rodland.advent.Pos

// template generated: 04/12/2024
// Fredrik Rødland 2024
typealias Grid = Array<CharArray>

class Day04(val input: List<String>) : Day<Int, Int, Grid> {

    private val parsed = input.parse()

    override fun partOne(): Int {
        return parsed.mapIndexed { y, row ->
            row.mapIndexed { x, _ ->
                val start = Pos(x, y)
                val words = Dir.entries.map { d ->
                    (0..3)
                        .map { num -> d.move(start, num) }
                        .filter { p -> p.isInGrid(parsed) }
                        .map { p -> parsed[p.y][p.x] }
                        .joinToString("")
                }.count { it == "XMAS" }
                words
            }.sum()
        }.sum()
    }

    override fun partTwo(): Int {
        val intRange = 1..(parsed.size - 2) // assuming grid is square (which both example and input is), avoiding both a filter and many comparisons.
        return intRange.sumOf { y ->
            intRange.map { x ->
                val start = Pos(x, y)
                listOf(listOf(start.nw(), start, start.se()), listOf(start.sw(), start, start.ne()))
                    .all { diagonal ->
                        diagonal.map { p -> parsed[p.y][p.x] }.joinToString("").let { it == "MAS" || it == "SAM" }
                    }
            }.count { it }
        }
    }

    override fun List<String>.parse(): Grid {
        return indices.map { y -> indices.map { x -> this[y][x] }.toCharArray() }.toTypedArray()
    }

    override val day = "04".toInt()
}



