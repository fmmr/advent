package no.rodland.advent_2023

import no.rodland.advent.Day
import no.rodland.advent_2023.Day07.Hand.Companion.LABELS

// template generated: 07/12/2023
// Fredrik Rødland 2023

class Day07(val input: List<String>) : Day<Long, Long, List<Day07.Hand>> {

    private val parsed = input.parse()

    override fun partOne(): Long {
        // 248704822 too low
        return parsed.sortedWith(
                compareBy(
                    Hand::getBestValue,
                    { LABELS[it.cards[0]] },
                    { LABELS[it.cards[1]] },
                    { LABELS[it.cards[2]] },
                    { LABELS[it.cards[3]] },
                    { LABELS[it.cards[4]] })
            ).mapIndexed { index: Int, hand: Hand ->
                (index + 1) * hand.bid.toLong()
            }.sum()
    }

    override fun partTwo(): Long {
        return 2
    }

    data class Hand(val cards: List<Char>, val bid: Int) {
        private val five = cards.all { it == cards.first() }
        private val four = hasNumOfAKind(4, cards)
        private val house = hasNumOfAKind(3, cards) && hasNumOfAKind(2, cards)
        private val three = hasNumOfAKind(3, cards)
        private val twoPairs = cards.any { card ->
            cards.count { it == card } == 2 && hasNumOfAKind(2, cards.filterNot { it == card })
        }
        private val pair = hasNumOfAKind(2, cards)
        private fun hasNumOfAKind(num: Int, chars: List<Char> = cards) = chars.any { card -> chars.count { it == card } == num }


        override fun toString(): String {
            return cards.joinToString("") + " (" + getBestString() + ")"
        }

        fun getBestValue(): Int = when {
            five -> 6
            four -> 5
            house -> 4
            three -> 3
            twoPairs -> 2
            pair -> 1
            else -> 0
        }

        private fun getBestString(): String = when {
            five -> "five"
            four -> "four"
            house -> "house"
            three -> "three"
            twoPairs -> "twopair"
            pair -> "pair"
            else -> "high"
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
