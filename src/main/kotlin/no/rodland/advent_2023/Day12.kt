package no.rodland.advent_2023

import no.rodland.advent.Day

// template generated: 12/12/2023
// Fredrik Rødland 2023

class Day12(val input: List<String>) : Day<Int, Long, List<Day12.Springs>> {

    private val parsed = input.parse()

    override fun partOne(): Int {
        return parsed.sumOf { (pattern, numbers) ->
            count(pattern, numbers).toInt()
        }
    }

    override fun partTwo(): Long {
        return parsed.map { it.expand() }.sumOf { (pattern, numbers) ->
            count(pattern, numbers)
        }
    }

    private val cache = mutableMapOf<Pair<String, List<Int>>, Long>()
    private fun count(pattern: String, groups: List<Int>): Long {
        return cache.getOrPut(pattern to groups) {
            if (pattern.isEmpty()) {
                return if (groups.isEmpty()) 1L else 0L
            }
            when (pattern[0]) {
                '.' -> count(pattern.drop(1), groups)
                '#' -> countHash(pattern, groups)
                '?' -> count(pattern.drop(1), groups) + count(pattern.replaceFirst('?', '#'), groups)
                else -> error("Should not happen")
            }
        }
    }

    private fun countHash(pattern: String, groups: List<Int>): Long {
        if (groups.isEmpty()) return 0L
        val number = groups.first()
        return when {
            groups.isEmpty() -> 0L
            pattern.length < number -> 0L
            '.' in pattern.substring(0..<number) -> 0L
            pattern.length == number && groups.size == 1 -> 1L
            pattern.length == number && groups.size != 1 -> 0L
            pattern[number] == '#' -> 0L
            else -> count(pattern.drop(number + 1), groups.drop(1))
        }
    }


    // https://www.reddit.com/r/adventofcode/comments/18ge41g/comment/kd0wo99/
    // https://github.com/eagely/adventofcode/blob/main/src/main/kotlin/solutions/y2023/Day12.kt
    private val mem = hashMapOf<Pair<String, List<Int>>, Long>()

    @Suppress("unused")
    private fun eagely(config: String, groups: List<Int>): Long {
        if (groups.isEmpty()) return if ("#" in config) 0 else 1
        if (config.isEmpty()) return 0

        return mem.getOrPut(config to groups) {
            var result = 0L
            if (config.first() in ".?")
                result += eagely(config.drop(1), groups)
            if (config.first() in "#?" && groups.first() <= config.length && "." !in config.take(groups.first()) && (groups.first() == config.length || config[groups.first()] != '#'))
                result += eagely(config.drop(groups.first() + 1), groups.drop(1))
            result
        }
    }

    @Suppress("unused")
    fun bruteForce(): Int {
        return parsed.sumOf { (patterns, numbers) ->
            expand(patterns)
                .map { pattern -> pattern.split("\\.+".toRegex()).filterNot { it.isEmpty() } }
                .filter { it.size == numbers.size }
                .map { strings -> strings.map { it.length } }
                .count { it == numbers }
        }
    }

    fun expand(input: String): List<String> {
        val count = input.count { it == '?' }
        val numChars = 1 shl count  // 2^count
        fun Int.toMapString(): String = toString(2)
            .padStart(count, '0')
            .replace("0", ".")
            .replace("1", "#")

        return (0..<numChars)
            .map { width -> width.toMapString() }
            .map { mask -> mask.fold(input) { acc: String, c: Char -> acc.replaceFirst("?", c.toString()) } }
    }

    data class Springs(val pattern: String, val numbers: List<Int>) {
        fun expand() = Springs(List(5) { pattern }.joinToString("?"), List(5) { numbers }.flatten())
    }

    override fun List<String>.parse(): List<Springs> {
        return map { line ->
            val (first, second) = line.split(" ")
            Springs(first, second.split(",").map { it.toInt() })
        }
    }

    override val day = "12".toInt()
}
