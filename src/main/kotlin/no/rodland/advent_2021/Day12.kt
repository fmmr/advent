package no.rodland.advent_2021


@Suppress("UNUSED_PARAMETER")
object Day12 {
    fun partOne(list: List<String>): Int {
        val map = parse(list)
        return solve(map, Cave("start"))
    }

    private fun parse(list: List<String>) = list
        .map { it.split('-') }
        .flatMap { (v1, v2) -> listOf(Cave(v1) to Cave(v2), Cave(v2) to Cave(v1)) }
        .groupBy { it.first }
        .mapValues { (_, v) -> v.map { it.second } }

    fun partTwo(list: List<String>): Int {
        val map = parse(list)
        return 2 // solve(map, true)
    }

    private fun solve(map: Map<Cave, List<Cave>>, current: Cave, visited: Set<Cave> = hashSetOf(current)): Int {
        val rec = map[current]!!
        return rec.sumOf {
            when {
                it.code == "end" -> 1
                it.big -> solve(map, it, visited + it)
                it !in visited -> solve(map, it, visited + it)
                else -> 0
            }
        }
    }

    data class Cave(val code: String, val big: Boolean = code[0].isUpperCase())
}
