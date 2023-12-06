package no.rodland.advent_2023

import no.rodland.advent.Day
import product

// template generated: 06/12/2023
// Fredrik Rødland 2023

class Day06(val input: List<Pair<Int, Int>>) : Day<Long, Long, List<Pair<Int, Int>>> {

    override fun List<String>.parse(): List<Pair<Int, Int>> {
        TODO("Not yet implemented")
    }

    override fun partOne(): Long {
        return input.map { line ->
            (0..line.first).count { push(it.toLong(), line.first.toLong()) > line.second }.toLong()
        }.product()
    }

    override fun partTwo(): Long {
        val test = input.first() == (7 to 9)
        return if (test) {
            val line = 71530L to 940200L
            (0..line.first).count { push(it, line.first) > line.second }.toLong()

        } else {
            val line = 48938466L to 261119210191063L
            (0..line.first).count { push(it, line.first) > line.second }.toLong()

        }
    }

    private fun push(pushButton: Long, maxLength: Long): Long {
        return pushButton * (maxLength - pushButton)
    }

    override val day = "06".toInt()
}

