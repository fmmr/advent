package no.rodland.advent_2024

import no.rodland.advent.Day
import kotlin.math.absoluteValue

// template generated: 18/11/2024
// Fredrik Rødland 2024

class Day01(val input: List<String>) : Day<Int, Int, List<Pair<Int, Int>>> {

    private val parsed = input.parse()
    private val firstList = parsed.map { it.first }
    private val lastList = parsed.map { it.second }

    override fun partOne(): Int {
        return firstList.sorted().zip(lastList.sorted()).sumOf { (it.first - it.second).absoluteValue }
    }

    override fun partTwo(): Int {
        return firstList.sumOf { first -> first * lastList.count { it == first } }
    }

    override fun List<String>.parse(): List<Pair<Int, Int>> {
        return map { line ->
            val split = line.split(" +".toRegex())
            split.first().toInt() to  split.last().toInt()
        }
    }

    override val day = "01".toInt()
}
