package no.rodland.advent_2021

import no.rodland.advent.Pos
import no.rodland.advent.Pos3D
import no.rodland.advent.SpacePos

@Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
object Day19 {
    fun partOne(list: List<String>): Int {
        val scanners = list.parse()
        return 2
    }


    fun partTwo(list: List<String>): Int {
        return 2
    }

    private data class Scanner(val idx: Int, val pos: List<SpacePos>) {
        constructor(idx: Int, str: String) : this(idx, str.split("\n").map {
            it.split(",").let {
                when (it.size) {
                    2 -> Pos(it[0].toInt(), it[1].toInt())
                    3 -> Pos3D(it[0].toInt(), it[1].toInt(), it[2].toInt())
                    else -> error("only 2 and 3 dimentions are supported")
                }
            }
        })
    }

    private fun List<String>.parse() = filterNot { it.contains("scanner") }.joinToString("\n").split("\n\n").mapIndexed { idx: Int, s: String -> Scanner(idx, s) }
}
