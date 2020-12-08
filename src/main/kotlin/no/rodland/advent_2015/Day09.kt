package no.rodland.advent_2015

import com.google.common.collect.Collections2

@Suppress("UnstableApiUsage")
object Day09 {

    // AlphaCentauri to Faerun = 44

    fun partOne(list: List<String>): Int {
        val weights = list
            .map { it.split(" = ") }
            .map { (it[0].split(" to ")[0] to it[0].split(" to ")[1]) to (it[1].toIntOrNull() ?: -1) }
            .flatMap { listOf(it, (it.first.second to it.first.first) to it.second) }
            .toMap()
        val locations = weights.map { it.key }.flatMap { listOf(it.first, it.second) }.distinct()
        val permutations = Collections2.permutations(locations)
        return permutations.map { it.distance(weights) }.minOrNull() ?: -1
    }

    private fun List<String>.distance(weights: Map<Pair<String, String>, Int>): Int = zipWithNext().map { weights[it]!! }.sum()

    fun partTwo(list: List<String>): Int {
        return 2
    }

    fun List<Pair<String, String>>.getUniqueValuesFromPairs() = map { (a, b) -> listOf(a, b) }
        .flatten()
        .toSet()

    fun List<Pair<String, String>>.getUniqueValuesFromPairs(predicate: (String) -> Boolean) = map { (a, b) -> listOf(a, b) }
        .flatten()
        .filter(predicate)
        .toSet()

}




