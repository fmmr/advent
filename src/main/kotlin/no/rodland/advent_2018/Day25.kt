package no.rodland.advent_2018

object Day25 {
    fun partOne(list: List<String>): Int {
        var id = 0
        val points = list.map { Point4D(it) }
        var constellations: MutableMap<Int, Set<Point4D>> = points.map { point -> id++ to points.filter { point.isCloseTo(it) }.toSet() }.toMap().toMutableMap()

        var numberOfConstellationEntries = constellations.map { (_, v) -> v.size }.sum()
        println("INIT: number points: ${list.size}, number of constellation entries: $numberOfConstellationEntries, number of constellations: ${constellations.size}")
        while (numberOfConstellationEntries > points.size) {
            merge(points, constellations)
            constellations = constellations.filterValues { it.isNotEmpty() }.toMutableMap()
            numberOfConstellationEntries = constellations.map { (_, v) -> v.size }.sum()
            println("LOOP: number points: ${list.size}, number of constellation entries: $numberOfConstellationEntries, number of constellations: ${constellations.size}")
        }
        return constellations.size
    }

    private fun merge(points: List<Point4D>, constellations: MutableMap<Int, Set<Point4D>>) {
        points.map { it to constellations.getConstellations(it) }
                .filter { it.second.size > 1 }
                .forEach { (_, ints) ->
                    val mergedConstellation = ints.flatMap { constellations[it]?.toList() ?: emptyList() }
                    ints.forEach { constellations.remove(it) }
                    constellations[ints.first()] = mergedConstellation.toSet()
                }
    }

    data class Point4D(val x: Int, val y: Int, val z: Int, val t: Int) {
        constructor(str: String) : this(str.split(",")[0].toInt(), str.split(",")[1].toInt(), str.split(",")[2].toInt(), str.split(",")[3].toInt())

        private fun distanceTo(other: Point4D): Int {
            return Math.abs(other.x - x) + Math.abs(other.y - y) + Math.abs(other.z - z) + Math.abs(other.t - t)
        }

        fun isCloseTo(other: Point4D): Boolean {
            return distanceTo(other) <= 3
        }
    }

    private fun Map<Int, Set<Day25.Point4D>>.getConstellations(p: Day25.Point4D): Set<Int> {
        return this.filterValues { it.contains(p) }.keys
    }
}

