package no.rodland.advent_2023

import kotlin.Int.Companion.MAX_VALUE

// template generated: 01/12/2023
// Fredrik Rødland 2023

@Suppress("MemberVisibilityCanBePrivate")
class Day01(val input: List<String>) {

    fun partOne(): Long {
        return input.sumOf { line -> calibrationValue(line) { digit -> listOf(digit.toString()) } }.toLong()
    }

    fun partTwo(): Long {
        return input.sumOf { line -> calibrationValue(line) { digit -> listOf(digit.toString(), DIGIT_MAPPING[digit]!!) } }.toLong()
    }

    fun calibrationValue(line: String, listFunc: (Int) -> List<String>): Int {
        val map = DIGIT_MAPPING.keys.map { d -> DigitPos(d, line.indexOfMaxDefault(listFunc(d)), line.lastIndexOfAny(listFunc(d))) }
        val first = map.minBy { it.firstOcc }.digit
        val last = map.maxBy { it.lastOcc }.digit
        return first * 10 + last
    }

    data class DigitPos(val digit: Int, val firstOcc: Int, val lastOcc: Int)

    fun String.indexOfMaxDefault(sub: List<String>) = indexOfAny(sub).let {
        when (it) {
            -1 -> MAX_VALUE
            else -> it
        }
    }

    companion object {
        private val DIGIT_MAPPING = mapOf(
            1 to "one",
            2 to "two",
            3 to "three",
            4 to "four",
            5 to "five",
            6 to "six",
            7 to "seven",
            8 to "eight",
            9 to "nine",
        )
    }
}
