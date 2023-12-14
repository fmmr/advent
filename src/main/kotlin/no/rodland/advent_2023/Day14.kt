package no.rodland.advent_2023

import no.rodland.advent.Day

// template generated: 14/12/2023
// Fredrik Rødland 2023

class Day14(val input: List<String>) : Day<Int, Int, List<String>> {

    private val grid = input.parse()

    override fun partOne(): Int {
        return grid.rotateLeft().tilt().load()
    }

    override fun partTwo(): Int {
        var cycle = grid.rotateLeft()
        val seenStateIn = mutableMapOf(cycle to 0)
        val number = 1_000_000_000
        // loop through until we see a map we've already seen and cycle (a few times) forward until we
        // would have reached 1_000_000_000
        for (i in 1..number) {
            cycle = cycle.cycle()
            val lastSeen = seenStateIn.getOrPut(cycle) { i }
            if (lastSeen != i) {
                val mod = i - lastSeen
                val diff = number - i
                val times = diff % mod
                repeat(times) { cycle = cycle.cycle() }
                break
            }
        }
        return cycle.load()
    }

    private fun List<String>.reverseEachLine() = map { it.reversed() }
    private fun List<String>.load() = sumOf { it.load() }
    private fun List<String>.cycle() = tilt().rotateLeft().reverseEachLine()
        .tilt().reversed().rotateLeft().reverseEachLine()
        .tilt().reversed().rotateLeft().reverseEachLine()
        .tilt().reversed().rotateLeft().asReversed().reverseEachLine()

    private fun List<String>.tilt() = map { line -> collapsed(line) }

    private fun String.load() =
        foldIndexed(0) { index: Int, acc: Int, c: Char ->
            if (c == 'O') {
                acc + (length - index)
            } else {
                acc
            }
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

    private fun hopOne(line: String): String {
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

    override fun List<String>.parse(): List<String> = this

    private fun List<String>.rotateLeft(): List<String> {
        return indices.reversed().map { j -> indices.map { i -> get(i)[j] }.joinToString("") }
    }

    @Suppress("unused")
    private fun List<String>.print(str: String? = null): List<String> {
        if (str != null) {
            println(str)
        }
        forEach { println(it) }
        println()
        return this
    }

    override val day = "14".toInt()
}
