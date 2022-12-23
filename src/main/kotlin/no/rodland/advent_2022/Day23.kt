package no.rodland.advent_2022

import no.rodland.advent.Pos
import no.rodland.advent_2022.Day23.Dir.*

// template generated: 23/12/2022
// Fredrik Rødland 2022
typealias Elves = Set<Pos>

@Suppress("unused")
class Day23(val input: List<String>) {

    private val parsed = input.parse()

    val dirLists = mapOf(
        N to listOf(N, S, W, E),
        S to listOf(S, W, E, N),
        W to listOf(W, E, N, S),
        E to listOf(E, N, S, W),
    )

    enum class Dir {
        N, S, W, E;

        companion object {
            fun dir(round: Int): Dir = values()[(round - 1 + 4) % 4]
        }
    }

    fun partOne(): Int {
        var elves = parsed
        (1 ..10).forEach { round ->
            println(Dir.dir(round))
            elves = elves.round(round)
        }
        val (x, y) = elves.getMinMax()
        return (x.second - x.first + 1) * (y.second - y.first + 1) - elves.size
    }

    private fun Collection<Pos>.getMinMax(): Pair<Pair<Int, Int>, Pair<Int, Int>> {
        val xMin = minOf { it.x }
        val xMax = maxOf { it.x }
        val yMin = minOf { it.y }
        val yMax = maxOf { it.y }
        return (xMin to xMax) to (yMin to yMax)
    }

    private fun Elves.round(round: Int): Elves {
        val dir = dirLists[Dir.dir(round)]!!
        val newPos = map { it to proposeNew(dir, it) }
        val proposed = newPos.groupBy { it.second }.mapValues { (_, v) -> v.map { it.first } }
        val moved = proposed.filterValues { it.size == 1 }.keys
        val notMoved = proposed.filterValues { it.size > 1 }.values.flatten()
        return moved + notMoved
    }

    private fun Elves.proposeNew(dirs: List<Dir>, pos: Pos): Pos {
        if (pos.neighbourCellsAllEight().none { contains(it) }){
            return pos
        }

        return dirs.firstNotNullOfOrNull { dir ->
            val n = pos.getNeighbours(dir)
            if (n.any { contains(it) }) null else n[1]
        } ?: pos
    }

    private fun Pos.getNeighbours(dir: Dir): List<Pos> {
        return when (dir) {
            N -> listOf(Pos(x - 1, y - 1), Pos(x, y - 1), Pos(x + 1, y - 1))
            S -> listOf(Pos(x - 1, y + 1), Pos(x, y + 1), Pos(x + 1, y + 1))
            W -> listOf(Pos(x - 1, y - 1), Pos(x - 1, y), Pos(x - 1, y + 1))
            E -> listOf(Pos(x + 1, y - 1), Pos(x + 1, y), Pos(x + 1, y + 1))
        }
    }

    fun partTwo(): Long {
        return 2
    }


    fun List<String>.parse(): Elves = flatMapIndexed { y, line ->
        line.mapIndexed { x, c ->
            if (c == '#') {
                Pos(x, y)
            } else {
                null
            }
        }.filterNotNull()
    }.toSet()
}

