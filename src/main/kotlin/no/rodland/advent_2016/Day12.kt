package no.rodland.advent_2016

import no.rodland.advent_2016.assembunny.compile
import no.rodland.advent_2016.assembunny.parseProgram
import no.rodland.advent_2016.assembunny.runProgram


@Suppress("UNUSED_PARAMETER")
object Day12 {
    fun partOne(list: List<String>): Int {
        val program = parseProgram(list)
        val reg = mutableMapOf("a" to 0, "b" to 0, "c" to 0, "d" to 0)
        program.compile(reg).runProgram()
        return reg["a"]!!
    }


    fun partTwo(list: List<String>): Int {
        val program = parseProgram(list)
        val reg = mutableMapOf("a" to 0, "b" to 0, "c" to 1, "d" to 0)
        program.compile(reg).runProgram()
        return reg["a"]!!
    }
}
