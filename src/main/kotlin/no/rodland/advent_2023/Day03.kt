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
        return numbers.sumOf { start ->
            val (number, neighbours) = neighbours(start)
            if (neighbours.any { neighbour -> neighbour in symbols }) {
                number
            } else {
                0
            }
        }
    }

    override fun partTwo(): Int {
        val allNeighbours = numbers.map { start ->
            val (number, neighbours) = neighbours(start)
            number to neighbours
        }
        return gears.sumOf { gearPos ->
            val numbers = allNeighbours.filter { it.second.contains(gearPos) }.map { it.first }
            if (numbers.size == 2) {
                numbers[0] * numbers[1]
            } else {
                0
            }
        }
    }

    private fun neighbours(start: Pos): Pair<Int, List<Pos>> {
        val row = parsed[start.y]
        val sub = row.copyOfRange(start.x, row.size)
        val numAsString = sub.takeWhile { d -> d.isDigit() }.joinToString("")
        val length = numAsString.length
        val number = numAsString.toInt()

        val posList = getAllPosForLength(start, length)
        val neighbours = posList
            .flatMap { it.neighbourCellsAllEight() }
            .filter { it !in posList }
            .filter { pos -> pos.x >= 0 && pos.y >= 0 && pos.x < parsed[0].size && pos.y < parsed.size }
            .distinct()
        return Pair(number, neighbours)
    }

    private fun getAllPosForLength(start: Pos, length: Int): List<Pos> {
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

    //private operator fun Grid.get(pos: Pos): Char = this[pos.y][pos.x]


    override val day = "03".toInt()
}
