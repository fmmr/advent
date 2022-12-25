package no.rodland.advent_2022

import kotlin.math.pow

// template generated: 23/12/2022
// Fredrik Rødland 2022

@Suppress("unused")
class Day25(val input: List<String>) {

    fun partOne(): String {
        val sum = input
            .map {
                it.toCharArray()
                    .reversed()
                    .map { c -> c.toNum() }
                    .toIntArray()
                    .mapIndexed { idx, i -> 5.0.pow(idx).toLong() * i }
            }
            .sumOf {
                it.sum()
            }
        return encode(sum)
    }

    private val DIGITS = "=-012"
    private fun encode(number: Long): String {
        if (number == 0L) return "0"
        var b = number
        return buildString {
            while (b > 0) {
                val m = (b + 2) % 5
                b = (b + 2) / 5
                append(DIGITS[m.toInt()])
            }
        }.reversed()
    }

    fun partTwo(): Long {
        return 2
    }

    private fun Char.toNum(): Int {
        return when (this) {
            '=' -> -2
            '-' -> -1
            else -> digitToInt()
        }
    }
}

