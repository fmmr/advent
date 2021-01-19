package no.rodland.advent_2016

import kotlin.math.max
import kotlin.math.min

@Suppress("UNUSED_PARAMETER")
object Day21 {
    fun partOne(str: String): Int {
        return 2
    }

    fun partTwo(str: String): Int {
        return 2
    }

    fun swapPos(s: String, x: Int, y: Int): String {
        return (min(x, y) to max(x, y)).let { (a, o) ->
            val first = s[a]
            val last = s[o]
            val sub1 = s.substring(0, a)
            val sub2 = s.substring(a + 1, o)
            val sub3 = s.substring(o + 1)
            sub1 + last + sub2 + first + sub3
        }
    }

    fun swapLetter(s: String, x: Char, y: Char): String = swapPos(s, s.indexOf(x), s.indexOf(y))

    fun rotate(s: String, direction: String, steps: Int): String {
        return (s.indices)
                .map { s[getRotate(direction, it, steps, s.length)] }
                .joinToString("")
    }

    private fun getRotate(direction: String, idx: Int, steps: Int, length: Int) = (if (direction == "right") idx + (2 * length) - steps else idx + steps) % length

    fun rotatePos(s: String, x: Char): String {
        val idx = s.indexOf(x)
        val steps = idx + (if (idx >= 4) 2 else 1)
        return rotate(s, "right", steps)
    }

    fun reversePos(s: String, x: Int, y: Int): String {
        return (min(x, y) to max(x, y)).let { (a, o) ->
            val sub1 = s.substring(0, a)
            val sub2 = s.substring(a, o + 1)
            val sub3 = s.substring(o + 1)
            sub1 + sub2.reversed() + sub3
        }
    }

    fun move(s: String, x: Int, y: Int): String {
        val removed = s[x]
        val rest = s.removeRange(x..x)
        return rest.substring(0, y) + removed + rest.substring(y)
    }
}
