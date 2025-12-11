package no.rodland.advent_2025

import combinations
import no.rodland.advent.Day

// template generated: 10/12/2025
// Fredrik Rødland 2025

class Day10(val input: List<String>) : Day<Long, Long, List<Machine>> {

    private val machines = input.parse()

    override fun partOne(): Long {
        return machines.sumOf { machine -> machine.validCombos.minOf { it.size } }.toLong()
    }


    override fun partTwo(): Long {
        return 2
    }

    override fun List<String>.parse(): List<Machine> {
        return map { line ->
            // [..#.] (0,1,2) (0) (0,2) (1,3) (0,3) {41,34,21,24}
            // [..#.]
            val goal = line.substringBefore("]").substringAfter("[").reversed().map {
                when (it) {
                    '#' -> 1
                    else -> 0
                }
            }.joinToString("").toInt(2)
            //(0,1,2) (0) (0,2) (1,3) (0,3)
            val buttons = line.substringAfter("] ").substringBefore(" {").split(" ").map { group ->
                group.replace("[() ]".toRegex(), "").split(",").map { it.toInt() }.map { 1 shl it }.fold(0) { acc, i -> acc or i }
            }
            // {41,34,21,24}
            val joltages = line.substringAfter(" {").substringBefore("}").split(",").map { it.toInt() }
            Machine(goal,  buttons, joltages)
        }
    }

    override val day = "10".toInt()
}

data class Machine(val goal: Int,  val buttons: List<Int>, val joltages: List<Int>) {
    val combos = buttons.combinations()
    val validCombos = combos.filter { valid(it) }
    fun valid(combo: List<Int>) = combo.fold(0) { acc, x -> acc xor x } == goal
}