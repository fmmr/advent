package no.rodland.advent_2017

import no.rodland.advent_2017.prog.Program.Companion.parse
import no.rodland.advent_2017.prog.runProgram

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
        val seq0 = program0.compile(mutableMapOf("p" to 0), false, program1)
        val seq1 = program1.compile(mutableMapOf("p" to 1), false, program0)
        val run1 = Thread { seq0.runProgram() }
        val run2 = Thread { seq1.runProgram() }
        run1.start()
        run2.start()
        Thread.sleep(500)
        return program1.numSent
    }
}
