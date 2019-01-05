package no.rodland.advent_2018

object Day25 {
    fun partOne(list: List<String>): Int {
        val points = list.map { Point4D(it) }
        val neighbors = points.map { point -> point to points.filterNot { point == it }.filter { point.isCloseTo(it) } }
        val constellations: MutableMap<Int, Set<Point4D>> = mutableMapOf()
        var id = 0

        neighbors.forEach { (k, v) ->
            if (constellations.doesNotContainPoint(k)) {
                constellations[id++] = setOf(k) + v
            } else {
                val currentId = constellations.getConstelltion(k)!!
                constellations[currentId] = constellations[currentId]!! + setOf(k) + v
            }
        }

        var numberOfConstellationEntries = constellations.map { (_, v) -> v.size }.sum()
        while (numberOfConstellationEntries > points.size) {
            merge(points, constellations)
            numberOfConstellationEntries = constellations.map { (_, v) -> v.size }.sum()
        }
        println("number points: ${list.size}, number of constellations: ${constellations.size}, number of points $numberOfConstellationEntries")
        return constellations.filterValues { it.size > 0 }.size
    }

    private fun merge(points: List<Point4D>, constellations: MutableMap<Int, Set<Point4D>>) {
        points.map { it to constellations.getConstelltions(it) }
                .filter { it.second.size > 1 }
                .forEach { (_, ints) ->
                    val mergedConstellation = ints.flatMap { constellations[it]?.toList() ?: emptyList() }
                    ints.forEach { constellations.remove(it) }
                    constellations[constellations.maxId() + 1] = mergedConstellation.toSet()
                }
    }

    data class Point4D(val x: Int, val y: Int, val z: Int, val t: Int) {
        constructor(str: String) : this(str.split(",")[0].toInt(), str.split(",")[1].toInt(), str.split(",")[2].toInt(), str.split(",")[3].toInt())

        fun distanceTo(other: Point4D): Int {
            return Math.abs(other.x - x) + Math.abs(other.y - y) + Math.abs(other.z - z) + Math.abs(other.t - t)
        }

        fun isCloseTo(other: Point4D): Boolean {
            return distanceTo(other) <= 3
        }
    }
}

private fun Map<Int, Set<Day25.Point4D>>.doesNotContainPoint(p: Day25.Point4D): Boolean {
    return !containsPoint(p)
}

private fun Map<Int, Set<Day25.Point4D>>.containsPoint(p: Day25.Point4D): Boolean {
    return getConstelltion(p) != null
}

private fun Map<Int, Set<Day25.Point4D>>.getConstelltion(p: Day25.Point4D): Int? {
    return this.filterValues { it.contains(p) }.keys.firstOrNull()
}

private fun Map<Int, Set<Day25.Point4D>>.getConstelltions(p: Day25.Point4D): Set<Int> {
    return this.filterValues { it.contains(p) }.keys
}

private fun Map<Int, Set<Day25.Point4D>>.maxId(): Int {
    return this.keys.max() ?: 0
}


