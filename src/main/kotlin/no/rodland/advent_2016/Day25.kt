package no.rodland.advent_2016

import no.rodland.advent_2016.assembunny.compile
import no.rodland.advent_2016.assembunny.parseProgram

@Suppress("UNUSED_PARAMETER")
object Day25 {
    fun partOne(list: List<String>): Int {
        val program = parseProgram(list)
        val lengthOfForever = 20
        (1..1000).forEach { a ->
            val reg = mutableMapOf("a" to a, "b" to 0, "c" to 0, "d" to 0)
            val seq = program.compile(reg).chunked(2).map { (first, last) -> first == 0 && last == 1 }.take(lengthOfForever).indexOfFirst { !it }
            if (seq == -1) {
                return a
            }
        }
        error("should not happen")
    }

    fun partTwo(list: List<String>): Int {
        return 2
    }
}
