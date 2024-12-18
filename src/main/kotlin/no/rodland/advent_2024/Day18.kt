package no.rodland.advent_2024

import no.rodland.advent.Day
import no.rodland.advent.Pos

// template generated: 18/12/2024
// Fredrik Rødland 2024

class Day18(val input: List<String>, private val bytesToTake: Int, maxIndex: Int) : Day<Int, Pos, List<Pos>> {

    private val parsed = input.parse()
    private val start = Pos(0, 0)
    private val end = Pos(maxIndex, maxIndex)

    override fun partOne(): Int {
        return bfs(bytesToTake).size - 1
    }

    override fun partTwo(): Pos {
        val findFirstPassingIndex: Int = binarySearch(bytesToTake, parsed.size - 1) { n: Int -> bfs(n).isEmpty() }
        return parsed[findFirstPassingIndex - 1]
    }

    private fun binarySearch(left: Int, right: Int, test: (index: Int) -> Boolean): Int {
        if (left > right) return -1
        val mid = left + (right - left) / 2
        return if (test(mid)) {
            // Check if this is the first passing index or search the left half
            val earlierIndex = binarySearch(left, mid - 1, test)
            if (earlierIndex != -1) earlierIndex else mid
        } else {
            // Search the right half
            binarySearch(mid + 1, right, test)
        }
    }

    private fun bfs(n: Int, set: Set<Pos> = parsed.take(n).toSet()) = bfs(start, end, { p ->
        p.neighbourCellsUDLR()
            .filter { it !in set }
            .filter { it.x >= start.x }
            .filter { it.y >= start.y }
            .filter { it.x <= end.x }
            .filter { it.y <= end.y }
    })

    private fun bfs(
        start: Pos,
        end: Pos,
        nextFunction: (Pos) -> List<Pos>,
        queue: ArrayDeque<List<Pos>> = ArrayDeque(listOf(listOf(start))),
        visited: MutableSet<Pos> = mutableSetOf(start)
    ): List<Pos> {
        if (queue.isEmpty()) return emptyList()
        // Dequeue the current path
        val path = queue.removeFirst()
        val current = path.last()

        // Check if we reached the end position
        if (current == end) return path

        // Explore neighbors
        for (neighbor in nextFunction(current)) {
            if (!visited.contains(neighbor)) {
                visited.add(neighbor)
                queue.add(path + neighbor) // Enqueue the new path
            }
        }

        // Recursive call with the updated queue and visited set
        return bfs(start, end, nextFunction, queue, visited)
    }

    override fun List<String>.parse(): List<Pos> {
        return map { line ->
            val (first, second) = line.split(",").map { it.toInt() }
            Pos(first, second)
        }
    }

    override val day = "18".toInt()
}
