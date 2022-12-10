package no.rodland.advent_2022

import getCharForTyping
import println

// template generated: 28/11/2022
// Fredrik Rødland 2022

object Day10 {

    fun partOne(list: List<String>): Int {
        var cycles = 0
        var x = 1
        var singleStrength = 0
        list.flatMapCommands()
            .forEach {
                cycles++
                if ((cycles - 20) % 40 == 0) {
                    singleStrength += cycles * x
                }
                if (it != "noop") {
                    x += it.substringAfter(" ").toInt()
                }
            }
        return singleStrength
    }

    fun partTwo(list: List<String>): Int {
        var cycles = 0
        var x = 1
        buildString {
            list.flatMapCommands()
                .forEach {
                    append(getCharForTyping { x.lit(cycles % 40) })
                    cycles++
                    if (cycles % 40 == 0) {
                        appendLine()
                    }
                    if (it.startsWith("addx")) {
                        x += it.substringAfter(" ").toInt()
                    }
                }
        }.println()
        return 2
    }

    private fun List<String>.flatMapCommands() = flatMap {
        if (it == "noop") listOf(it) else listOf("noop", it)
    }

    private fun Int.lit(pos: Int): Boolean = this in (pos - 1)..(pos + 1)
}
