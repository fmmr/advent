package no.rodland.advent_2021

typealias Caves = Map<Day12.Cave, List<Day12.Cave>>

object Day12 {  // Graph traversal dfs
    fun partOne(list: List<String>): Int {
        return solve(list.toCaves())
    }

    fun partTwo(list: List<String>): Int {
        return solve(list.toCaves(), true)
    }

    private fun List<String>.toCaves() = map { it.split('-') }
        .flatMap { (v1, v2) -> listOf(Cave(v1) to Cave(v2), Cave(v2) to Cave(v1)) }
        .groupBy({ it.first }, { it.second })


    private fun solve(initialGraph: Caves, visitSingleNodeTwice: Boolean = false, current: Cave = Cave("start"), visited: Set<Cave> = hashSetOf(current)): Int {
        return initialGraph[current]!!.sumOf {
            when {
                it.end -> 1
                it.start -> 0
                it.big -> solve(initialGraph, visitSingleNodeTwice, it, visited = visited + it)
                it !in visited -> solve(initialGraph, visitSingleNodeTwice, it, visited = visited + it)
                visitSingleNodeTwice -> solve(initialGraph, false, it, visited + it)
                else -> 0
            }
        }
    }

    data class Cave(val code: String) {
        val big: Boolean = code[0].isUpperCase()
        val start = code == "start"
        val end = code == "end"
    }
}
