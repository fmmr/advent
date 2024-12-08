package no.rodland.advent_2024

import no.rodland.advent.Day
import no.rodland.advent.Pos

// template generated: 08/12/2024
// Fredrik Rødland 2024

class Day08(val input: List<String>) : Day<Int, Long, Pair<Array<CharArray>, Map<Char, List<Pos>>>> {

    private val parsed = input.parse()
    val grid = parsed.first
    val map = parsed.second

    override fun partOne(): Int {
        val hei = map
            .flatMap { (_, v) ->
                v.pairs().flatMap { it.antinodes() }
            }
            .filter { p -> p in grid }
            .toSet()
        return hei.size
    }

    private fun Pair<Pos, Pos>.antinodes(): List<Pos> {
        val diff = second - first
        return listOf(second + diff, first - diff)
    }

    operator fun Grid.contains(pos: Pos): Boolean =
        pos.x >= 0 && pos.x < this[0].size && pos.y >= 0 && pos.y < this.size

    operator fun Grid.get(pos: Pos): Char {
        return this[pos.y][pos.x]
    }


    private fun List<Pos>.pairs(): List<Pair<Pos, Pos>> {
        if (size < 2) return emptyList()
        val first = first()
        return drop(1).let { rest -> rest.map { first to it } + rest.pairs() }
    }

    override fun partTwo(): Long {
        return 2
    }

    override fun List<String>.parse(): Pair<Array<CharArray>, Map<Char, List<Pos>>> {
        val map = mutableMapOf<Char, MutableList<Pos>>()
        val grid = indices.map { y ->
            indices.map { x ->
                val c = this[y][x]
                if (c != '.') {
                    map.getOrPut(c) { mutableListOf() }.add(Pos(x, y))
                }
                c

            }.toCharArray()
        }.toTypedArray()
        return grid to map
    }

    override val day = "08".toInt()
}


