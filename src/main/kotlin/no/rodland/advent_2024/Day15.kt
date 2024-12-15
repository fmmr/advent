package no.rodland.advent_2024

import no.rodland.advent.Cave
import no.rodland.advent.Day
import no.rodland.advent.Direction
import no.rodland.advent.Direction.Companion.fromChar

// template generated: 15/12/2024
// Fredrik Rødland 2024

class Day15(val input: List<String>) : Day<Long, Long, Pair<Array<CharArray>, List<Direction>>> {

    private val parsed = input.parse()
    private val grid = parsed.first
    private val movements = parsed.second

    override fun partOne(): Long {
        return 2
    }

    override fun partTwo(): Long {
        return 2
    }

    override fun List<String>.parse(): Pair<Array<CharArray>, List<Direction>> {
        val (map, move) = joinToString("\n").split("\n\n")
        val lines = map.lines()
        val cave = lines.indices.map { y -> lines.indices.map { x -> lines[y][x] }.toCharArray() }.toTypedArray<CharArray>()
        val directions = move.split("\n").flatMap { s -> s.map { Direction.fromChar(it) } }
        return cave to directions
    }

    override val day = "15".toInt()
}
