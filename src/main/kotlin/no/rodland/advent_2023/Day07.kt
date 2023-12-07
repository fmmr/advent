package no.rodland.advent_2023

import no.rodland.advent.Day

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
        val selectors = listOf(Hand::getBestValue) + (0..4).map { idx -> { it: Hand -> it.cards[idx] } }
        return parsed
            .map { (cards, bid) -> Hand(cards.map { cardValue(it) }, findGroups(cards), bid) }
            .sortedWith(compareBy(*selectors.toTypedArray<(Hand) -> Int>()))
            .mapIndexed { index: Int, hand: Hand -> (index + 1) * hand.bid.toLong() }
            .sum()
    }


    data class Hand(val cards: List<Int>, val groups: List<Int>, val bid: Int) {
        fun getBestValue(): Int = when {
            groups.size == 1 -> 6
            groups[0] == 4 -> 5
            groups[0] == 3 && groups[1] == 2 -> 4
            groups[0] == 3 && groups[1] == 1 -> 3
            groups[0] == 2 && groups[1] == 2 -> 2
            groups[0] == 2 && groups[1] == 1 -> 1
            else -> 0
        }

//        private fun getBestString(groupValue: Int): String = when (groupValue) {
//            6 -> "five"
//            5 -> "four"
//            4 -> "house"
//            3 -> "three"
//            2 -> "twopair"
//            1 -> "pair"
//            else -> "high"
//        }
//
//        override fun toString(): String {
//            return cards.joinToString("") + " (" + getBestString(getBestValue()) + ") " + groups
//        }
    }

    override fun List<String>.parse(): List<Pair<List<Char>, Int>> {
        return map { line ->
            val (cards, bid) = line.split(' ')
            cards.toList() to bid.toInt()
        }
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

    override val day = "07".toInt()
}
