package no.rodland.advent_2023

import no.rodland.advent_2023.Day02.Colour.*
import kotlin.math.max

// template generated: 01/12/2023
// Fredrik Rødland 2023

@Suppress("unused")
class Day02(val input: List<String>) {

    private val parsed = input.parse()
    private val candidate = Pick(12, 13, 14)

    fun partOne(): Int = parsed.filter { it.possible(candidate) }.sumOf { it.id }

    fun partTwo(): Int = parsed.map { it.max }.sumOf { it.red * it.green * it.blue }

    fun List<String>.parse(): List<Game> {
        val map = map { line ->
            val (f, s) = line.split(":")
            val id = f.substringAfter(" ").toInt()
            val picks = s.split(";").map { g ->
                val cubes = g.split(", ")
                val colours = cubes.associate {
                    val (number, colour) = it.trim().split(" ")
                    Colour.from(colour) to number.toInt()
                }
                Pick(colours.getOrDefault(RED, 0), colours.getOrDefault(GREEN, 0), colours.getOrDefault(BLUE, 0))
            }
            Game(id, picks)
        }
        return map
    }


    data class Game(val id: Int, val picks: List<Pick>) {
        val max = picks.fold(Pick(0, 0, 0)) { acc, cubes ->
            Pick(max(acc.red, cubes.red), max(acc.green, cubes.green), max(acc.blue, cubes.blue))
        }

        fun possible(candidate: Pick): Boolean = candidate.red >= max.red && candidate.green >= max.green && candidate.blue >= max.blue
    }

    data class Pick(val red: Int, val green: Int, val blue: Int)


    enum class Colour {
        RED, GREEN, BLUE;

        companion object {
            fun from(s: String): Colour {
                return when (s) {
                    "blue" -> BLUE
                    "red" -> RED
                    "green" -> GREEN
                    else -> error("unknown col: s")
                }
            }
        }
    }
}

