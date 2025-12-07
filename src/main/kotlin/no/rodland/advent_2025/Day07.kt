package no.rodland.advent_2025

import no.rodland.advent.Day
import no.rodland.advent.Pos

// template generated: 07/12/2025
// Fredrik Rødland 2025

class Day07(val input: List<String>) : Day<Long, Long, Pair<Grid, Pos>> {

    private val parsed = input.parse()

    override fun partOne(): Long {
        val (grid, start) = parsed
        return dfs(grid, start, mutableSetOf()).toLong()
    }

    @Suppress("KotlinConstantConditions")
    private fun dfs(grid: Grid, pos: Pos, visited: MutableSet<Pos>): Int {
        // stop if outside grid
        if (pos !in grid) return 0
        // stop if already visited
        if (!visited.add(pos)) return 0

        val c = grid[pos]
        val found = if (c == '^') 1 else 0

        return if (c == '^') {
            found +
                dfs(grid, pos.left(), visited) +
                dfs(grid, pos.right(), visited)
        } else {
            found + dfs(grid, pos.below(), visited)
        }
    }

    override fun partTwo(): Long {
        return 2
    }


    override fun List<String>.parse(): Pair<Grid, Pos> {
        var start: Pos = Pos(0, 0)
        val grid = mapIndexed { y, row ->
            val x = row.indexOf('S')
            if (x != -1) {
                start = Pos(x, y)
            }
            row.toCharArray()
        }.toTypedArray()
        return grid to start
    }

    override val day = "07".toInt()

}
