package no.rodland.advent_2024

import no.rodland.advent.Day

// template generated: 19/12/2024
// Fredrik Rødland 2024

class Day19(val input: List<String>) : Day<Int, Long, Pair<List<String>, List<String>>> {

    private val parsed = input.parse()
    private val towels = parsed.first
    private val designs = parsed.second

    override fun partOne(): Int {
        return designs.count { matches(it) > 0 }
    }

    override fun partTwo(): Long {
        return designs.sumOf { matches(it) }
    }

    fun matches(str: String, matches: List<String> = emptyList(), cache: MutableMap<String, Long> = mutableMapOf()): Long {
        if (str == "") {
            return 1
        }
        return cache.getOrPut(str) {
            towels
                .filter { str.startsWith(it) }
                .sumOf { matches(str.substringAfter(it), matches + it, cache) }
        }
    }

    override fun List<String>.parse(): Pair<List<String>, List<String>> {
        val (towel, design) = joinToString("\n").split("\n\n")
        val towels = towel.split(", ")
        val designs = design.split("\n")
        return towels to designs
    }

    override val day = "19".toInt()
}
