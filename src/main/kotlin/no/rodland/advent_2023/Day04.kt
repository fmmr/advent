package no.rodland.advent_2023

import no.rodland.advent.Day
import kotlin.math.pow

// template generated: 04/12/2023
// Fredrik Rødland 2023

class Day04(val input: List<String>) : Day<Long, Long, List<Day04.Game>> {

    private val parsed = input.parse()

    override fun partOne(): Long {
        return parsed.sumOf { it.points() }
    }

    override fun partTwo(): Long {
        return 2
    }

    data class Game(val id: Int, val winning: Set<Int>, val drawn: List<Int>) {
        fun points(): Long {
            val numberWinning = drawn.intersect(winning).size
            return if (numberWinning == 0) {
                0
            } else {
                2.toDouble().pow((numberWinning - 1).toDouble()).toLong()
            }
        }
    }

    override fun List<String>.parse(): List<Game> {
        return map { line ->
            // Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
            val id = line.substringAfter("Card").substringBefore(":").trim().toInt()
            val winning = line.substringAfter(":").substringBefore("|").split(" +".toRegex()).filterNot { it.isBlank() }.map { it.trim().toInt() }.toSet()
            val drawn = line.substringAfter("|").split(" +".toRegex()).filterNot { it.isBlank() }.map { it.trim().toInt() }
            Game(id, winning, drawn)
        }
    }

    override val day = "04".toInt()
}
