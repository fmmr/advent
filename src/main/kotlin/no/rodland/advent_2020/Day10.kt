package no.rodland.advent_2020

object Day10 {
    fun partOne(list: List<Int>): Int {
        val diffs = (listOf(0) + list)
            .asSequence()
            .sorted()
            .zipWithNext()
            .map { it.second - it.first }
            .groupBy { it }
            .map { it.key to it.value.size }
            .toMap()
        return diffs[1]!! * (diffs[3]!! + 1)
    }

    fun partTwo(list: List<Int>): Long {
        val sorted = (listOf(list.maxOrNull()!! + 3, 0) + list).sorted()
        val map: MutableMap<Int, Long> = mutableMapOf()
        sorted.forEach { adapter ->
            if (adapter == 0) {
                map[adapter] = 1 // get things started.
            } else {
                // the number of ways to reach this adapter is the sum of:
                // the ways to reach the one just prior + the one before that + the one before that
                map[adapter] = map.getOrDefault(adapter - 3, 0) + map.getOrDefault(adapter - 1, 0) + map.getOrDefault(adapter - 2, 0)
            }
        }
        return map.getValue(sorted.last())
    }
}
