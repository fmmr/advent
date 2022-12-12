package no.rodland.advent_2022

import no.rodland.advent.IntGrid
import no.rodland.advent.Pos

// template generated: 28/11/2022
// Fredrik Rødland 2022

object Day12 {
    fun partOne(list: List<String>): Int {
        val grid = IntGrid.fromChar(list)
        val all = grid.all()
        val start = all.first { it.second == 'S'.code }.first
        val end = all.first { it.second == 'E'.code }.first
        val shortestPath = bfs(grid, start, end)
        return shortestPath.size - 1
    }

    fun partTwo(list: List<String>): Int {
        val grid = IntGrid.fromChar(list)
        val all = grid.all()
        val start = all.filter { it.second == 'a'.code || it.second == 'S'.code }.map { it.first }
        val end = all.first { it.second == 'E'.code }.first
        val sorted = start.map { bfs(grid, it, end).size - 1 }.filter { it != -1 }.sorted()
        return sorted.min()
    }

    private fun bfs(grid: IntGrid, start: Pos, end: Pos): List<Pos> {
        val queue = ArrayDeque(listOf(start))
        val visited = mutableSetOf(start)
        val parents = mutableMapOf<Pos, Pos>()
        while (queue.isNotEmpty()) {
            val current = queue.removeFirst()
            if (current == end) {
                return parents.pathTo(end)
            }
            grid.neighbours(current)
                .filter { grid[it] < (grid[current] + 2) }
                .filterNot { it in visited }
                .forEach { neighbor ->
                    queue.add(neighbor)
                    visited.add(neighbor)
                    parents[neighbor] = current
                }
        }
        return emptyList()
    }

    private fun MutableMap<Pos, Pos>.pathTo(end: Pos): List<Pos> {
        val path = mutableListOf<Pos>()
        var node: Pos? = end
        while (node != null) {
            path.add(node)
            node = this[node]
        }
        return path.reversed()
    }

    private operator fun IntGrid.get(pos: Pos): Int {
        return when (val i = this[pos.y][pos.x]) {
            'S'.code -> 'a'.code
            'E'.code -> 'z'.code
            else -> i
        }
    }
}
