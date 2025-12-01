package no.rodland.advent_2025

import no.rodland.advent.Day
import kotlin.math.absoluteValue

// template generated: 01/12/2025
// Fredrik Rødland 2025

class Day01(val input: List<String>) : Day<Long, Long, List<Pair<LF, Int>>> {

    private val parsed = input.parse()
    private val start = 50

    override fun partOne(): Long {
        return parsed.runningFold(start to 0) { acc, (dir, num) -> turn(acc.first, dir, num) }.count { it.first == 0 }.toLong()
    }

    override fun partTwo(): Long {
        return parsed.runningFold<Pair<LF, Int>, Pair<Int, Int>>(start to 0) { acc, (dir, num) -> turn(acc.first, dir, num) }.sumOf { it.second }.toLong()
    }

    fun turn(currentPos: Int, dir: LF, num: Int): Pair<Int, Int> {
        val newNumRaw = when (dir) {
            LF.L -> (currentPos - num)
            LF.R -> (currentPos + num)
        }
        val newNum = newNumRaw.mod(100)
        val passesZero = passesZero(currentPos, newNumRaw)
        return newNum to passesZero
    }

    private fun passesZero(previous: Int, newRawPos: Int): Int {
        return when {
            newRawPos == 0 -> 1
            newRawPos > 0 -> newRawPos / 100
            else -> newRawPos.absoluteValue / 100 + if (previous == 0) 0 else 1
        }
    }

    override fun List<String>.parse(): List<Pair<LF, Int>> {
        return map { line ->
            LF.valueOf(line.first().toString()) to line.drop(1).toInt()
        }
    }

    override val day = "01".toInt()
}

enum class LF {
    L, R
}
