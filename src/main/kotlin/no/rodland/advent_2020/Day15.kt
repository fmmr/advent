package no.rodland.advent_2020

object Day15 {
    fun partOne(game: List<Int>): Int {
        return game.valueAt(2020)
    }

    fun partTwo(game: List<Int>): Int {
        return game.valueAt(30000000)
    }

    private fun List<Int>.valueAt(position: Int): Int {
        val gameMap = mutableMapOf<Int, Int>()
        val listSeq = subList(0, size - 1)
            .mapIndexed() { index: Int, i: Int -> index + 1 to i }
            .onEach { gameMap[it.second] = it.first }
            .asSequence()
        val gameSeq = generateSequence((size) to last()) { (turn, num) ->
            val nextNum = if (gameMap[num] != null) turn - gameMap[num]!! else 0
            gameMap[num] = turn
            turn + 1 to nextNum
        }
        return (listSeq + gameSeq).take(position).last().second
    }
}
