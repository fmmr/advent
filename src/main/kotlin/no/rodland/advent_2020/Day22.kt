package no.rodland.advent_2020

import java.util.*

// --- Day 22: Crab Combat ---

@Suppress("UNUSED_PARAMETER")
object Day22 {
    fun partOne(list: String): Int {
        val (p1, p2) = parseInput(list)
        play(p1, p2)
        var i = 1
        val winner = p1 + p2
        return winner.reversed().fold(0) { acc, card ->
            acc + (card * i++)
        }
    }

    private fun play(p1: ArrayDeque<Int>, p2: ArrayDeque<Int>) {
        while (p1.isNotEmpty() && p2.isNotEmpty()) {
            val card1 = p1.remove()
            val card2 = p2.remove()
            when {
                card1 > card2 -> {
                    p1.add(card1)
                    p1.add(card2)
                }
                card2 > card1 -> {
                    p2.add(card2)
                    p2.add(card1)
                }
                else -> {
                    error("Cards are equal - don't know what to do")
                }
            }
        }
    }

    fun partTwo(list: String): Int {
        val (p1, p2) = parseInput(list)
        return 2
    }

    private fun parseInput(str: String): Pair<ArrayDeque<Int>, ArrayDeque<Int>> {
        val splitted = str.split("\n\n")
        val player1 = splitted[0].split("\n").drop(1).map { it.toInt() }
        val player2 = splitted[1].split("\n").drop(1).map { it.toInt() }
        return ArrayDeque(player1) to ArrayDeque(player2)
    }
}
