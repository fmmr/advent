package no.rodland.advent_2023

import no.rodland.advent.Day
import kotlin.math.pow

// template generated: 07/12/2023
// Fredrik Rødland 2023

class Day07(val input: List<String>) : Day<Long, Long, List<Pair<List<Char>, Int>>> {

    private val parsed = input.parse()

    override fun partOne(): Long {
        val findGroups: (List<Char>) -> List<Int> = { hand ->
            hand.groupBy { it }.map { it.value.size }.sortedByDescending { it }
        }
        val cardValue: (Char) -> Int = { LABELS[it]!! }
        return score(findGroups, cardValue)
    }

    override fun partTwo(): Long {
        val findGroups: (List<Char>) -> List<Int> = { hand ->
            val groupsWithoutJoker = hand.filterNot { it == 'J' }.groupBy { it }.map { it.value.size }.sortedByDescending { it }
            val numJokers = hand.count { it == 'J' }
            val groups = if (numJokers == 5) {
                listOf(5)
            } else {
                listOf(groupsWithoutJoker[0] + numJokers) + groupsWithoutJoker.drop(1)
            }
            groups
        }
        val cardValue: (Char) -> Int = { if (it == 'J') 1 else LABELS[it]!! }
        return score(findGroups, cardValue)
    }

    private fun score(findGroups: (List<Char>) -> List<Int>, cardValue: (Char) -> Int): Long {
        return parsed
            .asSequence()
            .map { (cards, bid) -> Hand(cards.map { cardValue(it) }, findGroups(cards), bid) }
            .sortedBy(Hand::sortValue)
            .mapIndexed { index: Int, hand: Hand -> (index + 1) * hand.bid.toLong() }
            .sum()
    }

    data class Hand(val cards: List<Int>, val groups: List<Int>, val bid: Int) {
        private val typeValue = groups[0] * 10 + (groups.getOrNull(1) ?: 0) // 50, 41, 32, 31, 22, 21, 11
        private val cardValue = (0..4).sumOf { cards[it] * POWERS[it]!! }  // [0]*14^5 + [1]*14^4 ...
        val sortValue = typeValue * TYPE_FACTOR + cardValue
    }

    override fun List<String>.parse(): List<Pair<List<Char>, Int>> {
        return map { line ->
            val (cards, bid) = line.split(' ')
            cards.toList() to bid.toInt()
        }
    }

    companion object {
        val LABELS = mapOf('A' to 14, 'K' to 13, 'Q' to 12, 'J' to 11, 'T' to 10) + (2..9).map { it.digitToChar() to it }
        private val MAX = LABELS.values.max().toDouble()
        val POWERS = (0..4).associateWith { idx -> (MAX.pow((5 - idx))).toInt() }
        val TYPE_FACTOR = MAX.pow(6)
    }

    override val day = "07".toInt()
}
