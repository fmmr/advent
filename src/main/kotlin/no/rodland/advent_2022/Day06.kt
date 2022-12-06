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
        var counter = 0
        input.forEach { c ->
            debug("debug: $counter $last4")
            if (last4.toSet().size == length) {
                return counter
            }
            if (last4.size == length) {
                last4.removeFirst()
            }
            last4.addLast(c)
            counter++
        }
        error("No solution found for: counter: $counter length: $length deque: $last4")
    }
}
