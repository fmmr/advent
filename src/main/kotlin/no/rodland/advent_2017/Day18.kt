package no.rodland.advent_2017

import no.rodland.advent_2017.prog.Program.Companion.parse

@Suppress("UNUSED_PARAMETER")
object Day18 {

// 3675 too high

    fun partOne(list: List<String>): Long {
        val program = parse(list)
        val sound = program.compile(mutableMapOf()).first()
        return sound
    }

    fun partTwo(list: List<String>): Int {
        return 2
    }
}
