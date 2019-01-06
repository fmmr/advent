package no.rodland.advent_2017

object Day13 {

    fun partOne(list: List<String>): Int {
        val catches = list
                .map { it.split(": ") }
                .map { it[0].toInt() to it[1].toInt() }
                .filter { it.first % (it.second * 2 - 2) == 0 }
        return catches.map { it.first * it.second }.sum()
    }

    fun partTwo(list: List<String>): Int {
        return 2
    }
}