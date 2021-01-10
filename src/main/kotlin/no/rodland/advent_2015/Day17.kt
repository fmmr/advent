package no.rodland.advent_2015

import combinations

object Day17 {
    fun partOne(list: List<Int>, liters: Int): Int {
        return list.combinations().count { it.sum() == liters }
    }

    fun partTwo(list: List<Int>, liters: Int): Int {
        return list.combinations().filter { it.sum() == liters }.groupBy { it.size }.minByOrNull { it.key }!!.value.size
    }
}
