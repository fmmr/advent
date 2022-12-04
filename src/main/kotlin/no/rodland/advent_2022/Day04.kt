package no.rodland.advent_2022

// template generated: 28/11/2022
// Fredrik Rødland 2022

object Day04 {
    fun partOne(list: List<String>): Int {
        return list.map { CleaningPair(it) }.count { it.second fullyContained it.first || it.first fullyContained it.second }
    }

    fun partTwo(list: List<String>): Int {
        return list.map { CleaningPair(it) }.count { it.first partlyContained it.second || it.second partlyContained it.first }
    }

    data class CleaningPair(val input: String) {
        val first = input.split(",").first().toRange()
        val second = input.split(",").last().toRange()
    }

    private fun String.toRange() = split("-").let { it.first().toInt()..it.last().toInt() }

    private infix fun IntRange.fullyContained(other: IntRange) = first in other && last in other
    private infix fun IntRange.partlyContained(other: IntRange) = first in other || last in other
}
