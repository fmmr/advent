import java.util.ArrayDeque
import java.util.Deque
import kotlin.math.absoluteValue

object Day9 {
    fun partOne(players: Int, worth: Int): Long? {
        val game = Game(players, worth)
        game.playGame()
        game.scores.toList().println()
        return game.scores.maxOrNull()
    }

    fun partTwo(players: Int, worth: Int): Long {
        // part 2 copied from: https://todd.ginsberg.com/post/advent-of-code/2018/day9/
        return play(players, worth)
    }

    // part 2 copied from: https://todd.ginsberg.com/post/advent-of-code/2018/day9/
    private fun play(numPlayers: Int, highest: Int): Long {
        val scores = LongArray(numPlayers)
        val marbles = ArrayDeque<Int>().also { it.add(0) }

        (1..highest).forEach { marble ->
            when {
                marble % 23 == 0 -> {
                    scores[marble % numPlayers] += marble + with(marbles) {
                        shift(-7)
                        removeFirst().toLong()
                    }
                    marbles.shift(1)
                }
                else -> {
                    with(marbles) {
                        shift(1)
                        addFirst(marble)
                    }
                }
            }
        }
        return scores.maxOrNull()!!
    }

    private fun <T> Deque<T>.shift(n: Int): Unit =
            when {
                n < 0 -> repeat(n.absoluteValue) {
                    addLast(removeFirst())
                }
                else -> repeat(n) {
                    addFirst(removeLast())
                }
            }


    class Game(private val numPlayers: Int, private val lastMarble: Int) {
        val game = mutableListOf<Long>(0)
        var currentPlayer = -1
        var currentMarblePos = 0
        val scores = LongArray(numPlayers) { 0 }

        fun playGame() {
            (1..lastMarble).map { placeMarble(it.toLong()) }
        }

        fun placeMarble(marble: Long) {
            currentPlayer = (marble % numPlayers).toInt()
            if (marble % 23 == 0L) {
                scores[currentPlayer] += marble
                val removeMarblePos = getIndex(currentMarblePos, -7)
                scores[currentPlayer] += game.removeAt(removeMarblePos)
                currentMarblePos = removeMarblePos
            } else {
                val newMarblePos = getIndexForAdd(currentMarblePos)
                game.add(newMarblePos, marble)
                currentMarblePos = newMarblePos
            }
        }

        fun getIndex(current: Int, relative: Int): Int {
            val next = current + relative
            if (relative < 0) {
                return (game.size + next) % game.size
            }
            return (next) % game.size
        }

        fun getIndexForAdd(current: Int): Int {
            return when (current) {
                game.size - 1 -> 1
                else -> current + 2
            }
        }
    }
}

