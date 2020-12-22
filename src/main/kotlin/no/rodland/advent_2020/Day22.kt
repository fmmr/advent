package no.rodland.advent_2020

// --- Day 22: Crab Combat ---

@Suppress("UNUSED_PARAMETER")
object Day22 {
    fun partOne(list: String): Int {
        val (p1, p2) = parseInput(list)
        return 2
    }

    fun partTwo(list: String): Int {
        val (p1, p2) = parseInput(list)
        return 2
    }

    private fun parseInput(str: String): Pair<List<Int>, List<Int>> {
        val splitted = str.split("\n\n")
        val player1 = splitted[0].split("\n").drop(1).map { it.toInt() }
        val player2 = splitted[1].split("\n").drop(1).map { it.toInt() }
        return player1 to player2
    }
}
