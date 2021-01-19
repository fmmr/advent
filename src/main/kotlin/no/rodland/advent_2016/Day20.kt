package no.rodland.advent_2016

object Day20 {
    fun partOne(list: List<String>, max: Long): Long {
        val ranges = list.map { it.split("-") }.map { it.first().toLong()..it.last().toLong() }.sortedWith(compareBy({ it.first }, { it.last }))
        var currentValue = 0L
        ranges.forEach { range ->
            if (range.last >= currentValue) {
                if (range.first > currentValue) {
                    return currentValue
                }
                currentValue = range.last + 1
            }
        }
        return -1
    }

    fun partTwo(list: List<String>, max: Long): Long {
        var currentValue = 0L
        var numWhiteListed = 0L
        val ranges = list.map { it.split("-") }.map { it.first().toLong()..it.last().toLong() }.sortedWith(compareBy({ it.first }, { it.last }))
        ranges.forEach { range ->
            if (range.last >= currentValue) {
                if (range.first > currentValue) {
                    numWhiteListed += (range.first - currentValue)
                }
                currentValue = range.last + 1
            }
        }
        numWhiteListed += (max - currentValue + 1)
        return numWhiteListed

    }

    // 771316128 too high
    // 767079326 too high
}
