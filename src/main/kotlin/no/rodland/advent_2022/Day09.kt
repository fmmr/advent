package no.rodland.advent_2022

import no.rodland.advent.Pos
import kotlin.math.sign

private typealias Combo = Triple<Pos, Pos, Set<Pos>>

// template generated: 28/11/2022
// Fredrik Rødland 2022

object Day09 {
    fun partOne(list: List<String>): Int {
        val cmds = list
            .asSequence()
            .map { it.split(" ") }
            .map { it.first() to it.last().toInt() }
            .flatMap { (dir, howMuch) -> (1..howMuch).map { dir } }
            .map { Dir.valueOf(it) }
        val fold = cmds.fold(Combo(Pos(0, 0), Pos(0, 0), setOf(Pos(0, 0)))) { acc, dir ->
            val (newPosH, newPosT) = newPos(acc.first, acc.second, dir)
            Combo(newPosH, newPosT, acc.third + newPosT)
        }
        return fold.third.size
    }

    @Suppress("UNUSED_PARAMETER")
    fun partTwo(list: List<String>): Int {
        return 2
    }


    private fun newPos(posH: Pos, posT: Pos, dir: Dir): Pair<Pos, Pos> {
        val newPosH = dir.newPos(posH)
        val newPosT = newPosT(newPosH, posT)
        return newPosH to newPosT
    }

    private fun newPosT(posH: Pos, posT: Pos): Pos {
        val diffX = posH.x - posT.x
        val diffY = posH.y - posT.y
        val distance = posH.directDistance(posT)
        return when {
            distance.adjacent() -> posT
            else -> Pos(posT.x + diffX.sign, posT.y + diffY.sign)
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
