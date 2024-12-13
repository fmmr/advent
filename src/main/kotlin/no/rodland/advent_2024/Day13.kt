package no.rodland.advent_2024

import no.rodland.advent.Day
import no.rodland.advent.Pos

// template generated: 13/12/2024
// Fredrik Rødland 2024

class Day13(val input: List<String>) : Day<Long, Long, List<Day13.Machine>> {

    private val machines = input.parse()

    override fun partOne(): Long {
        return 2
    }

    override fun partTwo(): Long {
        return 2
    }

    override fun List<String>.parse(): List<Machine> {
        return chunked(4).map { (a, b, p) ->
            val ax = a.substringAfter("X+").substringBefore(",").toInt()
            val ay = a.substringAfter("Y+").toInt()
            val bx = b.substringAfter("X+").substringBefore(",").toInt()
            val by = b.substringAfter("Y+").toInt()
            val px = p.substringAfter("X=").substringBefore(",").toInt()
            val py = p.substringAfter("Y=").toInt()
            Machine(Pos(ax, ay), Pos(bx, by), Pos(px, py))
        }
    }

    data class Machine(val a: Pos, val b: Pos, val prize: Pos)
    override val day = "13".toInt()
}
