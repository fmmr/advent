package no.rodland.advent_2019

import no.rodland.advent.Pos
import kotlin.math.pow

@Suppress("UNUSED_PARAMETER")
object Day24 {
    fun partOne(list: List<String>): Int {
        val set = mutableSetOf<List<String>>()
        val grid = Grid(0, list, null, null)
        val firstTile = generateSequence(grid) { g ->
            runSim(g, ::getNeighboorsPart1)
        }.first { !set.add(it) }
        return biodiversityRating(firstTile)
    }

    private fun getNeighboorsPart1(grid: Grid, x: Int, y: Int) = Pos(x, y).neighboorCellsNDLR().filter { it.isInGrid(grid) }

    data class Grid(val level: Int, val list: List<String>, var parent: Grid?, var child: Grid?) : List<String> by list

    fun emptyGrid(): List<String> = (1..5).map { "....." }

    fun partTwo(list: List<String>, iterations: Int): Int {
        return 2
    }

    private fun biodiversityRating(grid: List<String>) = grid
            .flatMap { it.toList() }
            .mapIndexed { idx, c -> 2.toDouble().pow(idx.toDouble()).toInt() * if (c == '#') 1 else 0 }
            .sum()

    private fun runSim(grid: Grid, neighboorsFunc: (Grid, Int, Int) -> List<Pos>): Grid {
        val newList = grid.mapIndexed { y, row ->
            row.mapIndexed { x, c ->
                val neighboors = neighboorsFunc(grid, x, y)
                val infected = neighboors.count { grid[it.y][it.x] == '#' }
                c.newChar(infected)
            }.joinToString("")
        }
        return Grid(grid.level, newList, grid.parent, grid.child)
    }

    private fun Char.newChar(infected: Int) = when {
        this == '#' && infected != 1 -> '.'
        this == '.' && infected in 1..2 -> '#'
        else -> this
    }

}
