package no.rodland.advent_2019

import no.rodland.advent.Pos
import kotlin.math.pow

@Suppress("UNUSED_PARAMETER")
object Day24 {
    fun partOne(list: List<String>): Int {
        val set = mutableSetOf<List<String>>()
        val grid = Grid(0, list)
        val firstTile = generateSequence(grid) { g ->
            runSim(g, null, ::getNeighboorsPart1)
        }.first { !set.add(it) }
        return biodiversityRating(firstTile)
    }

    private fun getNeighboorsPart1(grid: Grid, x: Int, y: Int, map: Map<Int, Grid>?) = Pos(x, y)
        .neighbourCellsUDLR()
            .filter { it.isInGrid(grid) }
            .count { grid[it.y][it.x] == '#' }


    private fun getNeighboorsPart2(grid: Grid, x: Int, y: Int, map: Map<Int, Grid>?): Int {
        if (x == 2 && y == 2) {
            return 0
        }
        val parent = map!![grid.level - 1] ?: error("cannot find parent for $grid")
        val child = map[grid.level + 1] ?: error("cannot find child for $grid")
        val pos = Pos(x, y)
        val countSameGrid = pos.neighbourCellsUDLR()
            .filter { it.isInGrid(grid) }
            .filterNot { it.x == 2 && it.y == 2 }
            .count { grid[it.y][it.x] == '#' }
        val countFromParentAndChild = when (pos) {
            Pos(0, 0) -> listOf(parent[2][1], parent[1][2])  // 1
            Pos(4, 0) -> listOf(parent[2][3], parent[1][2])  // 5
            Pos(0, 4) -> listOf(parent[2][1], parent[3][2])  // 21
            Pos(4, 4) -> listOf(parent[2][3], parent[3][2])  // 25

            Pos(1, 0), Pos(2, 0), Pos(3, 0) -> listOf(parent[1][2])  // 2,3,4
            Pos(0, 1), Pos(0, 2), Pos(0, 3) -> listOf(parent[2][1])  // 6,11,16
            Pos(4, 1), Pos(4, 2), Pos(4, 3) -> listOf(parent[2][3])  // 10, 15, 20
            Pos(1, 4), Pos(2, 4), Pos(3, 4) -> listOf(parent[3][2])  // 22, 23, 24

            Pos(2, 1) -> child[0].toCharArray().toList()            // 8
            Pos(1, 2) -> child.map { it[0] } // 12
            Pos(3, 2) -> child.map { it[4] } // 14
            Pos(2, 3) -> child[4].toCharArray().toList()            // 18
            Pos(2, 2), Pos(1, 1), Pos(1, 3), Pos(3, 1), Pos(3, 3) -> emptyList() // 7,8,17,19 and middle

            else -> error("should never happen $pos")
        }.count { it == '#' }

        return countSameGrid + countFromParentAndChild
    }

    fun partTwo(list: List<String>, iterations: Int): Int {
        val map = newMap(list, iterations)
        return map.values.map { it.countActive() }.sum()
    }

    fun newMap(list: List<String>, iterations: Int): MutableMap<Int, Grid> {
        val map = mutableMapOf(0 to Grid(0, list), -1 to emptyGrid(-1), 1 to emptyGrid(1))
        repeat(iterations) {
            val existingGrids = map.values.map { it }
            val max = map.keys.maxOrNull() ?: error("should not be null")
            val min = map.keys.minOrNull() ?: error("should not be null")
            map[max + 1] = emptyGrid(max + 1)
            map[min - 1] = emptyGrid(min - 1)
            existingGrids
                    .map { g ->
                        runSim(g, map, ::getNeighboorsPart2)
                    }
                    .forEach { g ->
                        map[g.level] = g
                    }
        }
        return map
    }

    data class Grid(val level: Int, val list: List<String>) : List<String> by list {
        fun countActive() = list.map { str -> str.count { it == '#' } }.sum()
        override fun toString(): String {
            return "$level list: $list"
        }
    }

    fun emptyGrid(level: Int): Grid = Grid(level, emptyGrid())
    private fun emptyGrid(): List<String> = (1..5).map { "....." }

    private fun biodiversityRating(grid: List<String>) = grid
            .flatMap { it.toList() }
            .mapIndexed { idx, c -> 2.toDouble().pow(idx.toDouble()).toInt() * if (c == '#') 1 else 0 }
            .sum()

    private fun runSim(grid: Grid, map: Map<Int, Grid>?, neighboorsFunc: (Grid, Int, Int, Map<Int, Grid>?) -> Int): Grid {
        val newList = grid.mapIndexed { y, row ->
            row.mapIndexed { x, c ->
                val infectedNeighboors = neighboorsFunc(grid, x, y, map)
                c.newChar(infectedNeighboors)
            }.joinToString("")
        }
        return Grid(grid.level, newList)
    }

    private fun Char.newChar(infected: Int) = when {
        this == '#' && infected != 1 -> '.'
        this == '.' && infected in 1..2 -> '#'
        else -> this
    }
}
