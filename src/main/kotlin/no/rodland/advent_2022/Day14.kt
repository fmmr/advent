package no.rodland.advent_2022

import no.rodland.advent.Pos
import no.rodland.advent.Cave
import no.rodland.advent.print

// template generated: 28/11/2022
// Fredrik Rødland 2022

object Day14 {

    fun partOne(input: List<String>): Int {
        val start = Pos(500, 0)
        val cave = cave(input)
//        cave.print()
        val units = doIt(start, cave)
//        cave.print()
        return units
    }

    private fun doIt(start: Pos, cave: Array<CharArray>): Int = 2
//        TODO("Not yet implemented")
//    }

    private fun cave(input: List<String>): Cave {
        val walls = input.wall().toSet()
        val (x, y) = Pos.getMinMax(walls)
        return cave(walls, x.first, x.second, y.first, y.second)
    }

    private fun cave(walls: Set<Pos>, minX: Int, maxX: Int, minY: Int, maxY: Int): Cave {
        return ((minY - 1)..(maxY + 1)).map { y ->
            ((minX - 1)..(maxX + 1)).map { x ->
                if (Pos(x, y) in walls) {
                    '#'
                } else {
                    '.'
                }
            }.toCharArray()
        }.toTypedArray()
    }

    fun partTwo(input: List<String>): Long {
        return 2
    }

    private fun List<String>.wall(): List<Pos> {
        return flatMap { line ->
            line.split("->").windowed(2).flatMap { (first, second) ->
                Pos(first).fill(Pos(second))
            }
        }
    }
}

