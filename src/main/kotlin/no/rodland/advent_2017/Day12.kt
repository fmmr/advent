package no.rodland.advent_2017

object Day12 {
    // almost the same as 2018 Day 25
    fun partOne(list: List<String>): Int {
        val village = list.map {
            val line = it.split(" <-> ")
            line[0].toInt() to line[1].split(",").map { it.trim().toInt() }.toSet()
        }.toMap().toMutableMap()
        val programs = village.map { it.key }
        var sumLists = village.map { it.value }.map { it.size }.sum()
        while (sumLists > programs.size) {
            merge(programs, village)
            sumLists = village.map { it.value }.map { it.size }.sum()
        }
        return village[village.getGroups(0).first()]?.size ?: 0
    }

    private fun merge(programs: List<Int>, groups: MutableMap<Int, Set<Int>>) {
        programs.map { it to groups.getGroups(it) }
                .filter { it.second.size > 1 }
                .forEach { (_, ints) ->
                    val mergedConstellation = ints.flatMap { groups[it]?.toList() ?: emptyList() }
                    ints.forEach { groups.remove(it) }
                    groups[ints.first()] = mergedConstellation.toSet()
                }
    }

    private fun Map<Int, Set<Int>>.getGroups(p: Int): Set<Int> {
        return this.filterValues { it.contains(p) }.keys
    }

    fun partTwo(list: List<String>): Int {
        return 2
    }
}