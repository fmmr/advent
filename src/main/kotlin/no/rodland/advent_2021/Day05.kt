package no.rodland.advent_2021

import no.rodland.advent.Pos

val regex = """(-?\d+),(-?\d+) -> (-?\d+),(-?\d+)""".toRegex()

@Suppress("UNUSED_PARAMETER")
object Day05 {
    fun partOne(list: List<String>): Int {
        return list
            .map { Vent(it) }
            .filterNot { it.isDiagonal() }
            .flatMap { it.allPos() }
            .groupingBy { it }
            .eachCount()
            .filterValues { it >= 2 }
            .size
    }

    fun partTwo(list: List<String>): Int {
        return list
            .map { Vent(it) }
            .flatMap { it.allPos() }
            .groupingBy { it }
            .eachCount()
            .filterValues { it >= 2 }
            .size
    }

    data class Vent(val from: Pos, val to: Pos) {

        constructor(str: String, mr: MatchResult.Destructured = regex.find(str)!!.destructured) : this(
            Pos(mr.component1().toInt(), mr.component2().toInt()),
            Pos(mr.component3().toInt(), mr.component4().toInt()),
        )

        fun isDiagonal() = from.x != to.x && from.y != to.y

        fun allPos(): List<Pos> {
            val signX = Math.signum((to.x - from.x).toDouble()).toInt()
            val signy = Math.signum((to.y - from.y).toDouble()).toInt()
            var lastPos = from - Pos(signX, signy)
            return generateSequence {
                if (lastPos == to) {
                    null
                } else {
                    lastPos += Pos(signX, signy)
                    lastPos
                }
            }.toList()
        }
    }
}
