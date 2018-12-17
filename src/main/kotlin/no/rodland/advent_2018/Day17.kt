package no.rodland.advent_2018

object Day17 {
    fun partOne(list: List<String>): Int {
        val coordinates: List<Pair<IntRange, IntRange>> = parse(list)
        getYMinMax(coordinates)
        return 2
    }

    fun getYMinMax(coordinates: List<Pair<IntRange, IntRange>>): Pair<Int, Int> {
        val ymin = coordinates.map { it.second }.map { it.first }.min()!!
        val ymax = coordinates.map { it.second }.map { it.last }.max()!!
        return ymin to ymax
    }

    fun parse(list: List<String>): List<Pair<IntRange, IntRange>> {
        return list.map { str ->
            val part = (0..1).map { idx ->
                val (c, range) = str.split(", ")[idx].split("=")
                val rSplit = range.split("..")
                val r1 = rSplit[0].toInt()
                val r2 = if (rSplit.size == 2) rSplit[1].toInt() else r1
                c to r1..r2
            }.toMap()
            part["x"]!! to part["y"]!!
        }
    }

    fun partTwo(list: List<String>): Int {
        return 2
    }
}