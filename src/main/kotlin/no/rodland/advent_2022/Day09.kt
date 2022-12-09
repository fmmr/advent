@file:Suppress("unused")

package no.rodland.advent_2022

import no.rodland.advent.Pos
import kotlin.math.absoluteValue
import kotlin.math.sign

private typealias Combo = Pair<List<Pos>, Set<Pos>>

// template generated: 28/11/2022
// Fredrik Rødland 2022

object Day09 {
    fun partOne(list: List<String>): Int {
        return visited(list, 2)
    }
    @Suppress("UNUSED_PARAMETER")
    fun partTwo(list: List<String>): Int {
        return 2
    }

    private fun visited(list: List<String>, numberOfKnots: Int): Int {
        val cmds = list
            .asSequence()
            .map { it.split(" ") }
            .map { it.first() to it.last().toInt() }
            .flatMap { (dir, howMuch) -> (1..howMuch).map { dir } }
            .map { Dir.valueOf(it) }
        val initial = (1..numberOfKnots).map { _ -> Pos(0, 0) }
        val fold = cmds.fold(Combo(initial, setOf(Pos(0, 0)))) { acc, dir ->
            val newList = newPos(acc.first, dir)
            Combo(newList, acc.second + newList.last())
        }
        return fold.second.size
    }

    private fun newPos(list: List<Pos>, dir: Dir): List<Pos> {
        val newPosH = dir.newPos(list.first())


        val newPosT = newPosT(newPosH, list.last())
        return listOf(newPosH, newPosT)
    }

    private fun newPosT(posH: Pos, posT: Pos): Pos {
        val diffX = posH.x - posT.x
        val diffY = posH.y - posT.y
//        val distance = posH.directDistance(posT)
        return when {
            posH.adjacent(posT) -> posT
            else -> Pos(posT.x + diffX.sign, posT.y + diffY.sign)
        }
    }

    private fun Pos.adjacent(pos: Pos): Boolean {
        val diffX = this.x - pos.x
        val diffY = this.y - pos.y
        return when {
            diffY.absoluteValue <= 1 -> diffX.absoluteValue <= 1
            diffX.absoluteValue <= 1 -> diffY.absoluteValue <= 1
            else -> false
        }
    }

    private fun Double.adjacent() = this < 1.9


    enum class Dir {
        U, D, L, R;

        fun newPos(oldPos: Pos): Pos {
            return when (this) {
                U -> Pos(oldPos.x, oldPos.y - 1)
                D -> Pos(oldPos.x, oldPos.y + 1)
                L -> Pos(oldPos.x - 1, oldPos.y)
                R -> Pos(oldPos.x + 1, oldPos.y)
            }
        }
    }
}


