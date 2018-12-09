object Day9 {
    fun partOne(players: Int, worth: Int): Long? {
        val game = Game(players, worth)
        game.playGame()
        game.scores.toList().println()
        return game.scores.max()
    }

    fun partTwo(players: Int, worth: Int): Long? {
        return partOne(players, worth)
    }

    class Game(val numPlayers: Int, val lastMarble: Int) {
        val game = mutableListOf<Long>(0, 8, 4, 9, 2, 5, 1, 6, 3, 7)
        var currentPlayer = -1
        var currentMarblePos = 3
        val scores = LongArray(numPlayers) { 0 }


        fun playGame() {
            (10..lastMarble).map { placeMarble(it.toLong()) }.toList()
        }

        fun placeMarble(marble: Long) {
            currentPlayer = nextPlayer(currentPlayer)
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

        private fun nextPlayer(previous: Int): Int {
            return if (previous == numPlayers - 1) {
                0
            } else {
                previous + 1
            }
        }
    }
}

