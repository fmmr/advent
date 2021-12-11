package no.rodland.advent_2021

import no.rodland.advent.IntGrid
import no.rodland.advent.Pos


@Suppress("UNUSED_PARAMETER")
object Day11 {
    fun partOne(list: List<String>): Int {
        val steps = 10
        val grid = IntGrid.fromInput(list)

        val endState = (1..steps).fold(grid to 0) { acc, i ->
            val next = grid.step(i)
            next.first to acc.second + next.second
        }
//        return endState.second
        return 2
    }

    private fun IntGrid.step(i: Int): Pair<IntGrid, Int> {
        val flashed = mutableSetOf<Pos>()
        val increased = increase()


        val candidates = increased.flatMapIndexed { y, ar ->
            ar
                .mapIndexed() { x, i -> Pos(x, y) to i }
                .filter { it.second > 9 }
                .map { it.first }
        }.toSet() - flashed


        // a: increase every cell by 1
        // b: get all cells > 9 - flash!  Only once
        // c: neigboorsall8 of b: increase by 1
        // d: go to b: while not empty  
        // e: over 9 => 0
        // d: return number of flashes  ( the number of e)
        return increased to 2 /// XXX TODO CHANGE
    }

    fun partTwo(list: List<String>): Int {
        return 2
    }
}

