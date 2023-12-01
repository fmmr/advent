package no.rodland.advent_2023

// template generated: 01/12/2023
// Fredrik Rødland 2023

@Suppress("unused", "MemberVisibilityCanBePrivate")
class Day01(val input: List<String>) {


    fun partOne(): Long {
        return input.parse().sum().toLong()
    }

    fun partTwo(): Long {
        return input.parse2().sum().toLong()
    }

    fun List<String>.parse(): List<Int> {
        return map { str ->
            val d = str
                .toCharArray()
                .map { c ->
                    if (c.isDigit()) c else null
                }
                .filterNotNull()
            ("" + d.first().digitToInt() + d.last().digitToInt()).toInt()
        }
    }

    fun List<String>.parse2(): List<Int> {
        return map { line ->
            fix(line)
        }
    }

    fun fix(line: String): Int {
        val firstOcc = DIGIT_MAPPING.map { (d, l) ->
            val m = listOfNotNull(
                if (line.indexOf("$d") >= 0) {
                    line.indexOf("$d")
                } else null,
                if (line.indexOf(l) >= 0) {
                    line.indexOf(l)
                } else null
            ).minOrNull()

            d to m
        }.filter { it.second != null }
        val lastOcc = DIGIT_MAPPING.map { (d, l) ->
            val m = listOfNotNull(
                if (line.lastIndexOf("$d") >= 0) {
                    line.lastIndexOf("$d")
                } else null,
                if (line.lastIndexOf(l) >= 0) {
                    line.lastIndexOf(l)
                } else null
            ).maxOrNull()
            d to m
        }.filter { it.second != null }

        val first = firstOcc.minBy { it.second!! }.first
        val last = lastOcc.maxBy { it.second!! }.first
        return ("" + first + last).toInt()
    }

    companion object {
        val DIGIT_MAPPING = mapOf(
            1 to "one",
            2 to "two",
            3 to "three",
            4 to "four",
            5 to "five",
            6 to "six",
            7 to "seven",
            8 to "eight",
            9 to "nine",
        )
    }
}
