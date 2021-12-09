package no.rodland.advent_2021

import asInt
import no.rodland.advent.Pos

@Suppress("UNUSED_PARAMETER")
object Day09 {
    fun partOne(list: List<String>): Int {
        val grid = list.toGrid()
        return lowPoints(grid, list).map { it.second }.sumOf { it + 1 }
    }

    fun partTwo(list: List<String>): Int {
        val grid = list.toGrid()
        return lowPoints(grid, list)
            .map { getRegion(it.first, grid).size }
            .sorted()
            .takeLast(3)
            .reduce { acc, i -> acc * i }
    }

    private fun getRegion(pos: Pos, grid: Array<IntArray>): Set<Pos> {
        val frontier = ArrayDeque<Pos>()
        val visited = mutableSetOf<Pos>()
        frontier.add(pos)
        while (!frontier.isEmpty()) {
            frontier.removeFirst().let { potentialPos ->
                if (grid[potentialPos.y][potentialPos.x] < 9 && visited.add(potentialPos)) {
                    grid.neighboors(potentialPos).forEach { frontier.add(it) }
                }
            }
        }
        return visited
    }


    private fun lowPoints(grid: Array<IntArray>, list: List<String>): List<Pair<Pos, Int>> {
        val lowPoints = grid.flatMapIndexed { y, line ->
            line.mapIndexed { x, value ->
                val neighboors = grid.neighboors(Pos(x, y))
                if (neighboors.all { grid[it.y][it.x] > value }) {
                    Pos(x, y) to value
                } else {
                    null
                }
            }
        }.filterNotNull()
        return lowPoints
    }

    private fun Array<IntArray>.neighboors(p: Pos) = p.neighboorCellsNDLR().filter { it.isInGrid(this) }

    private fun List<String>.toGrid() = Array(size) { row ->
        val line = this[row].toCharArray().map { it.asInt() }
        IntArray(line.size) { line[it] }
    }
}


