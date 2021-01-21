package no.rodland.advent_2016

import no.rodland.advent_2016.assembunny.parseProgram
import no.rodland.advent_2016.assembunny.runProgram

@Suppress("UNUSED_PARAMETER")
object Day23 {
    fun partOne(list: List<String>, initialA: Int): Int {
        val program = parseProgram(list)
        return program.runProgram(0, mutableMapOf("a" to initialA, "b" to 0, "c" to 0, "d" to 0))["a"]!!
    }

    fun partTwo(list: List<String>): Int {
        val program = parseProgram(list)
        return program.runProgram(0, mutableMapOf("a" to 12, "b" to 0, "c" to 0, "d" to 0))["a"]!!
    }
}
