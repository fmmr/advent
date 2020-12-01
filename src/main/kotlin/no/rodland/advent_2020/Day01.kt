package no.rodland.advent_2020

object Day01 {
    fun partOne(expences: List<Int>): Int = findTarget(expences, 2020)!!

    private fun findTarget(expences: List<Int>, target: Int): Int? {
        return expences
            .firstOrNull { expences.contains(target - it) }
            ?.let { (target - it) * it }
    }

    fun partTwo(expences: List<Int>): Int {
        return expences
            .map { it to findTarget(expences, 2020 - it) }
            .first { it.second != null }
            .let { it.first * it.second!! }
    }
}
