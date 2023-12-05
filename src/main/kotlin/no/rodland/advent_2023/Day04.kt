package no.rodland.advent_2023

import no.rodland.advent.Day

// template generated: 04/12/2023
// Fredrik Rødland 2023

class Day04(val input: List<String>) : Day<Int, Long, List<Day04.Game>> {

    private val parsed = input.parse()

    override fun partOne(): Int {
        return parsed.sumOf { it.points() }
    }

    override fun partTwo(): Long {
        return partTwoWithMutableList()
    }

    @Suppress("unused")
    private fun partTwoWithArray(): Long {
        val mutableList = parsed.toTypedArray()
        mutableList.forEach { game ->
            val endIndex = game.id + game.numberWinning
            (game.id..<endIndex).forEach { i ->
                mutableList[i] = mutableList[i].copy(numCards = mutableList[i].numCards + game.numCards)
            }
        }
        return mutableList.sumOf { it.numCards }
    }

    private fun partTwoWithMutableList(): Long {
        val mutableList = parsed.toMutableList()
        mutableList.forEach { game ->
            val endIndex = game.id + game.numberWinning
            (game.id..<endIndex).forEach { i ->
                mutableList[i] = mutableList[i].copy(numCards = mutableList[i].numCards + game.numCards)
            }
        }
        return mutableList.sumOf { it.numCards }
    }

    @Suppress("unused")
    private fun partTwoWithFold(): Long {
        val end = parsed.fold(parsed) { acc: List<Game>, game: Game ->
            val done = acc.subList(0, game.id)
            val mustFix = acc.subList(game.id, acc.size)
            val updatedGame = done.last()
            val update = mustFix.subList(0, game.numberWinning).map { it.copy(numCards = it.numCards + updatedGame.numCards) }
            val doNotUpdate = mustFix.subList(game.numberWinning, mustFix.size)
            done + update + doNotUpdate
        }
        return end.sumOf { it.numCards }
    }

    data class Game(val id: Int, val winning: Set<Int>, val drawn: List<Int>, val numCards: Long = 1L) {
        val numberWinning = drawn.intersect(winning).size
        fun points(): Int = (1 shl (numberWinning - 1)) // 2.0.pow(numberWinning - 1)
    }

    override fun List<String>.parse(): List<Game> {
        return map { line ->
            // Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
            val id = line.substringAfter("Card").substringBefore(":").trim().toInt()
            val winning = line.substringAfter(":").substringBefore("|").split(" ").filterNot { it.isBlank() }.map { it.trim().toInt() }.toSet()
            val drawn = line.substringAfter("|").split(" ").filterNot { it.isBlank() }.map { it.trim().toInt() }
            Game(id, winning, drawn)
        }
    }

    override val day = "04".toInt()
}
