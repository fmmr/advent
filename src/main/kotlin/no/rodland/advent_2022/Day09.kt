package no.rodland.advent_2022

import no.rodland.advent.Pos
import kotlin.math.absoluteValue
import kotlin.math.sign


// template generated: 28/11/2022
// Fredrik Rødland 2022

object Day09 {
    fun partOne(input: List<String>): Int {
        return input.toDirections().walk(2).visited.size
    }

    fun partTwo(input: List<String>): Int {
        return input.toDirections().walk(10).visited.size
    }

    private fun List<Dir>.walk(numberOfKnots: Int): State {
        val initialKnots = (1..numberOfKnots).map { Pos(0, 0) }
        val state = fold(State(initialKnots, mutableSetOf())) { acc, dir ->
            val knots = nextPositions(acc.knots, dir)
            acc.visited.add(knots.last())
            State(knots, acc.visited )
        }
        return state
    }

    private fun nextPositions(knots: List<Pos>, dir: Dir): List<Pos> {
        val mutableKnots = knots.toMutableList()
        val newHead = dir.move(mutableKnots.removeFirst())
        return mutableKnots.runningFold(newHead) { head: Pos, tail: Pos -> tail.follow(head) }
    }

    private fun Pos.follow(head: Pos): Pos {
        val diff = head - this
        return when {
            diff.y.absoluteValue <= 1 && diff.x.absoluteValue <= 1 -> this
            else -> Pos(x + diff.x.sign, y + diff.y.sign)
        }
    }

    private data class State(val knots: List<Pos>, val visited: MutableSet<Pos>)

    enum class Dir {
        U, D, L, R;

        fun move(oldPos: Pos): Pos {
            return when (this) {
                U -> Pos(oldPos.x, oldPos.y - 1)
                D -> Pos(oldPos.x, oldPos.y + 1)
                L -> Pos(oldPos.x - 1, oldPos.y)
                R -> Pos(oldPos.x + 1, oldPos.y)
            }
        }
    }

    private fun List<String>.toDirections() = map { it.split(" ") }
        .map { it.first() to it.last().toInt() }
        .flatMap { (dir, howMuch) -> (1..howMuch).map { dir } }
        .map { Dir.valueOf(it) }
}


