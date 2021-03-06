package no.rodland.advent_2020

// --- Day 22: Crab Combat ---
object Day22 {
    fun partOne(list: String): Int {
        return play(list, false)
    }

    fun partTwo(list: String): Int {
        val play = play(list, true)
        return play
    }

    private fun play(list: String, recursive: Boolean): Int {
        val (list1, list2) = parseInput(list)
        val (p1, p2) = game(list1, list2, recursive)
        return (p1 + p2).score()
    }

    private fun game(p1List: List<Int>, p2List: List<Int>, recursive: Boolean): Pair<ArrayDeque<Int>, ArrayDeque<Int>> {
        val previousGames = mutableSetOf<Pair<ArrayDeque<Int>, ArrayDeque<Int>>>()
        val p1 = ArrayDeque(p1List)
        val p2 = ArrayDeque(p2List)
        while (p1.isNotEmpty() && p2.isNotEmpty()) {
            if (!previousGames.add(p1 to p2)) {
                return p1 to ArrayDeque()
            }
            val card1 = p1.removeFirst()
            val card2 = p2.removeFirst()
            val player1Wins = if (recursive && (p1.size) >= card1 && (p2.size) >= card2) {
                game(p1.take(card1), p2.take(card2), true).second.isEmpty()
            } else {
                card1 > card2
            }

            if (player1Wins) {
                p1.add(card1)
                p1.add(card2)
            } else {
                p2.add(card2)
                p2.add(card1)
            }
        }
        return p1 to p2
    }

    private fun parseInput(str: String): Pair<List<Int>, List<Int>> {
        val splitted = str.split("\n\n")
        val player1 = splitted[0].split("\n").drop(1).map { it.toInt() }
        val player2 = splitted[1].split("\n").drop(1).map { it.toInt() }
        return player1 to player2
    }

    private fun List<Int>.score() = foldIndexed(0) { idx, acc, card -> acc + card * (size - idx) }
}
