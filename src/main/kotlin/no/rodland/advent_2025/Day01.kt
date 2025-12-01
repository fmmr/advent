package no.rodland.advent_2025

import no.rodland.advent.Day

// template generated: 01/12/2025
// Fredrik Rødland 2025

class Day01(val input: List<String>) : Day<Long, Long, List<Pair<LF, Int>>> {

    private val parsed = input.parse()
    private val start = 50

    override fun partOne(): Long {
        return parsed.runningFold(start) { acc, (dir, num) -> turn(acc, dir, num) }.count { it == 0 }.toLong()
    }

    override fun partTwo(): Long {
        return 2
    }

    fun turn(previous: Int, dir: LF, num: Int): Int {
        return when (dir) {
            LF.L -> (previous - num) % 100
            LF.R -> (previous + num) % 100
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
