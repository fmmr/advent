package no.rodland.advent_2021

import java.lang.Integer.max

typealias Dice = Triple<Int, Int, Int>

@Suppress("UNUSED_PARAMETER")
object Day21 {

    fun partOne(startingPositions: Pair<Int, Int>): Int {
        val player1 = Player(startingPositions.first - 1, 0)
        val player2 = Player(startingPositions.second - 1, 0)
        val game = Game(player1, player2, 0)

        return generateSequence(Dice(1, 2, 3)) { Dice(it.third + 1, it.third + 2, it.third + 3) }
            .chunked(2)
            .runningFold(game) { g, dice -> g.turn(dice.first() to dice.last()) }
            .drop(1)
            .first { it.finished }
            .finalNumber()
    }

    fun partTwo(startingPositions: Pair<Int, Int>): Int {
        val (player1, player2) = startingPositions
        return 2
    }

    data class Game(val player1: Player, val player2: Player, val tossed: Int) {
        fun turn(dice: Pair<Dice, Dice>) = Game(player1.turn(dice.first), player2.turn(dice.second), tossed + 6)
        val finished = max(player1.score, player2.score) >= 1000

        fun finalNumber(): Int {
            return if (player1.score >= 1000) {
                (tossed - 3) * (player2.score - player2.position)
            } else {
                tossed * player1.score
            }
        }
    }

    data class Player(private val idx: Int, val score: Int = 0, val position: Int = idx + 1) {
        fun turn(dice: Dice): Player {
            val newidx = (dice.first + dice.second + dice.third + idx) % 10
            val newScore = score + newidx + 1
            return Player(newidx, newScore)
        }
    }

}
