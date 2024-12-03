package no.rodland.advent_2024

import no.rodland.advent.Day

// template generated: 03/12/2024
// Fredrik Rødland 2024

class Day03(val input: List<String>) : Day<Int, Int, String> {
    private val mulRegex = """mul\((\d{1,3}),(\d{1,3})\)""".toRegex()
    private val doRegex = """do\(\)""".toRegex()
    private val dontRegex = """don't\(\)""".toRegex()
    private val matches = """$mulRegex|$doRegex|$dontRegex""".toRegex()

    private val parsed = input.parse()

    override fun partOne(): Int {
        return mulRegex.findAll(parsed).toList().sumOf { mr -> mr.groupValues[1].toInt() * mr.groupValues[2].toInt() }
    }

    override fun partTwo(): Int {
        var inDoMode = true
        return sequence {
            matches
                .findAll(parsed)
                .forEach { mr ->
                    when {
                        mr.value == "do()" -> inDoMode = true
                        mr.value == "don't()" -> inDoMode = false
                        inDoMode && mulRegex.matches(mr.value) -> yield(mr.groupValues[1].toInt() * mr.groupValues[2].toInt())
                    }
                }
        }.sum()
    }

    override fun List<String>.parse(): String = this.joinToString("")

    override val day = "03".toInt()
}
