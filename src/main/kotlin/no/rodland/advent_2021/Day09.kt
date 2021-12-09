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
        return lowPoints(grid, list).map { it.second }.sumOf { it + 1 }
    }

    private fun lowPoints(grid: Array<IntArray>, list: List<String>): List<Pair<Pos, Int>> {
        val lowPoints = grid.flatMapIndexed { y, line ->
            line.mapIndexed { x, value ->
                val neighboors = Pos(x, y).neighboorCellsNDLR().filter { it.isInGrid(grid) }
                if (neighboors.all { grid[it.y][it.x] > value }) {
                    Pos(x, y) to value
                } else {
                    null
                }
            }
        }.filterNotNull()
        return lowPoints
    }

    fun partTwo(list: List<String>): Int {
        val grid = Array(list.size) { row ->
            val line = list[row].toCharArray().map { it.asInt() }
            IntArray(line.size) { line[it] }
        }
        return lowPoints(grid, list)
            .map {
                it to getRegion(it.first, grid).distinct()
            }
//            .onEach { println("n $it") }
            .map { it.second.size }
            .sorted()
            .takeLast(3)
            .reduce { acc, i -> acc * i }
    }

    private fun getRegion(pos: Pos, grid: Array<IntArray>, included: List<Pos> = emptyList()): List<Pos> {
        val value = grid[pos.y][pos.x]
        val newNeighboors = pos.neighboorCellsNDLR()
            .filter { it.isInGrid(grid) }
            .filter { it !in included }
            .filter { grid[it.y][it.x] != 9 }
            .filter { grid[it.y][it.x] > value }
        return (included + pos + newNeighboors).let { list ->
            list + newNeighboors.flatMap { n -> getRegion(n, grid, list) }
        }
    }
}


