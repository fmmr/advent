package no.rodland.advent_2022

// template generated: 28/11/2022
// Fredrik Rødland 2022

object Day06 {

    fun partOne(input: String): Int {
        return findPos(input, 4)
    }

    fun partTwo(input: String): Int {
        return findPos(input, 14)
    }

    private fun findPos(input: String, length: Int): Int = input.windowed(length).indexOfFirst { it.toSet().size == length } + length
}
