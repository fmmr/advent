package no.rodland.advent_2021

import no.rodland.advent.Pos
import kotlin.math.sign

val regex = """(-?\d+),(-?\d+) -> (-?\d+),(-?\d+)""".toRegex()

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
        private val signX = sign((to.x - from.x).toDouble()).toInt()
        private val signY = sign((to.y - from.y).toDouble()).toInt()

        constructor(str: String, mr: MatchResult.Destructured = regex.find(str)!!.destructured) :
            this(
                Pos(mr.component1().toInt(), mr.component2().toInt()),
                Pos(mr.component3().toInt(), mr.component4().toInt()),
            )

        fun isDiagonal() = from.x != to.x && from.y != to.y

        fun allPos(): List<Pos> {
            return generateSequence(from) { last ->
                if (last != to) {
                    last + Pos(signX, signY)
                } else {
                    null
                }
            }.toList()
        }
    }
}
