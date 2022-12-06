package no.rodland.advent_2022

import debug

// template generated: 28/11/2022
// Fredrik Rødland 2022

object Day06 {

    fun partOne(input: String): Int {
        return findPos(input, 4)
    }

    fun partTwo(input: String): Int {
        return findPos(input, 14)
    }

    private fun findPos(input: String, length: Int): Int {
        val last4 = ArrayDeque<Char>()
        return input.indexOfFirst { c ->
            debug("debug:  $last4")
            if (last4.size == length) {
                last4.removeFirst()
            }
            last4.addLast(c)
            last4.toSet().size == length
        } + 1
    }
}
