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
        val (grid, start) = parsed
        // Use memoized DFS with cycle detection: O(V+E) unique states
        return countPathsMemo(grid, start, mutableMapOf(), mutableSetOf())
    }

    // Count the total number of distinct paths following the same movement rules as part one:
    // - Move down by default
    // - If on '^', branch left and right
    // A path ends when moving outside the grid.
    // We memoize the number of paths from each position
    // and use an "inProgress" set to break cycles (back-edges), so we only count simple paths.
    private fun countPathsMemo(grid: Grid, pos: Pos, memo: MutableMap<Pos, Long>, inProgress: MutableSet<Pos>): Long {
        // stepping outside the grid completes one path
        if (pos !in grid) return 1L
        // return cached if computed
        memo[pos]?.let { return it }

        // back-edge: reaching a node already on the current recursion stack -> 
        // no simple path through this edge
//        if (!inProgress.add(pos)) return 0L

        val c = grid[pos]
        val total = if (c == '^') {
            countPathsMemo(grid, pos.left(), memo, inProgress) + countPathsMemo(grid, pos.right(), memo, inProgress)
        } else {
            countPathsMemo(grid, pos.below(), memo, inProgress)
        }

//        inProgress.remove(pos)
        memo[pos] = total
        return total
    }

    override fun List<String>.parse(): Pair<Grid, Pos> {
        var start = Pos(0, 0)
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
