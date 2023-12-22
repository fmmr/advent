package no.rodland.advent_2023

import no.rodland.advent.Day
import no.rodland.advent.Pos3D

// template generated: 22/12/2023
// Fredrik Rødland 2023

class Day22(val input: List<String>) : Day<Long, Long, List<Day22.Brick>> {

    private val parsed = input.parse()

    override fun partOne(): Long {
        return 2
    }

    override fun partTwo(): Long {
        return 2
    }

    data class Brick(val from: Pos3D, val to: Pos3D)

    override fun List<String>.parse(): List<Brick> {
        return map { line ->
            val (from, to) = line.split('~')
            Brick(Pos3D(from), Pos3D(to))
        }
    }

    override val day = "22".toInt()
}
