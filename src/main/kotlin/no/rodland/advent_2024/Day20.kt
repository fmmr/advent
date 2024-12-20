package no.rodland.advent_2024

import no.rodland.advent.*

// template generated: 20/12/2024
// Fredrik Rødland 2024

class Day20(val input: List<String>) : Day<Int, Int, Pair<Array<CharArray>, Pair<Pos, Pos>>> {

    private val parsed = input.parse()
    private val racetrack: Cave = parsed.first
    private val start = parsed.second.first
    private val end = parsed.second.second
    private val isTest = racetrack.size == 15
    private val limit = if (isTest) 10 else 100

    override fun partOne(): Int {
        return solve(2, limit)
    }

    override fun partTwo(): Int {
        return solve(20, limit)
    }

    private fun solve(maxDistance: Int, lim: Int): Int {
        val shortestPath = getPath().toTypedArray()
        return shortestPath.indices
            .sumOf { from ->
                ((from + lim)..<shortestPath.size).count { end ->
                    val manhattan = shortestPath[from].manhattanDistance(shortestPath[end])
                    manhattan <= maxDistance && manhattan <= end - from - lim
                }
            }
    }

    private fun getPath(): List<Pos> {
        return mutableListOf(start).apply {
            while (last() != end) {
                add(
                    last()
                        .neighbourCellsUDLR()
                        .filter { racetrack[it] != '#' }
                        .first { it != getOrNull(lastIndex - 1) }
                )
            }
        }
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
