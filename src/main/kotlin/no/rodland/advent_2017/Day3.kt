package no.rodland.advent_2017

import isEven
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

    fun partTwo(list: List<String>): Int {
        return 2
    }

}