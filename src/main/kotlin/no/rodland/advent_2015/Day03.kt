package no.rodland.advent_2015

import no.rodland.advent.Pos

object Day03 {
    fun partOne(list: String): Int {
        return houses(list).distinct().count()
    }

    fun partTwo(list: String): Int {
        val santa = list.filterIndexed { index, _ -> index % 2 == 0 }
        val roboSanta = list.filterIndexed { index, _ -> index % 2 == 1 }
        return (houses(santa) + houses(roboSanta)).distinct().count()
    }

    private fun houses(instructions: String): List<Pos> {
        return instructions.runningFold(Pos(0, 0)) { acc, c -> acc.next(c) }
    }
}
