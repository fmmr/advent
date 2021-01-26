package no.rodland.advent_2017

import no.rodland.advent_2017.prog.Program.Companion.parse

@Suppress("UNUSED_PARAMETER")
object Day18 {

// 3675 too high

    fun partOne(list: List<String>): Long {
        val program = parse("part1", list)
        return program.compile(mutableMapOf()).first()
    }

    fun partTwo(list: List<String>): Int {
        val program0 = parse("0", list)
        val program1 = parse("1", list)
        val seq0 = program0.compile(mutableMapOf("p" to 0), part1 = false)
        val seq1 = program1.compile(mutableMapOf("p" to 1), part1 = false)

        return 2
    }
}
