package no.rodland.advent_2021

import no.rodland.advent.Pos
import kotlin.math.sign


object Day05 {
    val regex = """(-?\d+),(-?\d+) -> (-?\d+),(-?\d+)""".toRegex()

    fun partOne(list: List<String>): Int {
        return list.solve { it.isLine() }
    }

    fun partTwo(list: List<String>): Int {
        return list.solve { true }
    }

    private fun List<String>.solve(filter: (Vent) -> Boolean) = map { Vent(it) }
        .filter { filter(it) }
        .flatMap { it.allPos() }
        .groupingBy { it }
        .eachCount()
        .filterValues { it >= 2 }
        .size

    data class Vent(val from: Pos, val to: Pos) {
        private val delta = Pos((to.x - from.x).sign, (to.y - from.y).sign)

        constructor(str: String, mr: MatchResult.Destructured = regex.find(str)!!.destructured) :
            this(
                Pos(mr.component1().toInt(), mr.component2().toInt()),
                Pos(mr.component3().toInt(), mr.component4().toInt()),
            )

        fun isLine() = from.x == to.x || from.y == to.y

        fun allPos(): List<Pos> {
            return generateSequence(from) { pos -> if (pos != to) pos + delta else null }.toList()
        }
    }
}
