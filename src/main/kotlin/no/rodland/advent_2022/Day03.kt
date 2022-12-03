package no.rodland.advent_2022

// template generated: 28/11/2022
// Fredrik Rødland 2022

object Day03 {
    fun partOne(list: List<String>): Int {
        return list.map { Bag(it) }.map { it.common() }.sumOf { it.priority() }
    }

    fun partTwo(list: List<String>): Long {
        return 2
    }

    fun Char.priority():Int {
        return when {
            this >= 'a' -> code - 96
            else -> code - 38
        }
    }


    class Bag(val input: String) {
        val compartment1 = component1()
        val compartment2 = component2()

        operator fun component1(): List<Char> = input.toCharArray().toList().subList(0, input.length / 2)
        operator fun component2(): List<Char> = input.toCharArray().toList().subList(input.length / 2, input.length)

        fun common() = compartment1.intersect(compartment2).distinct().first()
    }
}

