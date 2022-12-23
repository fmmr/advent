package no.rodland.advent_2022

import no.rodland.advent.Pos

// template generated: 23/12/2022
// Fredrik Rødland 2022
typealias Cave = Array<CharArray>

@Suppress("unused")
class Day23(val input: List<String>) {

    val cave = input.parse()

    fun partOne(): Long {
        return 2
    }

    fun partTwo(): Long {
        return 2
    }

    operator fun Cave.get(pos: Pos): Char = get(pos.y)[pos.x]

    fun List<String>.parse(): Cave = Array(size) { row -> this[row].toCharArray() }
}
