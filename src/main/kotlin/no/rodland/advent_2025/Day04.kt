package no.rodland.advent_2025

import no.rodland.advent.Day
import no.rodland.advent.Pos

// template generated: 04/12/2025
// Fredrik Rødland 2025
typealias Grid = Array<CharArray>

class Day04(val input: List<String>) : Day<Long, Long, Grid> {

    private val parsed = input.parse()

    override fun partOne(): Long {
        return parsed.indices.flatMap { y ->
            parsed[y].indices.map { x ->
                val pos = Pos(x, y)
                if (parsed[pos] == '@') {
                    pos.neighbourCellsAllEight().filter { it in parsed }.count { parsed[it] == '@' } < 4
                } else false
            }
        }.count { it }.toLong()

    }

    override fun partTwo(): Long {
        return 2
    }

    override fun List<String>.parse(): Grid {
        val maxX = maxOf { it.length }
        return indices.map { y ->
            (0 until maxX).map { x ->
                val c = this[y][x]
                c
            }.toCharArray()
        }.toTypedArray()
    }

    override val day = "04".toInt()

    operator fun Grid.get(pos: Pos): Char = this[pos.y][pos.x]
    operator fun Grid.contains(pos: Pos): Boolean = pos.x >= 0 && pos.x < this[0].size && pos.y >= 0 && pos.y < this.size

}
