package no.rodland.advent_2022

import no.rodland.advent.IntGrid
import no.rodland.advent.Pos

// template generated: 28/11/2022
// Fredrik Rødland 2022
object Day12 {
    fun partOne(list: List<String>, printGrid: Boolean = false): Int {
        val grid = IntGrid.fromChar(list)
        val all = grid.all()
        val start = all.first { it.second == 'S'.code }.first
        val end = all.first { it.second == 'E'.code }.first
        val shortestPath = bfs(grid, start, setOf(end)) { potentialMove, current -> grid[potentialMove] < (grid[current] + 2) }
        if (printGrid) {
            grid.printRoute(
                { p -> (grid[p] - 'a'.code).toDouble() / ('z'.code - 'a'.code) },
                { p: Pos -> if (p in shortestPath) Char(grid[p]) else ' ' }
            )
        }
        return shortestPath.size - 1
    }

    fun partTwo(list: List<String>): Int {
        val grid = IntGrid.fromChar(list)
        val all = grid.all()
        val start = all.first { it.second == 'E'.code }.first
        val end = all.filter { it.second == 'a'.code || it.second == 'S'.code }.map { it.first }.toSet()
        val shortestPath = bfs(grid, start, end) { potentialMove, current -> grid[potentialMove] > (grid[current] - 2) }
        return shortestPath.size - 1
    }

    private fun bfs(grid: IntGrid, start: Pos, end: Set<Pos>, walkRestriction: (Pos, Pos) -> Boolean): List<Pos> {
        val queue = mutableListOf(start)
        val parents = mutableMapOf<Pos, Pos?>().apply { put(start, null) }
        while (queue.isNotEmpty()) {
            val current = queue.removeFirst()
            if (current in end) {
                return generateSequence(current) { parents[it] }.toList()
            }
            grid.neighbours(current)
                .filter { walkRestriction(it, current) }
                .filterNot { it in parents.keys }
                .forEach { neighbor ->
                    queue.add(neighbor)
                    parents[neighbor] = current
                }
        }
        return emptyList()
    }

    private operator fun IntGrid.get(pos: Pos): Int {
        return when (val i = this[pos.y][pos.x]) {
            'S'.code -> 'a'.code
            'E'.code -> 'z'.code
            else -> i
        }
    }
}
