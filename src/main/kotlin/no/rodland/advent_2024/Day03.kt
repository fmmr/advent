package no.rodland.advent_2024

import no.rodland.advent.Day

// template generated: 03/12/2024
// Fredrik Rødland 2024

class Day03(val input: List<String>) : Day<Long, Long, List<String>> {
    private val multRegEx = """mul\((\d{1,3}),(\d{1,3})\)""".toRegex()

    private val parsed = input.parse()

    override fun partOne(): Long {
        return parsed
            .flatMap { line ->
                multRegEx.findAll(line).toList().map { mr ->
                    mr.groupValues[1].toInt() * mr.groupValues[2].toInt()
                }
            }
            .sum()
            .toLong()
    }

    override fun partTwo(): Long {
        val matches = """mul\((\d{1,3}),(\d{1,3})\)|do\(\)|don't\(\)""".toRegex()
        var inDoMode = true
        return parsed
            .flatMap { line ->
                sequence {
                    matches
                        .findAll(line)
                        .forEach { mr ->
                            when {
                                mr.value == "do()" -> inDoMode = true
                                mr.value == "don't()" -> inDoMode = false
                                multRegEx.matches(mr.value) && inDoMode -> yield(mr.groupValues[1].toInt() * mr.groupValues[2].toInt())
                            }
                        }
                }
            }
            .sum()
            .toLong()
    }

    override fun List<String>.parse(): List<String> = this

    override val day = "03".toInt()
}
