package no.rodland.advent_2024

import no.rodland.advent.Day
import no.rodland.advent.Pos

// template generated: 10/12/2024
// Fredrik Rødland 2024
class Day10(val input: List<String>) : Day<Int, Int, Map<Pos, Int>> {

    private val grid = input.parse()
    private val heads = grid.filterValues { it == 0 }.keys

    override fun partOne(): Int {
        return heads.sumOf { it.score(mutableSetOf()) }
    }

    override fun partTwo(): Int {
        return heads.sumOf { it.rating(emptyList(), mutableSetOf()) }
    }

    private fun Pos.score(visited: MutableSet<Pos>): Int {
        return grid[this]?.let { c ->
            visited.add(this)
            if (c == 9) 1
            else neighbourCellsUDLR().filterNot { it in visited }
                .filter { grid[it] == c + 1 }
                .sumOf { it.score(visited) }
        } ?: 0
    }

    private fun Pos.rating(path: List<Pos>, visited: MutableSet<List<Pos>>): Int {
        return grid[this]?.let { c ->
            val newPath = path + this
            visited.add(newPath)
            if (c == 9) 1
            else neighbourCellsUDLR().filterNot { (newPath + it) in visited }
                .filter { grid[it] == c + 1 }
                .sumOf { it.rating(newPath, visited) }
        } ?: 0
    }

    override fun List<String>.parse(): Map<Pos, Int> {
        return flatMapIndexed { y, str ->
            str.mapIndexed { x, c ->
                Pos(x, y) to c.digitToInt()
            }
        }.toMap()
    }

    override val day = "10".toInt()

}



