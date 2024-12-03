package no.rodland.advent_2024

import no.rodland.advent.Day

// template generated: 03/12/2024
// Fredrik Rødland 2024

class Day03(val input: List<String>) : Day<Int, Int, String> {
    private val multRegEx = """mul\((\d{1,3}),(\d{1,3})\)""".toRegex()

    private val parsed = input.parse()

    override fun partOne(): Int {
        return multRegEx.findAll(parsed).toList().sumOf { mr -> mr.groupValues[1].toInt() * mr.groupValues[2].toInt() }
    }

    override fun partTwo(): Int {
        val matches = """${multRegEx}|do\(\)|don't\(\)""".toRegex()
        var inDoMode = true
        return sequence {
            matches
                .findAll(parsed)
                .forEach { mr ->
                    when {
                        mr.value == "do()" -> inDoMode = true
                        mr.value == "don't()" -> inDoMode = false
                        inDoMode && multRegEx.matches(mr.value) -> yield(mr.groupValues[1].toInt() * mr.groupValues[2].toInt())
                    }
                }
        }.sum()
    }

    override fun List<String>.parse(): String = this.joinToString("")

    override val day = "03".toInt()
}
