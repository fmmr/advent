package no.rodland.advent_2024

import no.rodland.advent.*
import java.util.*

// template generated: 15/12/2024
// Fredrik Rødland 2024
data class State(val pos: Pos, val dir: Direction, val cost: Int)

class Day16(val input: List<String>) : Day<Int, Int, Cave> {

    private val parsed = input.parse()
    private val maze = parsed

    override fun partOne(): Int {
        val shortestPath = dijkstra(maze)
        return shortestPath
    }

    override fun partTwo(): Int {
        return 2
    }

    private fun dijkstra(maze: Cave): Int {
        val start = Pos(1, maze.size - 2) // Starting position (S)
        val end = Pos(maze[0].size - 2, 1) // Ending position (E)

        val pq = PriorityQueue<State>(compareBy { it.cost })
        val visited = mutableSetOf<Pair<Pos, Direction>>() // Track visited states by Pos and Direction

        pq.add(State(start, Direction.EAST, 0))

        while (pq.isNotEmpty()) {
            val current = pq.poll()
            if (!visited.add(Pair(current.pos, current.dir))) continue
            if (current.pos == end) return current.cost

            val move = current.pos.next(current.dir)
            if (move in maze && maze[move] != '#') {
                pq.add(State(move, current.dir, current.cost + 1))
            }
            pq.add(State(current.pos, current.dir.left(), current.cost + 1000))
            pq.add(State(current.pos, current.dir.right(), current.cost + 1000))
        }
        return -1 // If no path is found
    }

    override fun List<String>.parse(): Cave {
        return this.toCave()
    }

    override val day = "16".toInt()
}

