package no.rodland.advent_2022

import println

// template generated: 28/11/2022
// Fredrik Rødland 2022

object Day10 {

    fun partOne(list: List<String>): Int {
        var cycles = 0
        var x = 1
        var singleStrength = 0
        list.flatMap {
            when (it) {
                "noop" -> listOf(it)
                else -> listOf("noop", it)
            }
        }.forEach {
            cycles++
            if ((cycles - 20) % 40 == 0) {
                singleStrength += cycles * x
            }
            if (it.startsWith("addx")) {
                x += it.substringAfter(" ").toInt()
            }
        }
        return singleStrength
    }


    fun partTwo(list: List<String>): Int {
        val screen = mutableListOf<String>()
        var row = ""
        var cycles = 0
        var x = 1
        list.flatMap {
            when (it) {
                "noop" -> listOf(it)
                else -> listOf("noop", it)
            }
        }.forEach {
            row += if (x.lit(cycles % 40)) "[]" else "  "
            cycles++
            if (cycles % 40 == 0) {
                screen.add(row)
                row = ""
            }
            if (it.startsWith("addx")) {
                x += it.substringAfter(" ").toInt()
            }
        }
        screen.forEach { it.println() }
        return 2
    }

    private fun Int.lit(pos: Int): Boolean = this in (pos - 1)..(pos + 1)
}
