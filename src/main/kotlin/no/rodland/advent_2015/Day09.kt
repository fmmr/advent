package no.rodland.advent_2015

object Day09 {

    // AlphaCentauri to Faerun = 44

    fun partOne(list: List<String>): Int {
        val weights = list.map { it.split(" = ") }.map { (it[0].split(" to ")[0] to it[0].split(" to ")[1]) to (it[1].toIntOrNull() ?: -1) }
        val locations = weights.map { it.first }.flatMap { listOf(it.first, it.second) }.toSet()
        val graph = Graph(weights.toMap())

        return 2
    }

    fun partTwo(list: List<String>): Int {
        return 2
    }

    fun List<Pair<String, String>>.getUniqueValuesFromPairs() = map { (a, b) -> listOf(a, b) }
        .flatten()
        .toSet()

    fun List<Pair<String, String>>.getUniqueValuesFromPairs(predicate: (String) -> Boolean) = map { (a, b) -> listOf(a, b) }
        .flatten()
        .filter(predicate)
        .toSet()

    data class Graph(val vertices: Set<String>, val edges: Map<String, Set<String>>, val weights: Map<Pair<String, String>, Int>) {
        constructor(weights: Map<Pair<String, String>, Int>) : this(
            vertices = weights.keys.toList().getUniqueValuesFromPairs(),
            edges = weights.keys
                .groupBy { it.first }
                .mapValues { it.value.getUniqueValuesFromPairs { x -> x !== it.key } }
                .withDefault { emptySet() },
            weights = weights
        )
    }

//    fun dijkstra(graph: Graph, start: String): Map<String, String?> {
//        val S: MutableSet<String> = mutableSetOf() // a subset of vertices, for which we know the true distance
//
//        val delta = graph.vertices.map { it to Int.MAX_VALUE }.toMap().toMutableMap()
//        delta[start] = 0
//
//        val previous: MutableMap<String, String?> = graph.vertices.map { it to null }.toMap().toMutableMap()
//
//        while (S != graph.vertices) {
//            val v = delta
//                .filter { !S.contains(it.key) }
//                .minBy { it.value }!!
//                .key
//
//            graph.edges.getValue(v).minus(S).forEach { neighbor ->
//                val newPath = delta.getValue(v) + graph.weights.getValue(Pair(v, neighbor))
//
//                if (newPath < delta.getValue(neighbor)) {
//                    delta[neighbor] = newPath
//                    previous[neighbor] = v
//                }
//            }
//
//            S.add(v)
//        }
//
//        return previous.toMap()
//    }
//
//    fun shortestPath(shortestPathTree: Map<String, T?>, start: T, end: T): List<String> {
//        fun pathTo(start: T, end: T): List<String> {
//            if (shortestPathTree[end] == null) return listOf(end)
//            return listOf(pathTo(start, shortestPathTree[end]!!), listOf(end)).flatten()
//        }
//
//        return pathTo(start, end)
//    }
}



