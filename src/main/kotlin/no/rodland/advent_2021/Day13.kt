package no.rodland.advent_2021

import no.rodland.advent.Pos

@Suppress("UNUSED_PARAMETER")
object Day13 {
    fun partOne(list: List<String>): Int {
        val (grid, instructions) = list.parse()
        return grid.foldPaper(instructions[0]).size
    }

    fun partTwo(list: List<String>): Int {
        return 2
    }

    private fun Set<Pos>.foldPaper(instruction: Instruction): Set<Pos> {
        return if (instruction.axis == 'y') {
            map { pos ->
                if (pos.y < instruction.value) {
                    pos
                } else {
                    Pos(pos.x, instruction.value - (pos.y - instruction.value))
                }
            }
        } else {
            map { pos ->
                if (pos.x < instruction.value) {
                    pos
                } else {
                    Pos(instruction.value - (pos.x - instruction.value), pos.y)
                }
            }
        }.toSet()
    }


    data class Instruction(val axis: Char, val value: Int) {
        constructor(str: String) : this(str.substringBefore("=").last(), str.substringAfter("=").toInt())
    }

    private fun List<String>.parse(): Pair<Set<Pos>, List<Instruction>> {
        val (g, i) = filterNot { it.isEmpty() }.partition { it.contains(',') }
        return g.map { it.split(",") }.map { (x, y) -> Pos(x.toInt(), y.toInt()) }.toSet() to i.map { Instruction(it) }
    }
}


