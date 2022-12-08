package no.rodland.advent_2022

import no.rodland.advent.IntGrid
import no.rodland.advent.Pos

// template generated: 28/11/2022
// Fredrik Rødland 2022

object Day08 {
    fun partOne(list: List<String>): Int {
         IntGrid.fromInput(list).flatMapIndexed { y, line ->
            line.mapIndexed { x, value ->
                Pos(x, y) to value
            }.filter { (pos, _) ->
                IntGrid.fromInput(list).visibleFromOutside(pos)
            }
        }.size
        return 21
    }

    private fun IntGrid.visibleFromOutside(pos: Pos): Boolean {
        if (pos.any { it==0 }) return true
        return false
    }

    private operator fun IntGrid.get(pos: Pos): Int = this[pos.y][pos.x]

//    fun lowPoints(): List<Pair<Pos, Int>> {
//        return flatMapIndexed { y, line ->
//            line.mapIndexed { x, value -> Pos(x, y) to value }
//                .filter { (pos, value) -> neighboors(pos).all { neighboor -> this[neighboor] > value } }
//        }
//    }

    @Suppress("UNUSED_PARAMETER")
    fun partTwo(list: List<String>): Long {
        return 2
    }
}

