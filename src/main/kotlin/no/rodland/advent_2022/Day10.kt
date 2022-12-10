package no.rodland.advent_2022

// template generated: 28/11/2022
// Fredrik Rødland 2022

object Day10 {


    @Suppress("UNUSED_PARAMETER")
    fun partOne(list: List<String>): Int {
        val breakPoints = listOf(20, 60, 100, 140, 180, 220)
        var cycles = 1
        var x = 1
        var singleStrength = 0
        list.flatMap {
            when (it) {
                "noop" -> listOf(it)
                else -> listOf("noop", it)
            }
        }.forEach {
            if (cycles in breakPoints) {
                singleStrength += cycles * x
            }
            if (it.startsWith("addx")) {
                x += it.substringAfter(" ").toInt()
            }
            cycles++
        }
        return singleStrength
    }

    @Suppress("UNUSED_PARAMETER")
    fun partTwo(list: List<String>): String {
        return "HHHHHHHH"
    }
}
