package no.rodland.advent_2021

@Suppress("UNUSED_PARAMETER")
object Day01 {
    fun partOne(list: List<Int>): Int {
        return countIncreased(list)
    }

    private fun countIncreased(list: List<Int>) = list.zipWithNext().count { it.second > it.first }

    fun partTwo(list: List<Int>): Int {
        return countIncreased(list.windowed(3).map { it.sum() })
    }
}
