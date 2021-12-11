package no.rodland.advent_2021

import no.rodland.advent.IntGrid
import product

object Day09 {
    fun partOne(list: List<String>): Long {
        return IntGrid.fromInput(list)
            .lowPoints()
            .map { (_, value) -> value }
            .sumOf { it + 1 }
            .toLong()
    }

    fun partTwo(list: List<String>): Long {
        val grid = IntGrid.fromInput(list)
        return grid
            .lowPoints()
            .map { (lowPos, _) -> grid.getRegion(lowPos).size }
            .sortedDescending()
            .take(3)
            .product()
    }
}


