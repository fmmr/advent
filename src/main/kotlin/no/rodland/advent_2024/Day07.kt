package no.rodland.advent_2024

import no.rodland.advent.Day

// template generated: 07/12/2024
// Fredrik Rødland 2024

class Day07(val input: List<String>) : Day<Long, Long, List<Pair<Long, List<Long>>>> {

    private val parsed = input.parse()

    override fun partOne(): Long {
        return parsed
            .filter { canBeTrue(it.first, 0, it.second, listOf(Long::plus, Long::times)) }
            .sumOf { it.first }
    }

    override fun partTwo(): Long {
        return parsed
            .filter { canBeTrue(it.first, 0, it.second, listOf(Long::plus, Long::times, { l1, l2 -> "$l1$l2".toLong() })) }
            .sumOf { it.first }
    }

    private fun canBeTrue(sum: Long, acc: Long, values: List<Long>, operators: List<(Long, Long) -> Long>): Boolean {
        if (values.isEmpty()) return sum == acc
        val next = values.first()
        return operators.any { canBeTrue(sum, it.invoke(acc, next), values.drop(1), operators) }
    }

   override fun List<String>.parse(): List<Pair<Long, List<Long>>> {
        return map { line ->
            line.substringBefore(":").toLong() to line.substringAfter(": ").split(" ").map { it.toLong() }
        }
    }

    override val day = "07".toInt()
}

