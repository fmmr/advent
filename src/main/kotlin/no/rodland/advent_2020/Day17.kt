package no.rodland.advent_2020

import no.rodland.advent.Pos3D
import no.rodland.advent.Pos4D
import no.rodland.advent.SpacePos

@Suppress("UNUSED_PARAMETER")
object Day17 {

    private const val ACTIVE = '#'
    private const val INACTIVE = '.'

    fun partOne(list: List<String>): Int {
        val initial = parseInput(list) { x: Int, y: Int -> Pos3D(x, y, 0) }
        return simulate(initial, 6).size

    }

    fun partTwo(list: List<String>): Int {
        val initial = parseInput(list) { x: Int, y: Int -> Pos4D(x, y, 0, 0) }
        return simulate(initial, 6).size
    }

    @Suppress("SameParameterValue")
    private fun simulate(initial: Map<out SpacePos, Char>, times: Int): Map<out SpacePos, Char> {
        return generateSequence(initial) { space ->
            space
                .keys
                .flatMap { it.neighbours() }
                .toSet()
                .mapNotNull { pos ->
                    val state = space[pos] ?: INACTIVE
                    val activeNeighbors = pos.activeNeighbors(space)
                    newState(state, activeNeighbors).let { if (it.active()) pos to ACTIVE else null }
                }
                .toMap()
        }
            .drop(times)
            .first()
    }

    private fun parseInput(list: List<String>, initialPoint: (x: Int, y: Int) -> SpacePos): Map<SpacePos, Char> {
        return list
            .flatMapIndexed { x: Int, line: String ->
                line.mapIndexed { y: Int, c: Char -> initialPoint(x, y) to c }
                    .filter { it.second.active() }
            }
            .toMap()
    }

    private fun newState(state: Char, activeNeighbors: Int) = isNextActive(state, activeNeighbors).toChar()

    private fun isNextActive(state: Char, activeNeighbors: Int) = activeNeighbors == 3 || (activeNeighbors == 2 && state.active())

    private fun Boolean.toChar(): Char = if (this) ACTIVE else INACTIVE

    private fun Char.active() = this == ACTIVE
}

