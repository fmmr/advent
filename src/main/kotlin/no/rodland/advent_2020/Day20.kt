package no.rodland.advent_2020

import product

typealias Forrest = Array<CharArray>

// --- Day 20: Jurassic Jigsaw ---
@Suppress("UNUSED_PARAMETER")
object Day20 {
    fun partOne(list: String): Long {
        val tiles = list.split("\n\n").map { Tile(it) }
        val common = tiles.map { it to it.commonBorders(tiles) }.toMap()
        return common.filter { it.value.size == 2 }.map { it.key.num.toLong() }.product()
    }

    fun partTwo(list: String): Int {
        return 2
    }

    val regex = "Tile (\\d+):".toRegex()

    data class Tile(val num: Int, val forrest: Forrest) {
        constructor(str: String, split: List<String> = str.split("\n")) : this(regex.find(split[0])?.groups?.get(1)?.value?.toInt() ?: -1, split.subList(1, split.size).map { it.toCharArray() }.toTypedArray())

        override fun toString(): String {
            return "Tile: $num"
        }

        fun commonBorders(tiles: List<Tile>): List<Tile> = tiles.filterNot { it == this }.filterNot { commonBorders(it).isEmpty() }

        private fun commonBorders(tile: Tile): Set<List<Char>> = borders().intersect(tile.borders())

        private fun borders() = listOf(vBorders(), hBorders()).flatten()
        private fun vBorders() = listOf(topBorder(), bottomBorder()).flatten()
        private fun hBorders() = listOf(leftBorder(), rightBorder()).flatten()


        private fun leftBorder(): List<List<Char>> {
            return try {
                forrest.map { it.first() }.let { listOf(it, it.reversed()) }
            } catch (e: Exception) {
                error("no left border for $this")
            }
        }

        private fun rightBorder() = forrest.map { it.last() }.let { listOf(it, it.reversed()) }
        private fun topBorder() = forrest.first().toList().let { listOf(it, it.reversed()) }
        private fun bottomBorder() = forrest.last().toList().let { listOf(it, it.reversed()) }
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Tile

            if (num != other.num) return false
            if (!forrest.contentDeepEquals(other.forrest)) return false

            return true
        }

        override fun hashCode(): Int {
            var result = num
            result = 31 * result + forrest.contentDeepHashCode()
            return result
        }
    }
}


