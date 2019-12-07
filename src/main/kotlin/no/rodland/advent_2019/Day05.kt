package no.rodland.advent_2019

object Day05 {
    fun partOne(input: List<Int>, start: Int = 1): Int {
        val computer = IntCodeComputer(input, mutableListOf(start))
        return computer.runProgram()
    }
}