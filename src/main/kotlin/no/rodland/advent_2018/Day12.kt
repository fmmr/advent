package no.rodland.advent_2018

object Day12 {
    fun partOne(input: List<String>, init: String): Int {
        val rules = input.map { it.split(" => ") }.map { it[0] to it[1] }.toMap()
        val plants = (1..20).fold(init) { plants, gen ->
            val newGen = (-1..(plants.length)).map { idx ->
                val code = getCurrentCode(idx, plants)
                rules[code] ?: "."
            }.joinToString("")
            println("$gen: $newGen")
            newGen
        }
        return plants.mapIndexed { idx, c ->
            if (c == '#') {
                idx - 20
            } else {
                0
            }
        }.sum()
    }

    private fun getCurrentCode(idx: Int, plants: String): String {
        return (-2..2).map {
            if ((idx + it) < 0 || (idx + it) >= plants.length) {
                "."
            } else {
                plants[idx + it].toString()
            }
        }.joinToString("")
    }

    fun partTwo(list: List<String>): Int {
        return 2
    }
}