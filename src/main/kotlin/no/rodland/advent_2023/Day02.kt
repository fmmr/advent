package no.rodland.advent_2023

import no.rodland.advent_2023.Day02.Colour.*
import product
import kotlin.math.max

// template generated: 01/12/2023
// Fredrik Rødland 2023

class Day02(val input: List<String>) {

    private val parsed = input.parse()
    private val candidate = Pick(12, 13, 14)

    fun partOne(): Int = parsed.filter { it.possible(candidate) }.sumOf { it.id }

    fun partTwo(): Int = parsed.map { it.max }.sumOf { it.power() }

    fun List<String>.parse(): List<Game> {
        return map { line ->
            val (head, tail) = line.split(": ")
            val id = head.substringAfter(" ").toInt()
            val picks = tail.split("; ").map { pick ->
                val colours = pick
                    .split(", ")
                    .associate {
                        Colour.valueOf(it.substringAfter(" ")) to it.substringBefore(" ").toInt()
                    }
                Pick(colours.getOrDefault(red, 0), colours.getOrDefault(green, 0), colours.getOrDefault(blue, 0))
            }
            Game(id, picks)
        }
    }


    data class Game(val id: Int, val picks: List<Pick>) {
        val max = picks.fold(Pick(0, 0, 0)) { acc, cubes ->
            Pick(max(acc.red, cubes.red), max(acc.green, cubes.green), max(acc.blue, cubes.blue))
        }

        fun possible(candidate: Pick): Boolean = candidate.red >= max.red && candidate.green >= max.green && candidate.blue >= max.blue
    }

    data class Pick(val red: Int, val green: Int, val blue: Int) {
        fun power() = listOf(red, green, blue).product().toInt()
    }

    @Suppress("EnumEntryName")
    enum class Colour { red, green, blue }
}

