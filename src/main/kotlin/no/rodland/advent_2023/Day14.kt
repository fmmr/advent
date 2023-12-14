package no.rodland.advent_2023

import no.rodland.advent.Day

// template generated: 14/12/2023
// Fredrik Rødland 2023

class Day14(val input: List<String>) : Day<Int, Long, List<String>> {

    private val grid = input.parse()

    override fun partOne(): Int {
       return grid.map { line ->
            val collapsed = collapsed(line)
            val weight = collapsed.foldIndexed(0) { index: Int, acc: Int, c: Char ->
                if (c == 'O') {
                    acc + (line.length - index)
                } else {
                    acc
                }
            }
            weight
        }.sum()

    }

    fun collapsed(line: String): String {
        var previousLine = line
        var newLine = hopOne(line)
        while (newLine != previousLine) {
            previousLine = newLine
            newLine = hopOne(newLine)
        }
        return newLine
    }

    fun hopOne(line: String): String {
        val array = line.toCharArray()
        line.forEachIndexed { idx, c ->
            if (line[idx] == '.' && (idx + 1) < line.length && line[idx + 1] != '#') {
                val tmp = line[idx]
                array[idx] = line[idx + 1]
                array[idx + 1] = tmp
            }
        }
        return array.joinToString("")
    }

    override fun partTwo(): Long {
        return 2
    }

    override fun List<String>.parse(): List<String> {
        val width = first().length
        val height = size
        return (0..<width).map { j ->
            (0..<height).map { i ->
                get(i)[j]
            }.joinToString("")
        }
    }

    override val day = "14".toInt()
}
