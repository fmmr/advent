package no.rodland.advent_2023

import no.rodland.advent.Day
import no.rodland.advent.Pos
import kotlin.math.max
import kotlin.math.min

// template generated: 11/12/2023
// Fredrik Rødland 2023

class Day11(val input: List<String>) : Day<Long, Long, Triple<List<Pair<Pos, Pos>>, List<Int>, List<Int>>> {

    private val parsed = input.parse()

    override fun partOne(): Long {
        return findDistances()
    }

    override fun partTwo(): Long {
        return findDistances(1000000L)
    }

    private fun findDistances(expansion: Long = 2L): Long {
        val (pairs, missingRow, missingCol) = parsed
        val distances = pairs
            .map { (from, to) -> distance(from, to, missingCol, missingRow, expansion) }
        return distances.sum()
    }

    private fun distance(from: Pos, to: Pos, missingCol: List<Int>, missingRow: List<Int>, expansion: Long): Long {
        val crossingCols = missingCol.count { it in range(from.x, to.x) }
        val crossingRows = missingRow.count { it in range(from.y, to.y) }
        return from.manhattanDistance(to) + (expansion - 1) * (crossingRows + crossingCols)
    }

    private fun range(from: Int, to: Int) = min(from, to)..max(to, from)


    override fun List<String>.parse(): Triple<List<Pair<Pos, Pos>>, List<Int>, List<Int>> {
        val maxX = input.maxOf { it.length }
        val maxY = input.size
        val galaxies = indices.flatMap { y ->
            (0 until maxX).mapNotNull { x ->
                if (this[y][x] == '#') {
                    Pos(x, y)
                } else {
                    null
                }
            }
        }.toSet()
        val missingCol = (0 until maxX) - galaxies.map { it.x }.toSet()
        val missingRow = (0 until maxY) - galaxies.map { it.y }.toSet()
        val pairs = galaxies
            .flatMap { pos1 ->
                galaxies.mapNotNull { pos2 ->
                    if (pos1 != pos2) {
                        if (pos1 < pos2) {
                            pos1 to pos2
                        } else {
                            pos2 to pos1
                        }
                    } else {
                        null
                    }
                }
            }
            .distinct()
        return Triple(pairs, missingRow, missingCol)
    }

    override val day = "11".toInt()
}
