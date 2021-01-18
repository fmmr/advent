package no.rodland.advent_2016

@Suppress("UNUSED_PARAMETER")
object Day18 {
    fun partOne(str: String): Int {
        return (1 until 40)
                .runningFold(str) { acc, _ -> getNextRow(acc) }
                .map { s -> s.count { it == '.' } }
                .sum()
    }

    private fun getNextRow(str: String): String {
        return (".$str.").windowed(3, 1, false).map { if (it[0] == it[2]) '.' else '^' }.joinToString("")
    }

    fun partTwo(str: String): Int {
        return (1 until 400000)
                .runningFold(str) { acc, _ -> getNextRow(acc) }
                .map { s -> s.count { it == '.' } }
                .sum()
    }
}
