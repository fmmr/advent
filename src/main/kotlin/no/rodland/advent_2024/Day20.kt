package no.rodland.advent_2024

import no.rodland.advent.*

// template generated: 20/12/2024
// Fredrik Rødland 2024

class Day20(val input: List<String>, private val limit: Int = 0, private val limitTestPart2: Int = limit) : Day<Int, Int, Pair<Array<CharArray>, Pair<Pos, Pos>>> {

    private val parsed = input.parse()
    private val racetrack: Cave = parsed.first
    private val start = parsed.second.first
    private val end = parsed.second.second

    override fun partOne(): Int {
        return solve(2, limit)
    }

    override fun partTwo(): Int {
        return solve(20, limitTestPart2)
    }

    private fun solve(maxDistance: Int, lim: Int): Int {
        val shortestPath = bfs(start, end)
        val size = shortestPath.size
        val fromStart = shortestPath.mapIndexed { idx, pos -> pos to idx }.toMap()
        val toEnd = shortestPath.mapIndexed { idx, pos -> pos to size - idx }.toMap()
        return shortestPath.asSequence().flatMapIndexed() { idx, from ->
            shortestPath
                .subList(idx + 1, shortestPath.size)
                .mapNotNull { to ->
                    val manhattanDistance = from.manhattanDistance(to)
                    if (manhattanDistance in 2..maxDistance) {
                        manhattanDistance to to
                    } else {
                        null
                    }
                }
                .map { to ->
                    size - (fromStart[from]!! + to.first + toEnd[to.second]!!)
                }.filter { it > 0 }
        }
            .groupBy { it }
            .map { it.value.size to it.key }
            .filter { it.second >= lim }
            .sortedBy { it.second }
            .sumOf { it.first }
    }


    private tailrec fun bfs(
        start: Pos,
        end: Pos,
        queue: ArrayDeque<List<Pos>> = ArrayDeque(listOf(listOf(start))),
        visited: MutableSet<Pos> = mutableSetOf(start)
    ): List<Pos> {
        if (queue.isEmpty()) return emptyList()
        // Dequeue the current path
        val path = queue.removeFirst()
        val current = path.last()

        // Check if we reached the end position
        if (current == end) return path
        current.neighbourCellsUDLR()
            .filter { it !in visited }
            .filter { it in racetrack }
            .filter { racetrack[it] != '#' }
            .forEach { neighbor ->
                visited.add(neighbor)
                queue.add(path + neighbor) // Enqueue the new path
            }
        // Recursive call with the updated queue and visited set
        return bfs(start, end, queue, visited)
    }

    override fun List<String>.parse(): Pair<Array<CharArray>, Pair<Pos, Pos>> {
        var start = Pos(0, 0)
        var end = Pos(0, 0)
        return this.mapIndexed { y, line ->
            line.mapIndexed { x, c ->
                when (c) {
                    'S' -> start = Pos(x, y)
                    'E' -> end = Pos(x, y)
                }
                c
            }.toCharArray()
        }.toTypedArray<CharArray>() to (start to end)
    }

    override val day = "20".toInt()
}
