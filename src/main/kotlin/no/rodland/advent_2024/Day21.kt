package no.rodland.advent_2024

import no.rodland.advent.Day
import no.rodland.advent.Pos
import kotlin.math.absoluteValue
import kotlin.math.sign

// template generated: 21/12/2024
// Fredrik Rødland 2024


// copied from: https://github.com/kingsleyadio/adventofcode/blob/main/src/com/kingsleyadio/adventofcode/y2024/Day21.kt
@Suppress("PrivatePropertyName")
class Day21(val input: List<String>) : Day<Long, Long, List<String>> {
    private val NUMERIC = mapOf(
        '7' to Pos(0, 0), '8' to Pos(1, 0), '9' to Pos(2, 0),
        '4' to Pos(0, 1), '5' to Pos(1, 1), '6' to Pos(2, 1),
        '1' to Pos(0, 2), '2' to Pos(1, 2), '3' to Pos(2, 2),
        '.' to Pos(0, 3), '0' to Pos(1, 3), 'A' to Pos(2, 3),
    )

    private val DIRECTIONAL = mapOf(
        '.' to Pos(0, 0), '^' to Pos(1, 0), 'A' to Pos(2, 0),
        '<' to Pos(0, 1), 'v' to Pos(1, 1), '>' to Pos(2, 1),
    )

    override fun partOne(): Long {
        return solve(input, 2)
    }

    override fun partTwo(): Long {
        return solve(input, 25)
    }

    private fun solve(input: List<String>, robots: Int): Long = input.sumOf {
        val cache: MutableMap<Pair<Pair<Char, Char>, Int>, Long> = mutableMapOf()
        val lowest = "A$it".zipWithNext().sumOf { (a, b) -> simulate(a, b, 0, robots, cache) }
        val multiplier = it.substringBeforeLast('A').toLong()
        lowest * multiplier
    }

    private fun simulate(aa: Char, bb: Char, level: Int, robots: Int, cache: MutableMap<Pair<Pair<Char, Char>, Int>, Long>): Long {
        if (aa == bb) return 1
        val key = (aa to bb) to level
        return cache.getOrPut(key) {
            val keypad = if (level == 0) NUMERIC else DIRECTIONAL
            val a = keypad.getValue(aa)
            val b = keypad.getValue(bb)
            val invalid = keypad.getValue('.')
            val options = mutableListOf<List<Char>>()
            val (dx, dy) = b - a
            if (Pos(a.x + dx, a.y) != invalid) options += buildList<Char> {
                add('A')
                for (i in 1..dx.absoluteValue) if (dx.sign < 0) add('<') else add('>')
                for (i in 1..dy.absoluteValue) if (dy.sign < 0) add('^') else add('v')
                add('A')
            }
            if (Pos(a.x, a.y + dy) != invalid) options += buildList<Char> {
                add('A')
                for (i in 1..dy.absoluteValue) if (dy.sign < 0) add('^') else add('v')
                for (i in 1..dx.absoluteValue) if (dx.sign < 0) add('<') else add('>')
                add('A')
            }
            options.minOf { option ->
                if (level == robots) option.size.toLong() - 1
                else option.zipWithNext().sumOf { (a, b) -> simulate(a, b, level + 1, robots, cache) }
            }
        }
    }

    override fun List<String>.parse(): List<String> = this

    override val day = "21".toInt()
}
