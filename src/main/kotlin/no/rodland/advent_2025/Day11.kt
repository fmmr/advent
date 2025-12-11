package no.rodland.advent_2025

import no.rodland.advent.Day

// template generated: 11/12/2025
// Fredrik Rødland 2025

class Day11(val input: List<String>) : Day<Long, Long, Map<String, Set<String>>> {

    private val graph = input.parse()

    override fun partOne(): Long {
        return countPaths("you", emptySet())
    }

    override fun partTwo(): Long {
        return 2
    }

    private fun countPaths(current: String, visited: Set<String>): Long {
        if (current == "out") return 1L
        val connections = graph[current]!!
        return connections
            .filter { it !in visited }
            .sumOf { countPaths(it, visited + current) }
    }

    override fun List<String>.parse(): Map<String, Set<String>> {
        return associate { line ->
            val device = line.substringBefore(": ")
            val connections = line.substringAfter(": ").split(" ").toSet()
            device to connections
        }
    }

    override val day = "11".toInt()
}
