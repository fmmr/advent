package no.rodland.advent_2022

import no.rodland.advent.Pos
import no.rodland.advent.Cave
import no.rodland.advent.set
import no.rodland.advent.get
import no.rodland.advent.print

// template generated: 28/11/2022
// Fredrik Rødland 2022
typealias CaveStart = Pair<Cave, Pos>

private const val START = 'S'
private const val EMPTY = '.'
private const val SAND = 'o'
private const val WALL = '#'

@Suppress("DuplicatedCode")
object Day14 {


    private data class CaveWithFloor(val cave: Cave, val extra: MutableSet<Pos>) {
        val height = cave.size
        val width = cave[0].size
        fun countSand(): Int {
            val flat = cave.flatMapIndexed { y, row -> row.mapIndexed { x, c -> Pos(x, y) to c } }
            val inCave = flat.filter { p -> p.second == SAND }.map { it.first }.toSet()
            return inCave.size + extra.filterNot { it in inCave }.size
        }

        fun print() {
            cave.forEachIndexed { idy, ca ->
                ca.forEachIndexed { idx, c ->
                    if (Pos(idx, idy) in extra) {
                        print(SAND)
                    } else {
                        print(c)
                    }
                }
                println()
            }
            println()
        }


        fun isEmpty(pos: Pos): Boolean {
            return if (pos.x < 0 || pos.x > width - 1) {
                pos !in extra
            } else {
                (cave[pos] == EMPTY && pos !in extra)
            }
        }

        fun isSand(pos: Pos): Boolean {
            return if (pos.x < 0 || pos.x > width - 1) {
                pos in extra
            } else {
                (cave[pos] == SAND || pos in extra)
            }
        }

        fun onFloor(pos: Pos) = pos.y == height - 2

        operator fun set(pos: Pos, value: Char) {
            if (pos.x < 0 || pos.x > width - 1 || onFloor(pos)) {
                extra.add(pos)
            } else {
                cave[pos] = value
            }
        }

    }

    private fun Cave.isEmpty(pos: Pos) = this[pos] == EMPTY

    fun partOne(input: List<String>, printCave: Boolean = false): Int {
        val (cave, start) = cave(input, Pos(500, 0))
        println("start: $start")
        if (printCave) cave.print()
        val units = doItPart1(cave, start, ::endStatePart1)
        if (printCave) cave.print()
        return units
    }

    fun partTwo(input: List<String>, printCave: Boolean = false): Int {
        val (cave, start) = cave(input, Pos(500, 0))
        val caveWithFloor = CaveWithFloor(cave, mutableSetOf())
        println("start: $start")
        if (printCave) caveWithFloor.print()
        val units = doItPart2(caveWithFloor, start) { c, pos -> endStatePart2(c, pos) }
        if (printCave) caveWithFloor.print()
        return units
    }

    private fun doItPart1(cave: Array<CharArray>, start: Pos, endState: (Cave, Pos) -> Pos): Int {
        var reachedAbyss = false
        while (!reachedAbyss) {
            val pos = endState(cave, start)

            if (pos.y == cave.size - 2) {
                reachedAbyss = true
            } else {
                cave[pos] = SAND
            }
        }
        return cave.sumOf { row -> row.count { c -> c == SAND } }
    }

    private fun doItPart2(cave: CaveWithFloor, start: Pos, endState: (CaveWithFloor, Pos) -> Pos): Int {
        var reachedAbyss = false
        while (!reachedAbyss) {
            val pos = endState(cave, start)
            cave[pos] = SAND
            if (pos == start) {
                reachedAbyss = true
            }

        }
        return cave.countSand()
    }

    private fun endStatePart2(cave: CaveWithFloor, pos: Pos): Pos {
        val below = pos.below()
        val sw = pos.sw()
        val se = pos.se()

        return when {
            cave.onFloor(pos) -> {
                if (cave.isSand(pos)) {
                    endStatePart2(cave, pos)
                } else {
                    pos
                }
            }

            cave.isEmpty(below) -> endStatePart2(cave, below)
            cave.isEmpty(sw) -> endStatePart2(cave, sw)
            cave.isEmpty(se) -> endStatePart2(cave, se)
            else -> pos
        }
    }

    private fun endStatePart1(cave: Array<CharArray>, pos: Pos): Pos {
        val below = pos.below()
        val sw = pos.sw()
        val se = pos.se()
        return when {
            pos.y == cave.size - 2 -> pos
            cave.isEmpty(below) -> endStatePart1(cave, below)
            cave.isEmpty(sw) -> endStatePart1(cave, sw)
            cave.isEmpty(se) -> endStatePart1(cave, se)
            else -> pos
        }
    }


    private fun cave(input: List<String>, start: Pos): CaveStart {
        val walls = input.wall().toSet()
        val (x, y) = Pos.getMinMax(walls)
        return cave(walls, start, x.first, x.second, y.second)
    }

    private fun cave(walls: Set<Pos>, start: Pos, minX: Int, maxX: Int, maxY: Int): CaveStart {
        var foundStart = Pos(0, 0)
        return (0..(maxY + 2)).mapIndexed { yi, y ->
            ((minX - 1)..(maxX + 1)).mapIndexed { xi, x ->
                if (Pos(x, y) == start) {
                    foundStart = Pos(xi, yi)
                    START
                } else if (y == maxY + 2) {
                    WALL
                } else if (Pos(x, y) in walls) {
                    WALL
                } else {
                    EMPTY
                }
            }.toCharArray()
        }.toTypedArray() to foundStart
    }


    private fun List<String>.wall(): List<Pos> {
        return flatMap { line ->
            line.split("->").windowed(2).flatMap { (first, second) ->
                Pos(first).fill(Pos(second))
            }
        }
    }
}


