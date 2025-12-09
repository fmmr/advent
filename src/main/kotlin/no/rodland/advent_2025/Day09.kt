package no.rodland.advent_2025

import cartesianPairs
import no.rodland.advent.Day
import no.rodland.advent.Pos
import kotlin.math.abs

// template generated: 09/12/2025
// Fredrik Rødland 2025

class Day09(val input: List<String>) : Day<Long, Long, List<Pair<Pos, Pos>>> {

    private val parsed = input.parse()

    override fun partOne(): Long {
        val greatest = parsed.maxBy { (p, q) -> area(p, q) }
        return area(greatest.first, greatest.second)
    }


    fun area(p: Pos, q: Pos): Long = (abs(q.x - p.x).toLong() + 1) * (abs(q.y - p.y).toLong() + 1)

    override fun partTwo(): Long {
        return 2
    }

    override fun List<String>.parse(): List<Pair<Pos, Pos>> {
        return map { line ->
            line.split(",").map { it.toInt() }.let { (x, y) -> Pos(x, y) }
        }.cartesianPairs()
    }

    override val day = "09".toInt()
}
