package no.rodland.advent_2022

// template generated: 28/11/2022
// Fredrik Rødland 2022

object Day04 {
    fun partOne(list: List<String>): Int {
        return count(list, ::fullyContained)
    }

    fun partTwo(list: List<String>): Int {
        return count(list, ::partlyContained)
    }

    private fun count(list: List<String>, func: (IntRange, IntRange) -> Boolean) = list
        .map { input -> input.split(",").let { it.first().toRange() to it.last().toRange() } }
        .count { func(it.first, it.second) || func(it.second, it.first) }

    private fun String.toRange() = split("-").let { it.first().toInt()..it.last().toInt() }

    private fun fullyContained(first: IntRange, second: IntRange) = first.first in second && first.last in second
    private fun partlyContained(first: IntRange, second: IntRange) =  first.first <= second.last && second.first <= first.last
}
