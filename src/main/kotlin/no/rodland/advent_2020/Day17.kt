package no.rodland.advent_2020

import no.rodland.advent.Pos3D

@Suppress("UNUSED_PARAMETER")
object Day17 {

    private const val ACTIVE = '#'
    private const val INACTIVE = '.'

    fun partOne(list: List<String>): Int {
        val initial = list
            .flatMapIndexed { x: Int, line: String ->
                line.mapIndexed { y: Int, c: Char ->
                    Pos3D(x, y, 0) to c
                }
            }
            .toMap()

        return generateSequence(initial) { space ->
            space
                .filter { it.value.active() }.keys
                .flatMap { it.neighbors() }
                .toSet()
                .map { pos ->
                    val state = space.getOrDefault(pos, INACTIVE)
                    val activeNeighbors = pos.activeNeighbors(space)
                    pos to newState(state, activeNeighbors)
                }
                .toMap()
        }
            .drop(6)
            .first()
            .values
            .count { it.active() }
    }

    private fun Pos3D.activeNeighbors(space: Map<Pos3D, Char>) = neighbors().mapNotNull { space[it] }.count { it.active() }

    private fun newState(state: Char, activeNeighbors: Int) = isNextActive(state, activeNeighbors).toChar()

    private fun isNextActive(state: Char, activeNeighbors: Int) = activeNeighbors == 3 || (activeNeighbors == 2 && state.active())

    private fun Boolean.toChar(): Char = if (this) ACTIVE else INACTIVE

    fun partTwo(list: List<String>): Int {
        return 2
    }

    private fun Char.active() = this == ACTIVE
}

