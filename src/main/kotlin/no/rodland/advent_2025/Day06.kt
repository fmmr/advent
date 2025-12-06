package no.rodland.advent_2025

import no.rodland.advent.Day

// template generated: 06/12/2025
// Fredrik Rødland 2025

class Day06(val input: List<String>) : Day<Long, Long, Pair<List<Day06.Op>, List<List<Int>>>> {

    private val parsed = input.parse()

    override fun partOne(): Long {
        val (ops, nums) = parsed
        return ops.mapIndexed { index, op -> op.calc(nums[index]) }.sum()
    }

    override fun partTwo(): Long {
        return 2
    }

    override fun List<String>.parse(): Pair<List<Op>, List<List<Int>>> {
        val ops = last().split(" +".toRegex()).filterNot { it.isEmpty() }.map { Op.op(it) }
        val nums = dropLast(1).map { line -> line.split("\\s+".toRegex()).filterNot { it.isEmpty() }.map { it.toInt() } }.transpose()
        return ops to nums
    }

    override val day = "05".toInt()

    enum class Op {
        PLUS, MULT;

        fun calc(nums: List<Int>): Long = when (this) {
            PLUS -> nums.fold(0) { acc, i -> acc + i }
            MULT -> nums.fold(1) { acc, i -> acc * i }
        }

        companion object {
            fun op(str: String) = when (str) {
                "+" -> PLUS
                "*" -> MULT
                else -> error("unknown op $str")
            }
        }
    }

}
fun List<List<Int>>.transpose(): List<List<Int>> {
    val width = first().size
    val height = size
    return (0..<width).map { j ->
        (0..<height).map { i ->
            get(i)[j]
        }.toList()
    }
}
