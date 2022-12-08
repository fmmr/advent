package no.rodland.advent_2017

import no.rodland.advent.Pos

object Day14 {
    fun partOne(hashes: Map<Int, String>): Int {
        return hashes.map { (_, row) -> row.count { it == '1' } }.sum()
    }

    fun partTwo(hashes: Map<Int, String>): Int {
        val grid = hashes.map { (_, v) -> v.toCharArray() }
        val regions = mutableMapOf<Pos, Int>()
        var currentRegion = 0
        (0..127).forEach { y ->
            (0..127).forEach { x ->
                val pos = Pos(x, y)
                if (grid[y][x] == '1' && pos !in regions) {
                    currentRegion++
                    grid.getAllNeighboors(mutableSetOf(pos), pos).forEach { regions[it] = currentRegion }
                }
            }
        }
        return regions.values.distinct().count()
    }

    private fun List<CharArray>.getAllNeighboors(seen: MutableSet<Pos>, pos: Pos): List<Pos> {
        return listOf(pos) + pos.neighbourCellsUDLR().filter { it.isInGrid(this[0].size, size) }.filter { p -> this[p.y][p.x] == '1' }.filter { seen.add(it) }.flatMap { getAllNeighboors(seen, it) }
    }


    @Suppress("unused", "UNUSED_VARIABLE")
    private fun printHashes() {
        listOf("ffayrhll", "flqrgnkx").forEach { str ->
            println(str)
            val hashes = (0..127).map { i ->
                val hash = toKnotHash("$str-$i").toBinary()
                println("$i to \"$hash\",")
                hash
            }
            println()
            println()
        }
    }

    private fun toKnotHash(s: String): String {
        return Day10.knotHash(s)
    }

    private fun String.toBinary(): String {
        return map { it.toString() }.map { it.toInt(16) }.map { it.toString(2) }.joinToString("") { it.padStart(4, '0') }

    }
}
