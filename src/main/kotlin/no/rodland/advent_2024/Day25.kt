package no.rodland.advent_2024

import no.rodland.advent.Day
import no.rodland.advent.toCave

// template generated: 25/12/2024
// Fredrik Rødland 2024

class Day25(val input: List<String>) : Day<Int, Long, Pair<List<String>, List<String>>> {

    private val parsed = input.parse()
    private val locks = parsed.first.map { it.split("\n").toCave() }
    private val keys = parsed.second.map { it.split("\n").toCave() }
    val x = 0..4
    val y = 0..6

    override fun partOne(): Int {
        val free = locks.map { lock ->
            x.map { x ->
                y.count { y -> lock[y][x] == '.' }
            }
        }
        val used = keys.map { lock ->
            x.map { x ->
                y.count { y -> lock[y][x] == '#' } - 1
            }
        }

        return used.sumOf { key ->
            free.count { lock ->
                key.zip(lock).all { it.second - it.first > 0 }
            }
        }
    }

    override fun partTwo(): Long {
        return 2
    }

    override fun List<String>.parse(): Pair<List<String>, List<String>> {
        val (locks, keys) = joinToString("\n").split("\n\n").partition { it.startsWith("#####") }
        return locks to keys
    }

    override val day = "25".toInt()
}
