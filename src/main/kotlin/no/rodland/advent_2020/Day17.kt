package no.rodland.advent_2020

import no.rodland.advent.Pos3D
import no.rodland.advent.Pos3D.Companion.getMinMax

@Suppress("UNUSED_PARAMETER")
object Day17 {

    const val ACTIVE = '#'
    const val INACTIVE = '.'
    const val CYCLES = 6

    fun partOne(list: List<String>): Int {
        val initial = list
            .flatMapIndexed { x: Int, line: String ->
                line.mapIndexed { y: Int, c: Char ->
                    Pos3D(x, y, 0) to c
                }
            }
            .toMap()

        val space = generateSequence(initial) { newSpace ->
            newSpace
                .filter { it.value.active() }.keys
                .flatMap {
                    it.neighbors()
                }
                .toSet().map { pos ->
                    val state = newSpace.getOrDefault(pos, INACTIVE)
                    val activeNeighbors = pos.activeNeighbors(newSpace)
                    pos to newState(state, activeNeighbors)
                }
                .toMap()
        }.drop(6).first()
        return space.values.count { it.active() }
    }

    private fun Pos3D.activeNeighbors(space: Map<Pos3D, Char>) = neighbors().mapNotNull { space[it] }.count { it.active() }

    private fun newState(state: Char, activeNeighbors: Int) = if (state.active()) {
        if (activeNeighbors in 2..3) ACTIVE else INACTIVE
    } else {
        if (activeNeighbors == 3) ACTIVE else INACTIVE
    }

    private fun getMinMax(space: Map<Pos3D, Char>): Triple<Pair<Int, Int>, Pair<Int, Int>, Pair<Int, Int>> {
        val mm = getMinMax(space.keys)
        val mmx = mm.first.first - 1 to mm.first.second + 1
        val mmy = mm.second.first - 1 to mm.second.second + 1
        val mmz = mm.third.first - 1 to mm.third.second + 1
        return Triple(mmx, mmy, mmz)
    }

    fun partTwo(list: List<String>): Int {
        return 2
    }

    private fun Char.active() = this == ACTIVE
}
