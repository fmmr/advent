package no.rodland.advent_2022

// template generated: 28/11/2022
// Fredrik Rødland 2022

object Day10 {

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


    fun partTwo(list: List<String>): String {
        val breakPoints = listOf(40, 80, 120, 160, 200, 240)
        val screen = mutableListOf<String>()
        var row = ""
        var cycles = 1
        var x = 1
        list.flatMap {
            when (it) {
                "noop" -> listOf(it)
                else -> listOf("noop", it)
            }
        }.forEach {
            row += if (x.lit((cycles - 1) % 40)) "#" else " "
            if (cycles in breakPoints) {
                screen.add(row)
                row = ""
            }
            if (it.startsWith("addx")) {
                x += it.substringAfter(" ").toInt()
            }
            cycles++
        }
        return screen.joinToString("\n")
    }

    private fun Int.lit(pos: Int): Boolean = this in (pos - 1)..(pos + 1)
}
