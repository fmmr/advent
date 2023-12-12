package no.rodland.advent_2023

import no.rodland.advent.Day
import kotlin.math.pow

// template generated: 12/12/2023
// Fredrik Rødland 2023

class Day12(val input: List<String>) : Day<Int, Long, List<Pair<List<String>, List<Int>>>> {

    private val parsed = input.parse()

    override fun partOne(): Int {
        val hei = parsed.map { (candidates, ints) ->
            val map = candidates.map {
                it.split("\\.+".toRegex()).filterNot { it.isEmpty() }
            }
            val sameSize = map.filter { it.size == ints.size }
            val lengths = sameSize.map { strings ->
                strings.map { it.length }
            }
            val matches = lengths.count {
                it == ints
            }
            matches
        }
        return hei.sum()
    }

    override fun partTwo(): Long {
        return 2
    }

    data class Springs(val candidates: String, val lengths: List<Int>)

    fun expand(input: String): List<String> {
        val count = input.count { it == '?' }
        if (count == 0) {
            return listOf(input)
        }
        val numChars = 2.0.pow(count).toInt()
        val replacement = (0..(numChars - 1)).map { n ->
            val bin = n.toString(2).padStart(count, '0')
            bin.map { char ->
                if (char == '0') '.'
                else '#'
            }.joinToString("")
        }.map {
            var replaced = input
            for (char in it) {
                replaced = replaced.replaceFirst("?", char.toString())
            }
            replaced
        }
        return replacement
    }

    override fun List<String>.parse(): List<Pair<List<String>, List<Int>>> {

        return map { line ->
            val (first, second) = line.split(" ")
            val ints = second.split(",").map { it.toInt() }
            expand(first) to ints
        }
    }

    override val day = "12".toInt()
}
