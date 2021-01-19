package no.rodland.advent_2016

import no.rodland.advent.permutations
import kotlin.math.max
import kotlin.math.min

@Suppress("UNUSED_PARAMETER")
object Day21 {
    private val swapRE = """swap position (\d+) with position (\d+)""".toRegex()
    private val swapLetterRE = """swap letter ([a-z]) with letter ([a-z])""".toRegex()
    private val reverseRE = """reverse positions (\d+) through (\d+)""".toRegex()
    private val rotateRE = """rotate (left|right) (\d+) steps?""".toRegex()
    private val rotatePosRE = """rotate based on position of letter ([a-z])""".toRegex()
    private val moveRE = """move position (\d+) to position (\d+)""".toRegex()

    fun partOne(str: String, list: List<String>): String {
        return parseInput(list).runningFold(str) { acc, func -> func.f(acc) }.last()
    }

    fun partTwo(str: String, list: List<String>): String {
        val part2 = parseInput(list).reversed().fold(str) { acc, line -> line.r(acc) }
        val back = partOne(part2, list)
        println("str: $str, part2: $part2, back: $back - equals: ${str == back}")
        return part2
    }

    class Func(val func: (String) -> String, val reversed: (String) -> String = func) {
        fun f(s: String) = func(s)
        fun r(s: String) = reversed(s)
    }

    private fun parseInput(list: List<String>) = list.map { line ->
        when {
            swapRE.matches(line) -> Func({ s: String -> swapRE.find(line)!!.destructured.let { swapPos(s, it.component1().toInt(), it.component2().toInt()) } })
            line.startsWith("swap letter") -> Func({ s -> swapLetterRE.find(line)!!.destructured.let { swapLetter(s, it.component1()[0], it.component2()[0]) } })
            line.startsWith("reverse") -> Func({ s -> reverseRE.find(line)!!.destructured.let { reversePos(s, it.component1().toInt(), it.component2().toInt()) } })
            rotateRE.matches(line) -> Func({ s -> rotateRE.find(line)!!.destructured.let { rotate(s, it.component1(), it.component2().toInt()) } }, { s -> rotateRE.find(line)!!.destructured.let { rotateReversed(s, it.component1(), it.component2().toInt()) } })
            moveRE.matches(line) -> Func({ s -> moveRE.find(line)!!.destructured.let { move(s, it.component1().toInt(), it.component2().toInt()) } }, { s -> moveRE.find(line)!!.destructured.let { moveReversed(s, it.component1().toInt(), it.component2().toInt()) } })
            rotatePosRE.matches(line) -> Func({ s -> rotatePos(s, rotatePosRE.find(line)!!.destructured.component1()[0]) }, { s -> rotatePosReversed(s, rotatePosRE.find(line)!!.destructured.component1()[0]) })
            else -> error("unable to parse $line")
        }
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

    fun rotateReversed(s: String, direction: String, steps: Int): String = rotate(s, if (direction == "left") "right" else "left", steps)

    fun rotate(s: String, direction: String, steps: Int): String {
        return (s.indices)
                .map { s[getRotate(direction, it, steps, s.length)] }
                .joinToString("")
    }

    private fun getRotate(direction: String, idx: Int, steps: Int, length: Int) = (if (direction == "right") idx + (2 * length) - steps else idx + steps) % length

    fun rotatePosReversed(s: String, x: Char): String {
        return s.permutations().first { perm -> rotatePos(perm, x) == s }
    }

    fun rotatePos(s: String, x: Char): String {
        val idx = s.indexOf(x)
        val steps = idx + (if (idx >= 4) 2 else 1)
        val rotate = rotate(s, "right", steps)
//        val newIdx = rotate.indexOf(x)
//        println("$s idx: $idx, $rotate idx: $newIdx")
        return rotate
    }

    fun reversePos(s: String, x: Int, y: Int): String {
        return (min(x, y) to max(x, y)).let { (a, o) ->
            val sub1 = s.substring(0, a)
            val sub2 = s.substring(a, o + 1)
            val sub3 = s.substring(o + 1)
            sub1 + sub2.reversed() + sub3
        }
    }

    fun moveReversed(s: String, x: Int, y: Int): String = move(s, y, x)

    fun move(s: String, x: Int, y: Int): String {
        val removed = s[x]
        val rest = s.removeRange(x..x)
        return rest.substring(0, y) + removed + rest.substring(y)
    }
}
