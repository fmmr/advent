package no.rodland.advent_2020

import java.util.*

// --- Day 22: Crab Combat ---

@Suppress("UNUSED_PARAMETER")
object Day22 {
    fun partOne(list: String): Int {
        val (list1, list2) = parseInput(list)
        val (p1, p2) = play(list1, list2)
        var i = 1
        return (p1 + p2).reversed().fold(0) { acc, card -> acc + (card * i++) }
    }

    fun partTwo(list: String): Int {
        val (list1, list2) = parseInput(list)
        val (p1, p2) = playRec(list1, list2)
        var i = 1
        return (p1 + p2).reversed().fold(0) { acc, card -> acc + (card * i++) }
    }

    private fun playRec(p1List: List<Int>, p2List: List<Int>): Pair<ArrayDeque<Int>, ArrayDeque<Int>> {
        val previousGames1 = mutableSetOf<List<Int>>()
        val previousGames2 = mutableSetOf<List<Int>>()
        val p1 = ArrayDeque(p1List)
        val p2 = ArrayDeque(p2List)
        while (p1.isNotEmpty() && p2.isNotEmpty()) {
            if (!previousGames1.add(p1.toList()) || !previousGames2.add(p2.toList())) {
                return p1 to ArrayDeque()
            }
            val card1 = p1.remove()
            val card2 = p2.remove()
            if ((p1.size) >= card1 && (p2.size) >= card2) {
                val (subgame1, subgame2) = playRec(p1.take(card1), p2.take(card2))
                when {
                    subgame2.isEmpty() -> p1.addAll(listOf(card1, card2))
                    subgame1.isEmpty() -> p2.addAll(listOf(card2, card1))
                }
            } else {
                when {
                    card1 > card2 -> p1.addAll(listOf(card1, card2))
                    card2 > card1 -> p2.addAll(listOf(card2, card1))
                }
            }
        }
        return p1 to p2
    }

    private fun play(p1List: List<Int>, p2List: List<Int>): Pair<ArrayDeque<Int>, ArrayDeque<Int>> {
        val p1 = ArrayDeque(p1List)
        val p2 = ArrayDeque(p2List)
        while (p1.isNotEmpty() && p2.isNotEmpty()) {
            val card1 = p1.remove()
            val card2 = p2.remove()
            when {
                card1 > card2 -> p1.addAll(listOf(card1, card2))
                card2 > card1 -> p2.addAll(listOf(card2, card1))
            }
        }
        return p1 to p2
    }

    private fun parseInput(str: String): Pair<List<Int>, List<Int>> {
        val splitted = str.split("\n\n")
        val player1 = splitted[0].split("\n").drop(1).map { it.toInt() }
        val player2 = splitted[1].split("\n").drop(1).map { it.toInt() }
        return player1 to player2
    }
}
