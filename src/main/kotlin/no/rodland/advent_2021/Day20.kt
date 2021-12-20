package no.rodland.advent_2021

import no.rodland.advent.Pos

@Suppress("UNUSED_PARAMETER")
object Day20 {
    fun partOne(list: List<String>): Int {
        return solve(list, 2)
    }

    fun partTwo(list: List<String>): Int {
        return solve(list, 50)
    }

    private fun solve(list: List<String>, iterations: Int): Int {
        val (algorithm, map) = list.parse()
        val grid = Grid(map, algorithm)
        return (1..iterations).fold(grid) { acc, _ -> acc.transform() }.size
    }

    class Grid(val pos: Map<Pos, Char>, val algorithm: CharArray, private val infinty: Char = '.') : Map<Pos, Char> by pos {
        private val xMin = pos.keys.minOf { it.x }
        private val xMax = pos.keys.maxOf { it.x }
        private val yMin = pos.keys.minOf { it.y }
        private val yMax = pos.keys.maxOf { it.y }

        fun transform(): Grid {
            val newMap = (xMin - 1..xMax + 1).flatMap { x ->
                (yMin - 1..yMax + 1).map { y ->
                    val p = Pos(x, y)
                    val idx = algIndex(p)
                    val c = algorithm[idx]
                    p to c
                }
            }.toMap().filterValues { it == '#' }
            return Grid(newMap, algorithm, infinity())
        }

        private fun infinity() = when (infinty) {
            '.' -> algorithm.first()
            '#' -> algorithm.last()
            else -> error("unknown char: $infinty")
        }

        fun getValue(p: Pos): Int {
            val c = if (p.inRange(xMin..xMax, yMin..yMax)) {
                getOrDefault(p, '.')
            } else {
                infinty
            }
            return if (c == '#') 1 else 0
        }

        private fun algIndex(p: Pos) = p.neighboorCellsAllEightIncludingSelf().map { getValue(it) }.joinToString("").toInt(radix = 2)
    }

    private fun List<String>.parse() = first().toCharArray() to drop(2).flatMapIndexed { y, line -> line.mapIndexed { x, char -> Pos(x, y) to char } }.toMap().filterValues { it == '#' }
}

