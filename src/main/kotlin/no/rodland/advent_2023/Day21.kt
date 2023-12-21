package no.rodland.advent_2023

import no.rodland.advent.Day
import no.rodland.advent.Pos


// template generated: 21/12/2023
// Fredrik Rødland 2023

class Day21(val input: List<String>) : Day<Int, Long, Map<Pos, Char>> {

    private val parsed = input.parse()

    override fun partOne(): Int {
//        val start = parsed.filterValues { it == 'S' }.map { it.key }.single()
        var newMap = parsed
        val repeat = if (parsed.size == 121) 6 else 64
        repeat(repeat) { newMap = step(newMap) }
        return newMap.count { it.value == 'O' }
    }

    fun step(cave: Map<Pos, Char>): Map<Pos, Char> {
        val previousSteps = cave.filterValues { it == 'O' || it == 'S' }.keys
        val newOs = previousSteps
            .flatMap { it.neighbourCellsUDLR() }
            .filterNot { cave[it] == '#' }
            .map { it to 'O' }
        val nullOld = previousSteps.map { it to '.' }
        return cave + nullOld + newOs
    }


    override fun partTwo(): Long {
        return 2
    }

    override fun List<String>.parse(): Map<Pos, Char> {
        return flatMapIndexed { y, line ->
            line.mapIndexed() { x, c ->
                Pos(x, y) to c
            }
        }.toMap()
    }

    override val day = "21".toInt()
}
