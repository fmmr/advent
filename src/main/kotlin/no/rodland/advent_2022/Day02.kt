package no.rodland.advent_2022

import no.rodland.advent_2022.Day02.Hand.PAPER
import no.rodland.advent_2022.Day02.Hand.ROCK
import no.rodland.advent_2022.Day02.Hand.SCISSOR

// template generated: 28/11/2022
// Fredrik Rødland 2022

object Day02 {
    fun partOne(list: List<String>): Int {
        return list.playAllGames { c, _ -> c.toHand() }
    }

    fun partTwo(list: List<String>): Int {
        return list.playAllGames { c, elf -> c.fromOutcome(elf) }
    }

    private fun List<String>.playAllGames(resolveXYZ: (Char, Hand) -> Hand) =
        map { str ->
            val line = str.split(" ")
            val elfGame = line.first().first().toHand()
            val playerGame = resolveXYZ(line.last().first(), elfGame)
            elfGame to playerGame

        }.sumOf { round(it.first, it.second) + it.second.points }

    private fun Char.fromOutcome(elf: Hand) = when (this) {
        'X' -> elf.win()
        'Y' -> elf
        'Z' -> elf.lose()
        else -> error("unknown char ${this}")
    }

    private fun Char.toHand(): Hand = when (this) {
        'A', 'X' -> ROCK
        'B', 'Y' -> PAPER
        'C', 'Z' -> SCISSOR
        else -> error("unknown char ${this@Day02}")
    }

    private fun round(elf: Hand, player: Hand): Int = when (elf) {
        player -> 3
        player.lose() -> 0
        player.win() -> 6
        else -> error("should not happen $elf $player")
    }

    enum class Hand(val points: Int) {
        ROCK(1), PAPER(2), SCISSOR(3);

        fun win(): Hand = when (this) {
            ROCK -> SCISSOR
            PAPER -> ROCK
            SCISSOR -> PAPER
        }

        fun lose(): Hand = when (this) {
            ROCK -> PAPER
            PAPER -> SCISSOR
            SCISSOR -> ROCK
        }
    }
}
