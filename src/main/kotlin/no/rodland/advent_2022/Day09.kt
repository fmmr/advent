package no.rodland.advent_2022

import no.rodland.advent.Pos
import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.math.absoluteValue
import kotlin.math.sign


// template generated: 28/11/2022
// Fredrik Rødland 2022

object Day09 {
    fun partOne(list: List<String>): Int {
        return run(list, 2).visited.size
    }

    @Suppress("UNUSED_PARAMETER")
    fun partTwo(list: List<String>): Int {
        return run(list, 10).visited.size
    }

    private fun run(list: List<String>, numberOfKnots: Int): State {
        val commands = parseDirections(list)
        val knots = (1..numberOfKnots).map { Pos(0, 0) }
        val state = commands.fold(State(ArrayDeque(knots), emptySet())) { acc, dir ->
            val newList = nextPositions(acc.knots, dir)
            State(newList, acc.visited + newList.last())
        }
        return state
    }

    private fun parseDirections(list: List<String>) = list
        .map { it.split(" ") }
        .map { it.first() to it.last().toInt() }
        .flatMap { (dir, howMuch) -> (1..howMuch).map { dir } }
        .map { Dir.valueOf(it) }

    private fun nextPositions(list: ArrayDeque<Pos>, dir: Dir): ArrayDeque<Pos> {
        val newPosHead = dir.move(list.removeFirst())
        return ArrayDeque(list.runningFold(newPosHead) { acc: Pos, pos: Pos -> newPosT(acc, pos) })
    }

    private fun newPosT(posH: Pos, posT: Pos): Pos {
        return when {
            posH.adjacent(posT) -> posT
            else -> Pos(posT.x + (posH.x - posT.x).sign, posT.y + (posH.y - posT.y).sign)
        }
    }

    @Suppress("unused")
    private fun Pos.adjacent(pos: Pos): Boolean {
        val diffX = this.x - pos.x
        val diffY = this.y - pos.y
        return diffY.absoluteValue <= 1 && diffX.absoluteValue <= 1
    }

    @Suppress("unused")
    private fun Double.adjacent() = this < 1.9

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
}


