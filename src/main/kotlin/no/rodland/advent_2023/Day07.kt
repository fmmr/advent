package no.rodland.advent_2023

import no.rodland.advent.Day

// template generated: 07/12/2023
// Fredrik Rødland 2023

class Day07(val input: List<String>) : Day<Long, Long, List<Day07.Hand>> {

    private val parsed = input.parse()

    override fun partOne(): Long {
        val selectors = listOf(Hand::getBestValue1) + (0..4).map { idx -> { it: Hand -> it.cardValues1[idx] } }
        return score(selectors)
    }

    override fun partTwo(): Long {
        val selectors = listOf(Hand::getBestValue2) + (0..4).map { idx -> { it: Hand -> it.cardValues2[idx] } }
        return score(selectors)
    }

    private fun score(selectors: List<(Hand) -> Int>): Long {
        return parsed
            .sortedWith(compareBy(*selectors.toTypedArray()))
            .mapIndexed { index: Int, hand: Hand -> (index + 1) * hand.bid.toLong() }
            .sum()
    }

    data class Hand(val cards: List<Char>, val bid: Int) {
        val groups = cards.groupBy { it }.map { it.value.size }.sortedByDescending { it }
        val groupsWithoutJoker = cards.filterNot { it == 'J' }.groupBy { it }.map { it.value.size }.sortedByDescending { it }
        val numJokers = cards.count { it == 'J' }
        val groupsPart2 = if (numJokers == 5) {
            listOf(5)
        } else {
            listOf(groupsWithoutJoker[0] + numJokers) + groupsWithoutJoker.drop(1)
        }
        val cardValues1 = cards.map { LABELS[it]!! }
        val cardValues2 = cards.map { if (it == 'J') 1 else LABELS[it]!! }

        fun getBestValue2(): Int = getBestValue(groupsPart2)

        fun getBestValue1(): Int = getBestValue(groups)

        private fun getBestValue(checkGroups: List<Int>): Int = when {
            checkGroups.size == 1 -> 6
            checkGroups[0] == 4 -> 5
            checkGroups[0] == 3 && checkGroups[1] == 2 -> 4
            checkGroups[0] == 3 && checkGroups[1] == 1 -> 3
            checkGroups[0] == 2 && checkGroups[1] == 2 -> 2
            checkGroups[0] == 2 && checkGroups[1] == 1 -> 1
            else -> 0
        }

        private fun getBestString(groupValue: Int): String = when (groupValue) {
            6 -> "five"
            5 -> "four"
            4 -> "house"
            3 -> "three"
            2 -> "twopair"
            1 -> "pair"
            else -> "high"
        }

        override fun toString(): String {
            return cards.joinToString("") + " (1: " + getBestString(getBestValue(groups)) + ", 2: " + getBestString(getBestValue(groupsPart2)) + ") " + groups
        }

        companion object {
            val LABELS = mapOf(
                'A' to 14,
                'K' to 13,
                'Q' to 12,
                'J' to 11,
                'T' to 10,
            ) + (2..9).map { it.digitToChar() to it }
        }
    }

    override fun List<String>.parse(): List<Hand> {
        return map { line ->
            val (cards, bid) = line.split(' ')
            Hand(cards.toList(), bid.toInt())
        }
    }


    override val day = "07".toInt()
}
