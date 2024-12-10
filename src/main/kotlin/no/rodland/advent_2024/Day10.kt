package no.rodland.advent_2024

import no.rodland.advent.Day
import no.rodland.advent.Pos

// template generated: 10/12/2024
// Fredrik Rødland 2024
class Day10(val input: List<String>) : Day<Int, Long, Pair<Pair<Set<Pos>, Set<Pos>>, Array<CharArray>>> {

    private val parsed = input.parse()
    private val heads = parsed.first.first
    private val tails = parsed.first.second
    private val grid = parsed.second

    override fun partOne(): Int {
        return heads.sumOf { it.score(mutableSetOf()) }
    }

    override fun partTwo(): Long {
        return 2
    }

    private fun Pos.score(visited: MutableSet<Pos>): Int {
        visited.add(this)
        val c = grid[this].digitToInt()
        return if (c == 9) return 1
        else neighbourCellsUDLR()
            .filterNot { it in visited }
            .filter { it in grid }
            .filter { grid[it].digitToInt() == c + 1 }
            .sumOf { it.score(visited) }
    }

    operator fun Grid.contains(pos: Pos): Boolean = pos.x >= 0 && pos.x < this[0].size && pos.y >= 0 && pos.y < this.size

    operator fun Grid.get(pos: Pos): Char = this[pos.y][pos.x]

    override fun List<String>.parse(): Pair<Pair<Set<Pos>, Set<Pos>>, Array<CharArray>> {
        val heads = mutableSetOf<Pos>()
        val tails = mutableSetOf<Pos>()
        val grid = indices.map { y ->
            indices.map { x ->
                val c = this[y][x]
                if (c == '0') heads.add(Pos(x, y))
                if (c == '9') tails.add(Pos(x, y))
                c
            }.toCharArray()
        }.toTypedArray()
        return (heads to tails) to grid
    }

    override val day = "10".toInt()
}

