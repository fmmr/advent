package no.rodland.advent_2025

import no.rodland.advent.Day
import no.rodland.advent.Pos

// template generated: 04/12/2025
// Fredrik Rødland 2025
typealias Grid = Array<CharArray>

class Day04(val input: List<String>) : Day<Long, Long, Grid> {

    private val parsed = input.parse()

    override fun partOne(): Long {
        return parsed.movables().count().toLong()
    }

    override fun partTwo(): Long {
        val newGrid = parsed.copy()
        var remove = newGrid.movables()
        while (remove.isNotEmpty()) {
            newGrid.clear(remove)
            remove = newGrid.movables()
        }
        return (parsed.countPaper() - newGrid.countPaper()).toLong()
    }

    private fun Grid.movables(): List<Pos> = indices.flatMap { y ->
        this[y].indices.mapNotNull { x ->
            val pos = Pos(x, y)
            if (this[pos] == '@' && pos.neighbourCellsAllEight().filter { it in this }.count { this[it] == '@' } < 4) {
                pos
            } else {
                null
            }
        }
    }

    override fun List<String>.parse(): Grid = map { it.toCharArray() }.toTypedArray()

    operator fun Grid.get(pos: Pos): Char = this[pos.y][pos.x]
    operator fun Grid.contains(pos: Pos): Boolean = pos.x >= 0 && pos.x < this[0].size && pos.y >= 0 && pos.y < this.size
    private fun Grid.countPaper(): Int = sumOf { row -> row.count { c -> c == '@' } }
    private fun Grid.copy() = this.map { row -> row.map { it }.toCharArray() }.toTypedArray<CharArray>()
    private fun Grid.clear(list: List<Pos>) = list.forEach { pos -> this[pos.y][pos.x] = '.' }

    override val day = "04".toInt()
}

