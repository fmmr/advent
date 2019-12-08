package no.rodland.advent_2019

object Day08 {
    fun partOne(list: List<Int>, width: Int, height: Int): Int {
        val layers = list.chunked(width * height)
        val minZerosLayer = layers.minBy { it.count { it == 0 } }!!
        return minZerosLayer.count { it == 1 } * minZerosLayer.count { it == 2 }
    }

    fun partTwo(list: List<Int>): Int {
        return 2
    }
}