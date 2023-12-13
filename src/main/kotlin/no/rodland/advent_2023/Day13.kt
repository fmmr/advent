package no.rodland.advent_2023

import no.rodland.advent.Day

// template generated: 13/12/2023
// Fredrik Rødland 2023

class Day13(val input: List<String>) : Day<Int, Long, List<Day13.GroundMap>> {

    private val parsed = input.parse()

    override fun partOne(): Int {
        return parsed.map { groundMap ->
            val rowsHorizontal = isMirror(groundMap)
            val rowsVertical = if (rowsHorizontal == 0) {
                isMirror(groundMap.transpose())
            } else {
                0
            }
//            debug(rowsVertical, rowsHorisontal, groundMap)
            rowsVertical + 100 * rowsHorizontal
        }.sum()
    }

    private fun debug(rowsVertical: Int, rowsHorisontal: Int, groundMap: GroundMap) {
        if (rowsVertical == 0 && rowsHorisontal == 0) {
            println("no reflection for:")
        } else {
            println("misc v: $rowsVertical, h: $rowsHorisontal")
        }
        groundMap.print()
        println()
    }

    // 1500 too low
    // 1700 too low
    private fun isMirror(groundMap: GroundMap): Int {
        (1..<groundMap.size).forEach { i ->
            val map = groundMap.grounds

            val rev = map.subList(0, i).asReversed()
            val forw = map.subList(i, map.size)

            val size = minOf(rev.size, forw.size)
            // println("rev:   $rev    for:    $forw")
            if (rev.subList(0, size) == forw.subList(0, size))
                return i
        }
        return 0
    }

    override fun partTwo(): Long {
        return 2
    }

    override fun List<String>.parse(): List<GroundMap> {
        val converters = joinToString("\n").split("\n\n").map { it.split("\n") }

        return converters.map { l ->
            GroundMap(l)
        }

    }

    data class GroundMap(val grounds: List<String>) {
        fun transpose() = GroundMap(grounds.transpose())
        val size = grounds.size
        val reversed = grounds.reversed()

        fun print() {
            grounds.map { line ->
                println(line)
            }

        }
    }

    override val day = "13".toInt()
}

private fun List<String>.transpose(): List<String> {
    val width = first().length
    val height = size
    return (0..<width).map { j ->
        (0..<height).map { i ->
            get(i)[j]
        }.joinToString("")
    }
}


