package no.rodland.advent_2022

import no.rodland.advent.Pos

typealias Grid = Array<CharArray>

// template generated: 28/11/2022
// Fredrik Rødland 2022
private const val WALK = '.'
private const val EMPTY = ' '
private const val WALL = '#'

@Suppress("unused", "UNUSED_VARIABLE")
object Day22 {
    fun partOne(list: List<String>): Int {
        return solve(list)
    }

    fun partTwo(list: List<String>): Int {
        return 2
    }

    private fun part2Next(chars: CharArray, back: Boolean, idx: Int, dir: Dir): Int {
        return 2
    }

    private fun part1Next(chars: CharArray, back: Boolean, idx: Int, dir: Dir): Int {
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

    private fun solve(list: List<String>, getNextIndex: (CharArray, Boolean, Int, Dir) -> Int = ::part1Next): Int {
        val code = list.last().commands()
        val grid = list.dropLast(2).toGrid()
        val last = code.runningFold((Pos(0, 0) to Dir.R)) { previous, command ->
            grid.move(previous.first, previous.second, command.first, command.second, getNextIndex)
        }.last()
        return password(last.first.y, last.first.x, last.second)
    }


    fun Grid.move(from: Pos, facing: Dir, moves: Int, turn: Turn, getNextIndex: (CharArray, Boolean, Int, Dir) -> Int): Pair<Pos, Dir> {
        val newPos = when (facing) {
            Dir.D -> Pos(from.x, map { it[from.x] }.toCharArray().move(from.y, moves, false, facing, getNextIndex))
            Dir.U -> Pos(from.x, map { it[from.x] }.toCharArray().move(from.y, moves, true, facing, getNextIndex))
            Dir.L -> Pos(get(from.y).move(from.x, moves, true, facing, getNextIndex), from.y)
            Dir.R -> Pos(get(from.y).move(from.x, moves, false, facing, getNextIndex), from.y)
        }
        return newPos to facing.turn(turn)
    }


    @Suppress("MoveVariableDeclarationIntoWhen")
    private fun CharArray.move(idx: Int, moves: Int, back: Boolean, dir: Dir, getNextIndex: (CharArray, Boolean, Int, Dir) -> Int): Int {
        var x1 = idx
        var moved = 0
        var lastGoodX = x1
        while (moved < moves) {
            val nextIndex = getNextIndex(this, back, x1, dir)
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

    fun password(y: Int, x: Int, dir: Dir) = 1000 * (y + 1) + 4 * (x + 1) + dir.num
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