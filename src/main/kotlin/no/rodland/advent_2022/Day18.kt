package no.rodland.advent_2022

import no.rodland.advent.Pos3D

// template generated: 28/11/2022
// Fredrik Rødland 2022

object Day18 {
    fun partOne(list: List<String>): Int {
        val cubes = list.map { it.split(",") }.map { (x, y, z) -> Pos3D(x.toInt(), y.toInt(), z.toInt()) }.toSet()
        val allSides = cubes.flatMap { it.adjacent() }
        return allSides.count { it !in cubes }
    }

    fun partTwo(list: List<String>): Int {
        val cubes = list.map { it.split(",") }.map { (x, y, z) -> Pos3D(x.toInt(), y.toInt(), z.toInt()) }.toSet()
        val maxX = cubes.maxOf { it.x } + 1
        val minX = cubes.minOf { it.x } - 1
        val maxY = cubes.maxOf { it.y } + 1
        val minY = cubes.minOf { it.y } - 1
        val maxZ = cubes.maxOf { it.z } + 1
        val minZ = cubes.minOf { it.z } - 1

        // build up set of Pos3d surrounding the shape - i.e. accessible from the outside.
        val visited = mutableSetOf<Pos3D>()
        val queue = ArrayDeque(listOf(Pos3D(0, 0, 0)))
        while (queue.isNotEmpty()) {
            val pos = queue.removeFirst()
            val neighbours = pos.adjacent()
            neighbours
                .filter { neighbour -> neighbour.within(minX, maxX, minY, maxY, minZ, maxZ) }
                .filter { it !in cubes }
//                .filter { neighbour -> neighbour.adjacent().any { nn ->true  } }  // hm - should be enough to keep only points touching the shape?  But gives wrong answer.
                .filter { visited.add(it) }
                .forEach { queue.add(it) }
        }
        return cubes.sumOf { pos -> pos.adjacent().count { it in visited } }
    }

    private fun Pos3D.within(minX: Int, maxX: Int, minY: Int, maxY: Int, minZ: Int, maxZ: Int) = x in minX..maxX && y in minY..maxY && z in minZ..maxZ
}
