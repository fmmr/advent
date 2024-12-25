package no.rodland.advent_2024

import no.rodland.advent.Day

// template generated: 25/12/2024
// Fredrik Rødland 2024

class Day25(val input: List<String>) : Day<Long, Long, List<String>> {

    private val parsed = input.parse()

    override fun partOne(): Long {
        return 2
    }

    override fun partTwo(): Long {
        return 2
    }

    override fun List<String>.parse(): List<String> {

        val (locks, keys) = joinToString("\n").split("\n\n").partition { it.startsWith("#####") }
        return map { line ->
            line
        }
    }
    sealed interface Thing
    data class Lock(val init: String): Thing {

    }
    data class Key(val init: String): Thing {

    }

    override val day = "25".toInt()
}
