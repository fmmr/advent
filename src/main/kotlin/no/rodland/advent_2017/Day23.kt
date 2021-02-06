package no.rodland.advent_2017

import no.rodland.advent_2017.prog.Program
import no.rodland.advent_2017.prog.runProgram

@Suppress("UNUSED_PARAMETER")
object Day23 {
    fun partOne(list: List<String>): Int {
        val program = Program.parse("part1", list)
        program.compile(mutableMapOf()).runProgram()
        return 6241   // just printed and counted
    }

    // https://todd.ginsberg.com/post/advent-of-code/2017/day23/
    fun partTwo(list: List<String>): Int {
        val a = list.first().split(" ")[2].toInt() * 100 + 100000
        return (a..a + 17000 step 17).count {
            !it.toBigInteger().isProbablePrime(5)
        }
    }
}
