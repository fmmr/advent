package no.rodland.advent_2019

import no.rodland.advent.Pos
import kotlin.math.pow

@Suppress("UNUSED_PARAMETER")
object Day24 {
    fun partOne(list: List<String>): Int {
        val set = mutableSetOf<List<String>>()
        val firstTile = generateSequence(list) { l ->
            runSim(l)
        }.first { !set.add(it) }
        return biodiversityRating(firstTile)
    }

    fun partTwo(list: List<String>): Int {
        return 2
    }

    private fun biodiversityRating(grid: List<String>) = grid
            .flatMap { it.toList() }
            .mapIndexed { idx, c -> 2.toDouble().pow(idx.toDouble()).toInt() * if (c == '#') 1 else 0 }
            .sum()

    fun runSim(grid: List<String>): List<String> {
        return grid.mapIndexed { y, row ->
            row.mapIndexed { x, c ->
                val neighboors = Pos(x, y).neighboorCellsNDLR().filter { it.isInGrid(grid) }
                val numEmpty = neighboors.count { grid[it.y][it.x] == '.' } + (4 - neighboors.size)
                val infected = 4 - numEmpty
                val ret = if (c == '#' && infected != 1) {
                    '.'
                } else if (c == '.' && infected in 1..2) {
                    '#'
                } else {
                    c
                }
                ret
            }.joinToString("")
        }
    }

}
