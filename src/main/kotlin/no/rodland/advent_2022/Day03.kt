package no.rodland.advent_2022


// template generated: 28/11/2022
// Fredrik Rødland 2022

object Day03 {
    fun partOne(list: List<String>): Int {
        return list.map { Bag(it) }
            .map { it.common() }
            .sumOf { it.priority() }
    }

    fun partTwo(list: List<String>): Int {
        return list.map { Bag(it) }
            .chunked(3)
            .map { group ->
                group.map { it.toSet() }
                    .reduce { acc, chars -> acc.intersect(chars) }
                    .single()
            }
            .sumOf { it.priority() }
    }

    fun Char.priority(): Int = if (this >= 'a') code - 96 else code - 38

    class Bag(val input: String) {
        operator fun component1(): List<Char> = input.toList().subList(0, input.length / 2)
        operator fun component2(): List<Char> = input.toList().subList(input.length / 2, input.length)

        fun common() = component1().intersect(component2().toSet()).single()
        fun toSet() = input.toSet()
    }
}

