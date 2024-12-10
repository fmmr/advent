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

    // part 1
    private fun Pos.score(visited: MutableSet<Pos>): Int {
        val c = grid[this]!!
        visited.add(this)
        return if (c == 9) 1
        else neighbourCellsUDLR().filterNot { it in visited }
            .filter { grid[it] == c + 1 }
            .sumOf { it.score(visited) }
    }

    // part 2
    private fun Pos.rating(path: List<Pos>, visited: MutableSet<List<Pos>>): Int {
        val c = grid[this]!!
        val newPath = path + this
        visited.add(newPath)
        return if (c == 9) 1
        else neighbourCellsUDLR().filterNot { (newPath + it) in visited }
            .filter { grid[it] == c + 1 }
            .sumOf { it.rating(newPath, visited) }
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



