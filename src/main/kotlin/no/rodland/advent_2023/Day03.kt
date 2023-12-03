package no.rodland.advent_2023

import no.rodland.advent.Day
import no.rodland.advent.Pos

typealias Grid = Array<CharArray>

// template generated: 02/12/2023
// Fredrik Rødland 2023

class Day03(val input: List<String>) : Day<Int, Int, Grid> {

    private val symbols: MutableList<Pos> = mutableListOf()
    private val gears: MutableList<Pos> = mutableListOf()
    private val numbers: MutableList<Pos> = mutableListOf()

    private val parsed = input.parse()

    override fun partOne(): Int {
        val numberWithNeighbours = numberWithNeighbours()
        return numberWithNeighbours.sumOf { (number, neighbours) ->
            if (neighbours.any { neighbour -> neighbour in symbols }) {
                number
            } else {
                0
            }
        }
    }

    override fun partTwo(): Int {
        val numberWithNeighbours = numberWithNeighbours()
        return gears.sumOf { gearPos ->
            val numbers = numberWithNeighbours.filter { it.second.contains(gearPos) }.map { it.first }
            if (numbers.size == 2) {
                numbers[0] * numbers[1]
            } else {
                0
            }
        }
    }

    private fun numberWithNeighbours(): List<Pair<Int, List<Pos>>> {
        return numbers.map { start ->
            val row = parsed[start.y]
            val number = row.copyOfRange(start.x, row.size).takeWhile { d -> d.isDigit() }.joinToString("")
            val neighbours = number.positions(start)
                .flatMap { pos -> pos.neighbourCellsAllEight() }
                .filter { pos -> pos !in number.positions(start) }
                .filter { pos -> parsed.inGrid(pos) }
                .distinct()
            Pair(number.toInt(), neighbours)
        }
    }

    private fun Grid.inGrid(pos: Pos) = pos.x >= 0 && pos.y >= 0 && pos.x < this[0].size && pos.y < size

    private fun String.positions(start: Pos): List<Pos> {
        val length = length
        return (0 until length).map { i ->
            start.copy(x = start.x + i)
        }
    }


    override fun List<String>.parse(): Grid {
        val maxX = maxOf { it.length }
        var lastC: Char?
        return indices.map { y ->
            lastC = null
            (0 until maxX).map { x ->
                val c = this[y][x]
                when {
                    c == '.' -> {}
                    c == '*' -> {
                        symbols.add(Pos(x, y))
                        gears.add(Pos(x, y))
                    }

                    !c.isDigit() -> symbols.add(Pos(x, y))
                    lastC?.isDigit() != true -> numbers.add(Pos(x, y))
                    else -> {}
                }
                lastC = c
                c
            }.toCharArray()
        }.toTypedArray()
    }

    override val day = "03".toInt()
}
