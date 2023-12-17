package no.rodland.advent_2023

import no.rodland.advent.Day
import no.rodland.advent.Direction
import no.rodland.advent.Pos
import java.util.*

// template generated: 17/12/2023
// Fredrik Rødland 2023

class Day17(val input: List<String>) : Day<Int, Int, Map<Pos, Int>> {
    private val parsed = input.parse()
    private val width = input.first().length
    private val height = input.size

    override fun partOne(): Int {
        return dijkstra(1) { state, nextDirection ->
            state.steps < 3 || state.direction != nextDirection
        }
    }

    override fun partTwo(): Int {
        val minToMove = 4
        return dijkstra(minToMove) { state, nextDirection ->
            !((state.steps > 9 && state.direction == nextDirection) || ((state.steps < minToMove) && state.direction != nextDirection))
        }
    }

    private fun dijkstra(minStepsToMove: Int = 1, isValidNextMove: (State, Direction) -> Boolean): Int {
        val goal = Pos(width - 1, height - 1)
        val seen = mutableSetOf<State>()
        val queue = PriorityQueue(heatLossComparator)

        State(Pos(0, 0), Direction.EAST, 0).apply {
            queue += this to 0
            seen += this
        }

        while (queue.isNotEmpty()) {
            val (state, heatLoss) = queue.poll()
            if (state.pos == goal && state.steps >= minStepsToMove) return heatLoss

            Direction.entries
                .asSequence()
                .filterNot { d -> state.direction.goBack() == d }
                .filter { direction -> isValidNextMove(state, direction) }
                .map { direction -> state.next(direction) }
                .filter { newState -> newState.pos in parsed }
                .filterNot { newState -> newState in seen }
                .forEach { newState ->
                    queue += newState to heatLoss + parsed[newState.pos]!!
                    seen += newState
                }
        }
        throw IllegalStateException("No route to goal")
    }

    operator fun Array<Array<Char>>.contains(pos: Pos): Boolean = pos.x in 0..<width && pos.y in 0..<height
    operator fun Array<Array<Char>>.get(pos: Pos): Char = this[pos.y][pos.x]

    private val heatLossComparator: Comparator<Pair<State, Int>> = compareBy { it.second }

    private data class State(val pos: Pos, val direction: Direction, val steps: Int) {
        fun next(nextDirection: Direction): State =
            State(pos.next(nextDirection), nextDirection, if (direction == nextDirection) steps + 1 else 1)
    }

    override fun List<String>.parse(): Map<Pos, Int> {
        return flatMapIndexed { y, line ->
            line.mapIndexed { x, c ->
                Pos(x, y) to c.digitToInt()
            }
        }.toMap()
    }

    override val day = "17".toInt()
}

