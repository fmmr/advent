package no.rodland.advent_2020

object Day10 {
    fun partOne(list: List<Int>): Int {
        val diffs = (listOf(0) + list).sorted().windowed(2).map { it[1] - it[0] }.groupBy { it }.map { it.key to it.value.size }.toMap()
        return diffs[1]!! * (diffs[3]!! + 1)
    }

    fun partTwo(list: List<Int>): Int {
        return 2
    }
}
