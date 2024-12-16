package no.rodland.advent_2024

import no.rodland.advent.*
import java.util.*

// template generated: 15/12/2024
// Fredrik Rødland 2024
data class State(val pos: Pos, val dir: Direction, val cost: Int, val positions: Set<Pos>)

class Day16(val input: List<String>) : Day<Int, Int, Cave> {

    private val parsed = input.parse()
    private val maze = parsed

    override fun partOne(): Int {
        return dijkstraWithAllMinimalPaths(maze).first
    }

    override fun partTwo(): Int {
        return dijkstraWithAllMinimalPaths(maze).second.flatMap { it.positions }.toSet().size
    }

    private fun dijkstraWithAllMinimalPaths(maze: Cave): Pair<Int, MutableList<State>> {
        val start = Pos(1, maze.size - 2) // Starting position (S)
        val end = Pos(maze[0].size - 2, 1) // Ending position (E)

        val pq = PriorityQueue<State>(compareBy { it.cost })
        val costMap = mutableMapOf<Pair<Pos, Direction>, Int>()
        val endStates = mutableListOf<State>() // Collect all end states

        var minCost = Int.MAX_VALUE
        pq.add(State(start, Direction.EAST, 0, setOf(start)))
        while (pq.isNotEmpty()) {
            val current = pq.poll()

            // If this state exceeds the known minimum cost, skip it
            if (current.cost > minCost) continue

            // Allow revisiting if the cost is equal to the minimum
            val key = current.pos to current.dir

            // if we have an entry in the cache for this (pos,dir) which is STRICTLY lower we don't have to
            // keep adding it.  If it's equal or null - we should handle it.
            if (costMap[key]?.let { it < current.cost } == true) continue
            costMap[key] = current.cost

            // Add positions to current state's path
            val newPositions = current.positions + current.pos

            // If we reached the end, check and update the minimum cost
            if (current.pos == end) {
                minCost = current.cost
                endStates.add(current.copy(positions = newPositions))
                continue
            }

            // Generate next states
            val nextPos = current.pos.next(current.dir)
            val left = current.dir.left()
            val nextLeft = current.pos.next(left)
            val right = current.dir.right()
            val nextRight = current.pos.next(right)

            if (inMazeAndNotWall(nextPos, maze)) {
                pq.add(State(nextPos, current.dir, current.cost + 1, newPositions))
            }
            if (inMazeAndNotWall(nextLeft, maze)) {
                pq.add(State(nextLeft, left, current.cost + 1001, newPositions))
            }
            if (inMazeAndNotWall(nextRight, maze)) {
                pq.add(State(nextRight, right, current.cost + 1001, newPositions))
            }
        }
        return minCost to endStates
    }

    private fun inMazeAndNotWall(nextPos: Pos, maze: Cave) = nextPos in maze && maze[nextPos] != '#'


    override fun List<String>.parse() = this.toCave()

    override val day = "16".toInt()
}

