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
        val state = fold(State(ArrayDeque(initialKnots), emptySet())) { acc, dir ->
            val knots = nextPositions(acc.knots, dir)
            State(knots, acc.visited + knots.last())
        }
        return state
    }

    private fun nextPositions(knots: ArrayDeque<Pos>, dir: Dir): ArrayDeque<Pos> {
        val newHead = dir.move(knots.removeFirst())
        val nextKnots = knots.runningFold(newHead) { head: Pos, tail: Pos -> nextPos(head, tail) }
        return ArrayDeque(nextKnots)
    }

    private fun nextPos(head: Pos, tail: Pos): Pos {
        val diff = head - tail
        return when {
            diff.y.absoluteValue <= 1 && diff.x.absoluteValue <= 1 -> tail
            else -> Pos(tail.x + diff.x.sign, tail.y + diff.y.sign)
        }
    }

    private data class State(val knots: ArrayDeque<Pos>, val visited: Set<Pos>)

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


