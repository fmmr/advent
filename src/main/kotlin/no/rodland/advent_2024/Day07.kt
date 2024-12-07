package no.rodland.advent_2024

import no.rodland.advent.Day

// template generated: 07/12/2024
// Fredrik Rødland 2024

class Day07(val input: List<String>) : Day<Long, Long, List<Pair<Long, List<Long>>>> {

    private val parsed = input.parse()
    private val operatorsPart1: List<(Long, Long) -> Long> = listOf(Long::plus, Long::times)
    private val operatorsPart2 = operatorsPart1 + { l1, l2 -> "$l1$l2".toLong() }

    override fun partOne(): Long {
        return solve(operatorsPart1)
    }

    override fun partTwo(): Long {
        return solve(operatorsPart2)
    }

    private fun solve(operators: List<(Long, Long) -> Long>) = parsed
        .filter { canBeTrue(it.first, 0, it.second, operators) }
        .sumOf { it.first }

    private fun canBeTrue(sum: Long, acc: Long, values: List<Long>, operators: List<(Long, Long) -> Long>): Boolean {
        if (values.isEmpty()) return sum == acc
        return operators.any { canBeTrue(sum, it.invoke(acc, values.first()), values.drop(1), operators) }
    }

    override fun List<String>.parse(): List<Pair<Long, List<Long>>> {
        return map { line ->
            line.substringBefore(":").toLong() to line.substringAfter(": ").split(" ").map { it.toLong() }
        }
    }

    override val day = "07".toInt()
}

