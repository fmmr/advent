package no.rodland.advent_2015

object Day02 {
    fun partOne(list: List<String>): Int {
        return list.map { it.split("x").map { it.toInt() } }.map { paper(it) }.sum()
    }

    private fun paper(dimensions: List<Int>): Int {
        val (l, w, h) = dimensions
        val allsides = listOf(l * w, w * h, h * l)
        return 2 * allsides.sum() + (allsides.minOrNull()!!)
    }

    fun partTwo(list: List<String>): Int {
        return list.map { it.split("x").map { it.toInt() } }.map { ribbon(it) }.sum()
    }

    private fun ribbon(dimensions: List<Int>): Int {
        val twoShortestSides = dimensions - (dimensions.maxOrNull()!!)
        return 2 * twoShortestSides.sum() + dimensions.reduce { acc, n -> acc * n }
    }
}
