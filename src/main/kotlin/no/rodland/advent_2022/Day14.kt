package no.rodland.advent_2022

import no.rodland.advent.Pos
import no.rodland.advent.Cave
import no.rodland.advent.print

// template generated: 28/11/2022
// Fredrik Rødland 2022


object Day14 {

    // 498,4 -> 498,6 -> 496,6
    // 503,4 -> 502,4 -> 502,9 -> 494,9

    fun partOne(input: List<String>): Int {
        val start = Pos(500, 0)
        val cave = cave(input)

        cave.print()
        return 2
    }

    private fun cave(input: List<String>): Cave {
        val walls = input.parse()
        val minX = walls.minOf { it.minOf { it.x } }
        val maxX = walls.maxOf { it.maxOf { it.x } }
        val minY = walls.minOf { it.minOf { it.y } }
        val maxY = walls.maxOf { it.maxOf { it.y } }
        return cave(walls, minX, maxX, minY, maxY)
    }

    private fun cave(walls: List<List<Pos>>, minX: Int, maxX: Int, minY: Int, maxY: Int): Cave {
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

    fun List<String>.parse(): List<List<Pos>> = map { line -> line.split("->").map { it.trim() }.map { Pos(it) } }
    operator fun List<List<Pos>>.contains(pos: Pos): Boolean {
        return any { has(it, pos) }
    }

    private fun has(wall: List<Pos>, pos: Pos): Boolean {
        return wall.windowed(2).any { (first, second) ->
            when {
                first.x == second.x -> pos.x == first.x && ((pos.y in first.y..second.y) || pos.y in first.y downTo second.y)
                first.y == second.y -> {
                    pos.y == first.y && ((pos.x in first.x..second.x) || (pos.x in first.x downTo second.x))
                }
                else -> error("should not happen")
            }
        }
    }
}

