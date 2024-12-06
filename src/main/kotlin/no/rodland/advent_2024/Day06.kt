package no.rodland.advent_2024

import no.rodland.advent.Day
import no.rodland.advent.Direction
import no.rodland.advent.Pos

// template generated: 06/12/2024
// Fredrik Rødland 2024

class Day06(val input: List<String>) : Day<Int, Int, Pair<Pos, Array<CharArray>>> {

    private val parsed = input.parse()
    private val start = parsed.first
    private val grid = parsed.second

    override fun partOne(): Int {
        return doTheWalk()!!.map { it.first }.toSet().size
    }

    override fun partTwo(): Int {
        val obstructions = doTheWalk()!!.map { it.first }.toSet() - start
        return obstructions
            .parallelStream()
            .map { check ->
                doTheWalk { g: Grid, p: Pos -> if (p == check) '#' else g[p] }
            }
            .filter { it == null }
            .count().toInt()
    }

    private fun doTheWalk(getter: (g: Grid, p: Pos) -> Char = { g, p -> g[p] }): MutableSet<Pair<Pos, Direction>>? {
        var current = start
        var dir = Direction.NORTH
        val visited = mutableSetOf(current to dir)
        while (true) {
            val next = current.next(dir)
            if (visited.contains(next to dir)) {
                return null
            }
            if (!next.isInGrid(grid)) {
                break
            }
            val value = getter(grid, next)
            if (value == '#') {
                dir = dir.right()
            } else {
                visited.add(next to dir)
                current = next
            }
        }
        return visited
    }

    private operator fun Grid.get(pos: Pos): Char = this[pos.y][pos.x]

    override fun List<String>.parse(): Pair<Pos, Array<CharArray>> {
        var start: Pos? = null
        val grid = indices.map { y ->
            indices.map { x ->
                val c = this[y][x]
                if (c == '^') start = Pos(x, y)
                c
            }.toCharArray()
        }.toTypedArray()
        return start!! to grid
    }

    override val day = "06".toInt()
}

