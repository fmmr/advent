package no.rodland.advent_2023

import no.rodland.advent.Day
import no.rodland.advent.Pos
import no.rodland.advent_2023.Tile.*

// template generated: 10/12/2023
// Fredrik Rødland 2023
typealias Tiles = Array<Array<Tile>>

class Day10(val input: List<String>) : Day<Int, Int, Pair<Pos, Tiles>> {

    private val parsed = input.parse()

    override fun partOne(): Int {
        val (start, tiles) = parsed

        val seen = mutableSetOf(start)
        val deque = ArrayDeque(listOf(start))

        // idea: run BFS from start back to start, and then return half the size og the path
        while (deque.isNotEmpty()) {
            val p = deque.removeFirst()
            val current = tiles[p]
            val (n, s, w, e) = p.neighbourCellsUDLR()
            if (current in setOf(S, L, J, PIPE) && tiles[n] in setOf(PIPE, SVN, F) && n !in seen) {
                deque.addLast(n)
                seen.add(n)
            }
            if (current in setOf(S, SVN, F, PIPE) && tiles[s] in setOf(PIPE, L, J) && s !in seen) {
                deque.addLast(s)
                seen.add(s)
            }
            if (current in setOf(S, SVN, J, DASH) && tiles[w] in setOf(DASH, L, F) && w !in seen) {
                deque.addLast(w)
                seen.add(w)
            }
            if (current in setOf(S, L, F, DASH) && tiles[e] in setOf(DASH, SVN, J) && e !in seen) {
                deque.addLast(e)
                seen.add(e)
            }
        }
        return seen.size / 2
    }


    operator fun Tiles.get(pos: Pos): Tile = if (pos in this) this[pos.y][pos.x] else DOT

    operator fun Tiles.contains(pos: Pos): Boolean =
        pos.x >= 0 && pos.x < this[0].size && pos.y >= 0 && pos.y < this.size

    override fun partTwo(): Int {
        return 2
    }


    override fun List<String>.parse(): Pair<Pos, Array<Array<Tile>>> {
        val maxX = maxOf { it.length }
        var start = Pos(0, 0)
        val tiles = indices.map { y ->
            (0 until maxX).map { x ->
                val c = this[y][x]
                if (c == 'S') {
                    start = Pos(x, y)
                }
                c
            }.map { c -> Tile.from(c) }.toTypedArray()
        }.toTypedArray()
        return start to tiles
    }

    override val day = "10".toInt()
}

enum class Tile(val char: Char) {
    PIPE('|'),
    DASH('-'),
    L('L'),
    J('J'),
    SVN('7'),
    F('F'),
    DOT('.'),
    S('S');


    companion object {
        fun from(c: Char) = when (c) {
            '|' -> PIPE
            '-' -> DASH
            'L' -> L
            'J' -> J
            '7' -> SVN
            'F' -> F
            '.' -> DOT
            'S' -> S
            else -> error("unknown tile $c")
        }
    }

}