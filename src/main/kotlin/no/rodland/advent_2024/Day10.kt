package no.rodland.advent_2024

import no.rodland.advent.Day
import no.rodland.advent.Pos

// template generated: 10/12/2024
// Fredrik Rødland 2024
class Day10(val input: List<String>) : Day<Int, Int, Map<Pos, Int>> {

    private val grid = input.parse()
    private val heads = grid.filterValues { it == 0 }.keys

    override fun partOne(): Int {
        return heads.sumOf {
            it.score().toSet().size
        }
    }

    override fun partTwo(): Int {
        return heads.sumOf {
            it.score().size
        }
    }

    private fun Pos.score(): List<Pos> {
        val c = grid[this]!!
        return if (c == 9) listOf(this)
        else neighbourCellsUDLR()
            .filter { grid[it] == c + 1 }
            .flatMap { it.score() }
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



