package no.rodland.advent_2024

import no.rodland.advent.Day
import no.rodland.advent.Pos

// template generated: 14/12/2024
// Fredrik Rødland 2024

class Day14(val input: List<String>) : Day<Long, Long, List<Day14.Robot>> {

    private val robots = input.parse()

    override fun partOne(): Long {
        return 2
    }

    override fun partTwo(): Long {
        return 2
    }

    override fun List<String>.parse(): List<Robot> {
        return map { line ->
            // p=0,4 v=3,-3
            val (px, py) = line.substringAfter("p=").substringBefore(" v=").split(",").map { it.toInt() }
            val (vx, vy) = line.substringAfter("v=").split(",").map { it.toInt() }
            Robot(Pos(px, py), Pos(vx, vy))
        }
    }

    data class Robot(val pos: Pos, val vel: Pos)

    override val day = "14".toInt()
}
