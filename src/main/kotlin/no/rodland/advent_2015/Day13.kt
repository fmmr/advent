@file:Suppress("UnstableApiUsage")

package no.rodland.advent_2015

import com.google.common.collect.Collections2

object Day13 {
    val regex = """(.+) would (lose|gain) (\d+) happiness units by sitting next to (.+).""".toRegex()

    fun partOne(list: List<String>): Int {
        return maxHappinesChange(list.map { Line(it) })
    }

    private fun calculateHappiness(seatings: List<String>, allMap: Map<Pair<String, String>, Line>): Int {
        val pairs = (seatings.windowed(2).map { it.first() to it.last() }) + (seatings.first() to seatings.last())
        return pairs.map { allMap[it.first to it.second]!!.units + allMap[it.second to it.first]!!.units }.sum()
    }

    fun partTwo(list: List<String>): Int {
        val input = list.map { Line(it) }
        val persons = input.map { it.persons.first }.toSet()
        return maxHappinesChange(input + persons.flatMap { listOf(Line("FMR" to it, 0), Line(it to "FMR", 0)) })
    }

    private fun maxHappinesChange(input: List<Line>): Int {
        val persons = input.map { it.persons.first }.toSet()
        val permutations = Collections2.permutations(persons)
        val allMap = input.map { it.persons to it }.toMap()
        return permutations.map { calculateHappiness(it, allMap) }.maxOrNull()!!
    }

    data class Line(val persons: Pair<String, String>, val units: Int) {
        constructor(str: String, mr: MatchResult.Destructured = regex.find(str)!!.destructured) : this(mr.component1() to mr.component4(), if ("gain" == mr.component2()) mr.component3().toInt() else -mr.component3().toInt())
    }
}
