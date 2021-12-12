package no.rodland.advent_2021

object Day12 {
    fun partOne(list: List<String>): Int {
        return solve(list.toGraph(), Cave("start"))
    }

    fun partTwo(list: List<String>): Int {
        return solve(list.toGraph(), Cave("start"), visitSingleNodeTwice = true)
    }

    private fun List<String>.toGraph() = map { it.split('-') }
        .flatMap { (v1, v2) -> listOf(Cave(v1) to Cave(v2), Cave(v2) to Cave(v1)) }
        .groupBy { it.first }
        .mapValues { (_, v) -> v.map { it.second } }


    private fun solve(initialGraph: Map<Cave, List<Cave>>, current: Cave, visitSingleNodeTwice: Boolean = false, visited: Set<Cave> = hashSetOf(current)): Int {
        val rec = initialGraph[current]!!
        return rec.sumOf {
            when {
                it.end -> 1
                it.start -> 0
                it.big -> solve(initialGraph, it, visitSingleNodeTwice, visited = visited + it)
                it !in visited -> solve(initialGraph, it, visitSingleNodeTwice, visited = visited + it)
                visitSingleNodeTwice -> solve(initialGraph, it, false, visited + it)
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
