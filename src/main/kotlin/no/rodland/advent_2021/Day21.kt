package no.rodland.advent_2021

import java.lang.Integer.max

typealias Dice = Triple<Int, Int, Int>
typealias Answer = Pair<Long, Long>
typealias Players = Pair<Day21.Player, Day21.Player>

@Suppress("UNUSED_PARAMETER")
object Day21 {

    fun partOne(startingPositions: Pair<Int, Int>): Int {
        val player1 = Player(startingPositions.first - 1, 0)
        val player2 = Player(startingPositions.second - 1, 0)
        val game = TossedGame(player1, player2)

        val endGame = generateSequence(Dice(1, 2, 3)) { Dice(it.third + 1, it.third + 2, it.third + 3) }
            .chunked(2)
            .runningFold(game) { g, dice -> g.turn(dice.first() to dice.last()) }
            .drop(1)
            .first { max(it.player1.score, it.player2.score) >= 1000 }

        return if (endGame.player1.score >= 1000) {
            (endGame.tossed - 3) * (endGame.player2.score - endGame.player2.position)
        } else {
            endGame.tossed * endGame.player1.score
        }
    }

    fun partTwo(startingPositions: Pair<Int, Int>): Long {
        val player1 = Player(startingPositions.first - 1, 0)
        val player2 = Player(startingPositions.second - 1, 0)

        val answer = countGames(player1, player2)
        return java.lang.Long.max(answer.first, answer.second)
    }

    // implemented DP after watching Jonathan Paulson's solution: https://www.youtube.com/watch?v=a6ZdJEntKkk&t=1226s 
    private fun countGames(p1: Player, p2: Player, cache: MutableMap<Players, Answer> = mutableMapOf()): Answer {
        if (p1.score >= 21) {
            return 1L to 0L
        }
        if (p2.score >= 21) {
            return 0L to 1L
        }
        val cached = cache[p1 to p2]
        if (cached != null) {
            return cached
        }

        val ans = (1..3).fold(0L to 0L) { a1, x ->
            (1..3).fold(a1) { a2, y ->
                (1..3).fold(a2) { a3, z ->
                    val newp1 = p1.turn(Dice(x, y, z))
                    val (p2wins, p1wins) = countGames(p2, newp1, cache)
                    a3.first + p1wins to a3.second + p2wins
                }
            }
        }
        cache[Players(p1, p2)] = ans
        return ans
    }


    data class TossedGame(val players: Players, val tossed: Int = 0) {
        val player1 = players.first
        val player2 = players.second

        constructor(p1: Player, p2: Player) : this(Players(p1, p2))

        fun turn(dice: Pair<Dice, Dice>) = TossedGame(Players(player1.turn(dice.first), player2.turn(dice.second)), tossed + 6)
    }

    data class Player(private val idx: Int, val score: Int = 0, val position: Int = idx + 1) {
        fun turn(dice: Dice) = ((dice.first + dice.second + dice.third + idx) % 10).let { Player(it, score + it + 1) }
    }
}
