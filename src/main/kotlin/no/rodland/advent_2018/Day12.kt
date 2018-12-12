package no.rodland.advent_2018

object Day12 {
    fun partOne(input: List<String>, init: String): Int {
        val rules = input.map { it.split(" => ") }.map { it[0] to it[1] }.toMap()
        val generations = 20
        val plants = (1..generations).fold(init) { plants, gen ->
            val newGen = (-1..(plants.length)).map { idx ->
                val code = getCurrentCode(idx, plants)
                rules[code] ?: "."
            }.joinToString("")
            println("$gen: $newGen")
            newGen
        }
        return tverrSum(plants, generations)
    }

    private fun tverrSum(plants: String, gen: Int): Int {
        return plants.mapIndexed { idx, c ->
            if (c == '#') {
                idx - gen
            } else {
                0
            }
        }.sum()
    }

    fun partTwo(input: List<String>, init: String): Int {
        val rules = input.map { it.split(" => ") }.map { it[0] to it[1] }.toMap()
        val plants = (20..400).map { generation ->
            generation to (1..generation).fold(init) { plants, _ ->
                (-1..(plants.length)).map { idx ->
                    val code = getCurrentCode(idx, plants)
                    rules[code] ?: "."
                }.joinToString("")
            }
        }
        plants.forEachIndexed { idx, str -> println("$idx: ${tverrSum(str.second, str.first)}   $str") }
        return 2
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
}