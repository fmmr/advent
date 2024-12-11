package no.rodland.advent_2024

import no.rodland.advent.Day

// template generated: 11/12/2024
// Fredrik Rødland 2024

class Day11(val input: List<String>) : Day<Long, Long, List<Long>> {

    private val parsed = input.parse()
    override fun partOne(): Long {
        val memoization = mutableMapOf<Pair<Long, Int>, Long>()
        return parsed.map { it.numberStones(25, memoization) }.sumOf { it }
    }

    override fun partTwo(): Long {
        val memoization = mutableMapOf<Pair<Long, Int>, Long>()
        return parsed.map { it.numberStones(75, memoization) }.sumOf { it }
    }

    private fun Long.numberStones(i: Int, memoization: MutableMap<Pair<Long, Int>, Long>): Long {
        if (i == 0) return 1
        memoization[this to i]?.let { return it }
        return this.runRules().sumOf { it.numberStones(i - 1, memoization) }.also { memoization[this to i] = it }
    }

    private fun Long.runRules(): List<Long> {
        return when {
            this == 0L -> listOf(1L)
            this.evenDigits() -> this.split()
            else -> listOf(2024 * this)
        }
    }

    private fun Long.split(): List<Long> = "$this".let {
        val middle = "$this".length / 2
        listOf(it.substring(0, middle).toLong(), it.substring(middle).toLong())
    }

    private fun Long.evenDigits(): Boolean = "$this".length % 2 == 0

    @Suppress("unused")
    fun partOneBruteForce(): Long {
        var l = parsed
        repeat(25) {
            l = l.fold(emptyList()) { acc, num ->
                acc + num.runRules()
            }
        }
        return l.size.toLong()
    }

    override fun List<String>.parse(): List<Long> {
        return first().split("\\s+".toRegex()).map { it.toLong() }
    }

    override val day = "11".toInt()
}