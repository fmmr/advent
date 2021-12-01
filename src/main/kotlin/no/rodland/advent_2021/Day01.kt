package no.rodland.advent_2021

@Suppress("UNUSED_PARAMETER")
object Day01 {
    fun partOne(list: List<Int>): Int {
        return list.countIncreased()
    }

    private fun List<Int>.countIncreased() = zipWithNext().count { it.second > it.first }

    fun partTwo(list: List<Int>): Int {
        return list.windowed(3).map { it.sum() }.countIncreased()
    }
}
