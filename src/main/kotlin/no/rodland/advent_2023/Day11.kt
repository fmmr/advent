package no.rodland.advent_2023

import no.rodland.advent.Day
import no.rodland.advent.Pos
import kotlin.math.max
import kotlin.math.min

// template generated: 11/12/2023
// Fredrik Rødland 2023

class Day11(val input: List<String>) : Day<Long, Long, Set<Pos>> {

    private val parsed = input.parse()

    override fun partOne(): Long {
        return findDistances(2)
    }

    override fun partTwo(): Long {
        return findDistances(1000000)
    }

    private fun findDistances(expansion: Int): Long {
        val maxX = input.maxOf { it.length }
        val maxY = input.size
        val missingCol = (0 until maxX) - parsed.map { it.x }.toSet()
        val missingRow = (0 until maxY) - parsed.map { it.y }.toSet()
        val pairs = parsed
            .flatMap { pos1 ->
                parsed.mapNotNull { pos2 ->
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
            .map { (from, to) ->
                val distance = from.manhattanDistance(to)
                val crossingCols = missingCol.count { it in (min(from.x, to.x)..max(to.x, from.x)) }
                val crossingRows = missingRow.count { it in (min(from.y, to.y)..max(to.y, from.y)) }
                (distance + (expansion - 1) * (crossingRows + crossingCols)).toLong()
            }
        return pairs.sum()
    }


    override fun List<String>.parse(): Set<Pos> {
        val maxX = maxOf { it.length }
        return indices.flatMap { y ->
            (0 until maxX).mapNotNull { x ->
                if (this[y][x] == '#') {
                    Pos(x, y)
                } else {
                    null
                }
            }
        }.toSet()
    }

    override val day = "11".toInt()
}
