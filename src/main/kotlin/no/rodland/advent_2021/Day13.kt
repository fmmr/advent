package no.rodland.advent_2021

import no.rodland.advent.Pos

object Day13 {
    fun partOne(list: List<String>): Int {
        val (grid, instructions) = list.parse()
        return grid.foldPaper(instructions[0]).size
    }

    fun partTwo(list: List<String>): String {
        val (grid, instructions) = list.parse()
        val endGrid = instructions.fold(grid) { acc, instruction -> acc.foldPaper(instruction) }
        val code = endGrid.asPrintedGrid()
        println("CODE:")
        println(code)
        println()
        return code
    }

    private fun Set<Pos>.asPrintedGrid(): String {
        return (0..maxOf { it.y }).joinToString("\n") { y -> (0..maxOf { it.x }).joinToString("") { x -> char(x, y) } }
    }

    private fun Set<Pos>.char(x: Int, y: Int) = if (Pos(x, y) in this) "#" else " "

    private fun Set<Pos>.foldPaper(instruction: Instruction): Set<Pos> = map { pos -> Pos(value(instruction, pos.x, 'x'), value(instruction, pos.y, 'y')) }.toSet()

    private fun value(instruction: Instruction, value: Int, axis: Char): Int {
        return if (instruction.axis != axis || value < instruction.value) {
            value
        } else {
            instruction.value - (value - instruction.value)
        }
    }


    data class Instruction(val axis: Char, val value: Int) {
        constructor(str: String) : this(str.substringBefore("=").last(), str.substringAfter("=").toInt())
    }

    private fun List<String>.parse(): Pair<Set<Pos>, List<Instruction>> {
        val (g, i) = filterNot { it.isEmpty() }.partition { it.contains(',') }
        return g.map { it.split(",") }.map { (x, y) -> Pos(x.toInt(), y.toInt()) }.toSet() to i.map { Instruction(it) }
    }
}



