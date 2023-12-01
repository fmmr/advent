package no.rodland.advent_2023

import kotlin.Int.Companion.MAX_VALUE

// template generated: 01/12/2023
// Fredrik Rødland 2023

@Suppress("MemberVisibilityCanBePrivate")
class Day01(val input: List<String>) {

    fun partOne(): Long {
        return input.sumOf { line -> findNumbers(line) { digit -> listOf(digit) } }.toLong()
    }

    fun partTwo(): Long {
        return input.sumOf { line -> findNumbers(line) { digit -> listOf(digit, DIGIT_MAPPING[digit]!!) } }.toLong()
    }

    fun findNumbers(line: String, listFunc: (String) -> List<String>): Int {
        val map = DIGIT_MAPPING
            .keys
            .map { d -> DigitPos(d, listFunc(d).minOf { line.indexOfMaxDefault(it) }, listFunc(d).maxOf { line.lastIndexOf(it) }) }
        val first = map.minBy { it.firstOcc }.digit
        val last = map.maxBy { it.lastOcc }.digit
        return "$first$last".toInt()
    }

    data class DigitPos(val digit: String, val firstOcc: Int, val lastOcc: Int)

    fun String.indexOfMaxDefault(sub: String) = if (contains(sub)) indexOf(sub) else MAX_VALUE

    companion object {
        private val DIGIT_MAPPING = mapOf(
            "1" to "one",
            "2" to "two",
            "3" to "three",
            "4" to "four",
            "5" to "five",
            "6" to "six",
            "7" to "seven",
            "8" to "eight",
            "9" to "nine",
        )
    }
}
