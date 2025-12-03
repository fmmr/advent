package no.rodland.advent_2025

import no.rodland.advent.Day

// template generated: 03/12/2025
// Fredrik Rødland 2025

class Day03(val input: List<String>) : Day<Long, Long, List<String>> {

    private val parsed = input.parse()

    override fun partOne(): Long {
        return parsed
            .asSequence()
            .map { it.getMax() }
            .sum()
            .toLong()
    }

    private fun String.getMax(): Int {
        (9 downTo 1).forEach { first ->
            val idx = indexOf(first.toString())
            if (idx > -1 && idx != lastIndex) {
                val second = substring(idx + 1).maxBy { it.digitToInt() }
                return (first.toString() + second.digitToInt()).toInt()
            }
        }
        return -1
    }

    override fun partTwo(): Long {
        return 2
    }

    override fun List<String>.parse(): List<String> = this

    override val day = "03".toInt()
}

