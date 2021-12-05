package no.rodland.advent_2021

import no.rodland.advent.Pos
import kotlin.math.sign

val regex = """(-?\d+),(-?\d+) -> (-?\d+),(-?\d+)""".toRegex()

object Day05 {
    fun partOne(list: List<String>): Int {
        return solve(list) { it.isLine() }
    }

    fun partTwo(list: List<String>): Int {
        return solve(list) { true }
    }

    private fun solve(list: List<String>, filter: (Vent) -> Boolean) = list
        .map { Vent(it) }
        .filter { filter(it) }
        .flatMap { it.allPos() }
        .groupingBy { it }
        .eachCount()
        .filterValues { it >= 2 }
        .size

    data class Vent(val from: Pos, val to: Pos) {
        private val signX = sign((to.x - from.x).toDouble()).toInt()
        private val signY = sign((to.y - from.y).toDouble()).toInt()
        private val delta = Pos(signX, signY)

        constructor(str: String, mr: MatchResult.Destructured = regex.find(str)!!.destructured) :
            this(
                Pos(mr.component1().toInt(), mr.component2().toInt()),
                Pos(mr.component3().toInt(), mr.component4().toInt()),
            )

        fun isLine() = from.x == to.x || from.y == to.y

        fun allPos(): List<Pos> {
            return generateSequence(from) { last -> if (last != to) last + delta else null }.toList()
        }
    }
}
