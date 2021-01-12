package no.rodland.advent_2015

import no.rodland.advent.combinations


@Suppress("UNUSED_PARAMETER")
object Day24 {
    fun partOne(list: List<Int>): Long {
        return minimalQuantum(list, 3)
    }

    fun partTwo(list: List<Int>): Long {
        return minimalQuantum(list, 4)
    }

    private fun minimalQuantum(list: List<Int>, groups: Int): Long {
        val groupWeight = list.sum() / groups
        return (1..(list.size))
                .asSequence()
                .map { size ->
                    list.combinations(size).filter { it.sum() == groupWeight }.toList()
                }
                .first { it.isNotEmpty() }
                .map { it.quantumEntanglement() }
                .minOrNull()!!
    }

    private fun List<Int>.quantumEntanglement(): Long = this.fold(1L) { acc: Long, i: Int -> acc * i }
}
