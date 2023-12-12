package no.rodland.advent_2023

import no.rodland.advent.Day

// template generated: 12/12/2023
// Fredrik Rødland 2023

class Day12(val input: List<String>) : Day<Int, Long, List<Pair<String, List<Int>>>> {

    private val parsed = input.parse()

    override fun partOne(): Int {
        return parsed.sumOf { (candidates, numbers) ->
            expand(candidates)
                .map { candidate -> candidate.split("\\.+".toRegex()).filterNot { it.isEmpty() } }
                .filter { it.size == numbers.size }
                .map { strings -> strings.map { it.length } }
                .count { it == numbers }
        }
    }

    override fun partTwo(): Long {
        return 2
    }


    fun expand(input: String): List<String> {
        fun String.replaceBoolean(): List<Char> = map { char ->
            if (char == '0') '.'
            else '#'
        }

        val count = input.count { it == '?' }
        val numChars = 1 shl count  // 2^count
        return (0..<numChars).map { boolean ->
            boolean.toString(2).padStart(count, '0').replaceBoolean()
        }.map { mask ->
            mask.fold(input) { acc: String, c: Char -> acc.replaceFirst("?", c.toString()) }
        }
    }

    override fun List<String>.parse(): List<Pair<String, List<Int>>> {
        return map { line ->
            val (first, second) = line.split(" ")
            first to second.split(",").map { it.toInt() }
        }
    }

    override val day = "12".toInt()
}
