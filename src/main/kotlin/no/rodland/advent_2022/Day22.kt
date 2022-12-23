package no.rodland.advent_2022

import no.rodland.advent.Pos
import no.rodland.advent_2022.Dir.*

typealias Grid = Array<CharArray>

// template generated: 28/11/2022
// Fredrik Rødland 2022
private const val WALK = '.'
private const val EMPTY = ' '
private const val WALL = '#'

object Day22 {
    fun partOne(list: List<String>): Int {
        val code = list.last().commands()
        val grid = list.dropLast(2).toGrid()
        val last = code.fold((Pos(0, 0) to R)) { previous, command ->
            grid.move(previous.first, previous.second, command.first, command.second)
        }
        return password(last.first.y, last.first.x, last.second)
    }

    fun partTwo(list: List<String>): Int {
        val code = list.last().commands()
        val grid = list.dropLast(2).toGrid()
        val last = code.fold((Pos(50, 0) to R)) { previous, command ->
            grid.move2(previous.first, previous.second, command.first, command.second)
        }
        return password(last.first.y, last.first.x, last.second)
    }

    private fun Grid.move2(from: Pos, facing: Dir, moves: Int, turn: Turn): Pair<Pos, Dir> {
        var remaining = moves
        var x = from.x
        var y = from.y
        var dir = facing
        while (remaining > 0) {
            val (newX, newY, newDir) = getNext(x, y, dir)
            x = newX
            y = newY
            dir = newDir
            remaining--
        }
        return Pos(x, y) to dir.turn(turn)
    }

    private val X1 = 0 until 50
    private val X2 = 50 until 100
    private val X3 = 100 until 150
    private val Y1 = 0 until 50
    private val Y2 = 50 until 100
    private val Y3 = 100 until 150
    private val Y4 = 150 until 200

    //   E F
    //   D
    // B C
    // A

    private fun a(x: Int, y: Int) = x in X1 && y in Y4
    private fun b(x: Int, y: Int) = x in X1 && y in Y3
    private fun bRight(x: Int, y: Int) = b(x, y) && y == 100
    private fun c(x: Int, y: Int) = x in X2 && y in Y3
    private fun d(x: Int, y: Int) = x in X2 && y in Y2
    private fun e(x: Int, y: Int) = x in X2 && y in Y1
    private fun f(x: Int, y: Int) = x in X3 && y in Y1

    private fun Grid.getNext(x: Int, y: Int, dir: Dir): Triple<Int, Int, Dir> {
        val (newX, newY, newDir) = when {  // phuuuu
            dir == U && bRight(x, y) -> {
                Triple(50, x + 50, R) // UP from B
            }
            dir == U && e(x, y) && y == 0 -> {
                Triple(0, x - 50 + 150, R) // UP from E
            }
            dir == U && f(x, y) && y == 0 -> {
                Triple(x - 100, 199, U) // UP from F
            }
            dir == D && a(x, y) && y == 199 -> {
                Triple(x + 100, 0, D) // DOWN from A
            }
            dir == D && c(x, y) && y == 149 -> {
                Triple(49, x - 50 + 150, L) // DOWN from C
            }
            dir == D && f(x, y) && y == 49 -> {
                Triple(99, x - 100 + 50, L) // DOWN from F
            }
            dir == L && a(x, y) && x == 0 -> {
                Triple(y - 150 + 50, 0, D) // LEFT from A
            }
            dir == L && b(x, y) && x == 0 -> {
                Triple(50, 149 - y, R) // LEFT from B
            }
            dir == L && d(x, y) && x == 50 -> {
                Triple(y - 50, 100, D) // LEFT from D
            }
            dir == L && e(x, y) && x == 50 -> {
                Triple(0, 49 - y + 100, R) // LEFT from E
            }
            dir == R && a(x, y) && x == 49 -> {
                Triple(y - 150 + 50, 149, U) // RIGHT from A
            }
            dir == R && c(x, y) && x == 99 -> {
                Triple(149, 149 - y, L) // RIGHT from C
            }
            dir == R && d(x, y) && x == 99 -> {
                Triple(y - 50 + 100, 49, U) // RIGHT from D
            }
            dir == R && f(x, y) && x == 149 -> {
                Triple(99, 49 - y + 100, L) // RIGHT from F
            }
            dir == U -> Triple(x, y - 1, dir)
            dir == D -> Triple(x, y + 1, dir)
            dir == L -> Triple(x - 1, y, dir)
            dir == R -> Triple(x + 1, y, dir)
            else -> error("should not happen")
        }
        return if (this[Pos(newX, newY)] == '#') {
            Triple(x, y, dir)
        } else {
            Triple(newX, newY, newDir)
        }
    }

    operator fun Grid.get(pos: Pos) = this[pos.y][pos.x]

    private fun part1Next(chars: CharArray, back: Boolean, idx: Int): Int {
        return if (back) {
            if (idx == 0) {
                chars.size - 1
            } else {
                idx - 1
            }
        } else {
            if (idx == chars.size - 1) {
                0
            } else {
                idx + 1
            }
        }
    }


    private fun Grid.move(from: Pos, facing: Dir, moves: Int, turn: Turn): Pair<Pos, Dir> {
        val newPos = when (facing) {
            D -> Pos(from.x, map { it[from.x] }.toCharArray().move(from.y, moves, false))
            U -> Pos(from.x, map { it[from.x] }.toCharArray().move(from.y, moves, true))
            L -> Pos(get(from.y).move(from.x, moves, true), from.y)
            R -> Pos(get(from.y).move(from.x, moves, false), from.y)
        }
        return newPos to facing.turn(turn)
    }

    @Suppress("MoveVariableDeclarationIntoWhen")
    private fun CharArray.move(idx: Int, moves: Int, back: Boolean): Int {
        var x1 = idx
        var moved = 0
        var lastGoodX = x1
        while (moved < moves) {
            val nextIndex = part1Next(this, back, x1)
            val nextChar = this[nextIndex]
            when (nextChar) {
                EMPTY -> x1 = nextIndex
                WALL -> moved++
                WALK -> {
                    moved++
                    x1 = nextIndex
                    lastGoodX = x1
                }
            }
        }
        return lastGoodX
    }

    private fun List<String>.toGrid(): Grid {
        val maxX = maxOf { it.length }
        return indices.map { y ->
            (0 until maxX).map { x ->
                if (x >= this[y].length) {
                    EMPTY
                } else {
                    this[y][x]
                }
            }.toCharArray()
        }.toTypedArray()
    }

    private fun password(y: Int, x: Int, dir: Dir) = 1000 * (y + 1) + 4 * (x + 1) + dir.num
    private fun String.commands(): List<Pair<Int, Turn>> {
        val turns = Turn.values().joinToString("")
        val reg = "(?<=[$turns])|(?=[$turns])".toRegex()
        return (this + "N")
            .split(reg)
            .chunked(2)
            .dropLast(1)  // listOf("")
            .map { it.first().toInt() to Turn.valueOf(it.last()) }
    }
}


enum class Turn { R, L, N; }
enum class Dir(val num: Int) {
    U(3), D(1), L(2), R(0);

    fun turn(t: Turn): Dir {

        return when (this) {
            U -> when (t) {
                Turn.R -> R
                Turn.L -> L
                Turn.N -> this
            }
            D -> when (t) {
                Turn.R -> L
                Turn.L -> R
                Turn.N -> this
            }
            L -> when (t) {
                Turn.R -> U
                Turn.L -> D
                Turn.N -> this
            }
            R -> when (t) {
                Turn.R -> D
                Turn.L -> U
                Turn.N -> this
            }
        }
    }
}