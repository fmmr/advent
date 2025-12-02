package no.rodland.advent_2025

import no.rodland.advent.Day

// template generated: 02/12/2025
// Fredrik Rødland 2025

class Day02(val input: List<String>) : Day<Long, Long, List<LongRange>> {

    private val parsed = input.first().split(",").parse()

    override fun partOne(): Long {
        return parsed.flatMap { h -> h.filter { it.invalid() } }.sum()
    }

    override fun partTwo(): Long {
        return parsed.flatMap { h -> h.filter { it.invalid2() } }.sum()
    }

    override fun List<String>.parse(): List<LongRange> {
        return map { line ->
            line.substringBefore("-").toLong()..line.substringAfter("-").toLong()
        }
    }

    fun Long.invalid(): Boolean {
        val str = toString()
        val lng = str.length / 2
        return str.take(lng) == str.drop(lng)
    }


    fun Long.invalid2(): Boolean {
        val str = toString()
        val lng = str.length / 2
        (1..lng).forEach { if (str.isRepeating(str.take(it))) return true }
        return false
    }

    private fun String.isRepeating(sub: String): Boolean {
        return replace(sub, "").isBlank()
    }


    override val day = "02".toInt()
}
