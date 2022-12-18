package no.rodland.advent_2022

import no.rodland.advent.Pos3D

// template generated: 28/11/2022
// Fredrik Rødland 2022

object Day18 {
    @Suppress("UNUSED_PARAMETER")
    fun partOne(list: List<String>): Int {
        val cubes = list.map { it.split(",") }.map { (x, y, z) -> Pos3D(x.toInt(), y.toInt(), z.toInt()) }.toSet()
        val allSides = cubes.flatMap { it.adjacent() }
        return allSides.count { it !in cubes }
    }

    @Suppress("UNUSED_PARAMETER")
    fun partTwo(list: List<String>): Long {
        return 2
    }
}
