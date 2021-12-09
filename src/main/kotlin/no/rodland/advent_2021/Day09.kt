package no.rodland.advent_2021

import asInt
import no.rodland.advent.Pos

@Suppress("UNUSED_PARAMETER")
object Day09 {
    fun partOne(list: List<String>): Int {
        val grid = Array(list.size) { row ->
            val line = list[row].toCharArray().map { it.asInt() }
            IntArray(line.size) { line[it] }
        }
        return grid.flatMapIndexed { y, line ->
            line.mapIndexed { x, value ->
                val neighboors = Pos(x, y).neighboorCellsNDLR().filter { it.isInGrid(grid) }
                if (neighboors.all { grid[it.y][it.x] > value }) {
                    value
                } else {
                    null
                }
            }
        }.filterNotNull().sumOf { it + 1 }
    }

    fun partTwo(list: List<String>): Int {
        return 2
    }
}

