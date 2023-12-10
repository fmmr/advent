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
        return findPath(start, tiles).size / 2
    }

    override fun partTwo(): Int {
        // point in polygon
        // https://en.wikipedia.org/wiki/Point_in_polygon
        // Ray casting algorithm
        val (start, tiles) = parsed
        val findPath = findPath(start, tiles)
        // set everything else than path to dot
        val cleanTiles = cleanTiles(tiles, findPath)

        val posInside = cleanTiles.flatMapIndexed { y, line ->
            line.mapIndexedNotNull { x, tile ->
                val rest = line.subList(x + 1, line.size)
                if (tile == DOT) {
                    // skip DASH L J  (and maybe S)  - we define the point
                    // as being under these when ray casting horisontally
                    if ((rest.count { it !in setOf(DASH, L, J, DOT) } % 2) == 1) {
                        Pos(x, y)
                    } else {
                        null
                    }
                } else {
                    null
                }
            }
        }
        return posInside.size
    }

    private fun cleanTiles(tiles: Array<Array<Tile>>, findPath: Set<Pos>): List<List<Tile>> {
        return tiles.mapIndexed() { y, line ->
            line.mapIndexed { x, tile ->
                if (Pos(x, y) !in findPath) {
                    DOT
                } else {
                    tile
                }
            }
        }
    }

    private fun findPath(start: Pos, tiles: Array<Array<Tile>>): Set<Pos> {
        val seen = mutableSetOf(start)
        val deque = ArrayDeque(listOf(start))
        // idea: run BFS from start back to start, and then return half the size og the path
        while (deque.isNotEmpty()) {
            val p = deque.removeFirst()
            val current = tiles[p]
            val (n, s, w, e) = p.neighbourCellsUDLR()
            if (current in setOf(S, L, J, PIPE) && tiles[n] in setOf(PIPE, SVN, F) &&  seen.add(n)) {
                deque.addLast(n)
            }
            if (current in setOf(S, SVN, F, PIPE) && tiles[s] in setOf(PIPE, L, J) && seen.add(s)) {
                deque.addLast(s)

            }
            if (current in setOf(S, SVN, J, DASH) && tiles[w] in setOf(DASH, L, F) && seen.add(w)) {
                deque.addLast(w)

            }
            if (current in setOf(S, L, F, DASH) && tiles[e] in setOf(DASH, SVN, J) && seen.add(e)) {
                deque.addLast(e)

            }
        }
        return seen
    }


    operator fun Tiles.get(pos: Pos): Tile = if (pos in this) this[pos.y][pos.x] else DOT

    operator fun Tiles.contains(pos: Pos) =
        pos.x >= 0 && pos.x < this[0].size && pos.y >= 0 && pos.y < this.size


    override fun List<String>.parse(): Pair<Pos, Array<Array<Tile>>> {
        val maxX = maxOf { it.length }
        var start = Pos(0, 0)
        val tiles = indices.map { y ->
            (0 until maxX).map { x ->
                val c = this[y][x]
                val t = Tile.from(c)
                if (t == S) {
                    start = Pos(x, y)
                }
                t
            }.toTypedArray()
        }.toTypedArray()
        return start to tiles
    }

    override val day = "10".toInt()
}

enum class Tile {
    PIPE, DASH, L, J, SVN, F, DOT, S;





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