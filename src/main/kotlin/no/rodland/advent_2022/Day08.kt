package no.rodland.advent_2022

import no.rodland.advent.IntGrid
import no.rodland.advent.Pos

// template generated: 28/11/2022
// Fredrik Rødland 2022

@Suppress("UnusedReceiverParameter")
object Day08 {
    fun partOne(list: List<String>): Int {

        // THOUGHT 1: could do away with lots fewer comparison but only checking trees not already blocked -
        // i.e. find first blocked tree from each direction for each row and column


        // THOUGHT 2: could skip checking edges and add (grid.size * 4 - 4) in the end, but:
        // I find doing a very fast check in start of visibleFromOutside more readable
        // (1..(grid.size - 2)).flatMap { y -> (1..(grid[y].size - 2)).map { x -> Pos(x, y).let { it to grid[it] } } }
        return iterateEveryPosAndCheckIfItsVisible(list)
    }

    private fun iterateEveryPosAndCheckIfItsVisible(list: List<String>) = IntGrid.fromInput(list).flatMapIndexed { y, line ->
        line.mapIndexed { x, value ->
            Pos(x, y) to value
        }.filter { (pos, _) ->
            IntGrid.fromInput(list).visibleFromOutside(pos)
        }
    }.size

    @Suppress("UNUSED_PARAMETER")
    fun partTwo(list: List<String>): Long {
        return 2
    }

    private fun IntGrid.isEdge(pos: Pos): Boolean = pos.any { it == 0 } || pos.any { it == size - 1 }
    private fun IntGrid.visibleFromOutside(pos: Pos): Boolean {
        if (isEdge(pos)) return true
        val value = get(pos)
        return (
                getBelow(pos).all { get(it) < value } ||
                getAbove(pos).all { get(it) < value } ||
                getLeft(pos).all { get(it) < value } ||
                getRight(pos).all { get(it) < value }
            )
    }

    private fun IntGrid.getBelow(pos: Pos): List<Pos> = ((pos.y + 1) until size).map { Pos(pos.x, it) }

    private fun IntGrid.getAbove(pos: Pos): List<Pos> = (0 until (pos.y)).map { Pos(pos.x, it) }

    private fun IntGrid.getLeft(pos: Pos): List<Pos> = (0 until (pos.x)).map { Pos(it, pos.y) }

    private fun IntGrid.getRight(pos: Pos): List<Pos> = ((pos.x + 1) until get(0).size).map { Pos(it, pos.y) }

    private operator fun IntGrid.get(pos: Pos): Int = this[pos.y][pos.x]
}



