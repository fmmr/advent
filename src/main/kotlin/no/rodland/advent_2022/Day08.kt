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
        // would have to account for not counting same pos twice though

        // THOUGHT 2: could skip checking edges and add (grid.size * 4 - 4) in the end, but:
        // I find doing a very fast check in start of visibleFromOutside more readable
        // (1..(grid.size - 2)).flatMap { y -> (1..(grid[y].size - 2)).map { x -> Pos(x, y).let { it to grid[it] } } }
        val grid = IntGrid.fromInput(list)
        return grid.flatMapIndexed { y, line ->
            line.mapIndexed { x, value ->
                Pos(x, y) to value
            }.filter { (pos, value) ->
                grid.visibleFromOutside(pos, value)
            }
        }.size
    }

    @Suppress("UNUSED_PARAMETER")
    fun partTwo(list: List<String>): Int {
        val grid = IntGrid.fromInput(list)
        val allPos = grid.flatMapIndexed { y, line ->
            line.mapIndexed { x, value ->
                Pos(x, y) to value
            }
        }
        val scenicScores = allPos.map { (pos, value) ->
            pos to grid.scenicScore(pos, value)
        }
        return scenicScores.maxOf { it.second }
    }

    private fun IntGrid.scenicScore(pos: Pos, value: Int): Int {
        if (isEdge(pos)) return 0
        return getBelow(pos).scenicScore(this, value) *
            getAbove(pos).scenicScore(this, value) *
            getLeft(pos).scenicScore(this, value) *
            getRight(pos).scenicScore(this, value)
    }

    private fun IntGrid.visibleFromOutside(pos: Pos, value: Int): Boolean {
        return (
            isEdge(pos) ||
                getBelow(pos).all { get(it) < value } ||
                getAbove(pos).all { get(it) < value } ||
                getLeft(pos).all { get(it) < value } ||
                getRight(pos).all { get(it) < value }
            )
    }

    private fun List<Pos>.scenicScore(intGrid: IntGrid, value: Int): Int {
        val idx = indexOfFirst { intGrid[it] >= value } + 1
        return if (idx == 0) size else idx
    }

    private fun IntGrid.isEdge(pos: Pos): Boolean = pos.any { it == 0 } || pos.any { it == size - 1 }

    private fun IntGrid.getBelow(pos: Pos): List<Pos> = ((pos.y + 1) until size).map { Pos(pos.x, it) }

    private fun IntGrid.getAbove(pos: Pos): List<Pos> = (0 until (pos.y)).map { Pos(pos.x, it) }.reversed()

    private fun IntGrid.getLeft(pos: Pos): List<Pos> = (0 until (pos.x)).map { Pos(it, pos.y) }.reversed()

    private fun IntGrid.getRight(pos: Pos): List<Pos> = ((pos.x + 1) until get(0).size).map { Pos(it, pos.y) }

    private operator fun IntGrid.get(pos: Pos): Int = this[pos.y][pos.x]
}



