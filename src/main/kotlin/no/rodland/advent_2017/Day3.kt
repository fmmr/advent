package no.rodland.advent_2017

import isEven
import no.rodland.advent_2017.Day3.Direction.DOWN
import no.rodland.advent_2017.Day3.Direction.UP
import kotlin.math.abs
import kotlin.math.ceil
import kotlin.math.roundToInt
import kotlin.math.sqrt

object Day3 {

    fun partOne(num: Int): Int {
        if (num == 1) {
            return 0
        }
        val s = ceil(sqrt(num.toDouble())).roundToInt()
        val nextOddNumber = if (s.isEven()) {
            s + 1
        } else {
            s
        }
        val nextSquare = nextOddNumber * nextOddNumber
        val onSquare = genSquares().takeWhile { nextSquare >= it }.count() - 1
        // find the largest middle elements
        val middle = nextSquare - onSquare
        // find the closest "middle" element
        val closest = genMiddles(middle, nextOddNumber)
                .associate { it to abs(num - it) }
                .minBy { it.value }

        println("num: $num, s: $s, o: $nextOddNumber, nextSquare: $nextSquare, square: $onSquare, side-length: $nextOddNumber, middle: $middle, closest: $closest")
        return onSquare + (closest?.value ?: 0)
    }

    fun partTwo(limit: Int): Int {
        // size 11 is enough to solve number in test
        return Grid(50, 1).genSeq().first { it > limit }
    }

    private enum class Direction {
        UP, DOWN, LEFT, RIGHT;

        fun nextPos(pos: Pos): Pos {
            return when (this) {
                LEFT -> Pos(pos.x - 1, pos.y)
                UP -> Pos(pos.x, pos.y - 1)
                DOWN -> Pos(pos.x, pos.y + 1)
                RIGHT -> Pos(pos.x + 1, pos.y)
            }
        }

        fun turn(): Direction {
            return when (this) {
                LEFT -> DOWN
                UP -> LEFT
                DOWN -> RIGHT
                RIGHT -> UP
            }
        }
    }

    private data class Pos(val x: Int, val y: Int) {
        constructor(pair: Pair<Int, Int>) : this(pair.first, pair.second)

        fun getValue(map: List<IntArray>): Int {
            return getNeighboors().map { map[it.x][it.y] }.sum()
        }

        private fun getNeighboors(): List<Pos> {
            return listOf(
                    Pos(x + 1, y),
                    Pos(x - 1, y),

                    Pos(x + 1, y + 1),
                    Pos(x, y + 1),
                    Pos(x - 1, y + 1),

                    Pos(x + 1, y - 1),
                    Pos(x, y - 1),
                    Pos(x - 1, y - 1)
            )
        }
    }

    private class Grid(size: Int, seed: Int) {
        val pos: Pos = Pos((size / 2) to (size / 2))
        val map: List<IntArray> = (0 until size).map {
            IntArray(size) {
                0
            }
        }.apply { this[pos.x][pos.y] = seed }

        fun genSeq(): Sequence<Int> {
            var lastPos = pos
            var direction = UP // fake it til you make it  (get to return 1 for first element)
            return generateSequence() {
                if (lastPos == pos && direction == UP) {
                    direction = DOWN
                    return@generateSequence 1
                }
                val (dir, pos) = getNextPos(direction, lastPos)
                lastPos = pos
                direction = dir

                val value = pos.getValue(map)
                map[pos.x][pos.y] = value
                value
            }
        }

        fun getNextPos(keep_going: Direction, pos: Pos): Pair<Direction, Pos> {
            val turn = keep_going.turn()
            val turnPos = turn.nextPos(pos)
            return if (map[turnPos.x][turnPos.y] == 0) {
                turn to turnPos
            } else {
                keep_going to keep_going.nextPos(pos)
            }
        }

    }

    fun genMiddles(middle: Int, num: Int): Sequence<Int> {
        var i = 0
        return generateSequence {
            if (i == 4) {
                null
            } else {
                middle - i++ * (num - 1)
            }
        }
    }


    fun genSquares(): Sequence<Int> {
        var i = -1
        return generateSequence {
            i += 2
            i * i
        }
    }
}

