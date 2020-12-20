package no.rodland.advent_2020

import product
import kotlin.math.sqrt

typealias Forrest = List<List<Char>>

// --- Day 20: Jurassic Jigsaw ---
@Suppress("UNUSED_PARAMETER")
object Day20 {
    fun partOne(list: String): Long {
        val tiles = list.split("\n\n").map { Tile(it) }
        val common = tiles.map { it.num to it.commonBorders(tiles) }.toMap()
        return common.filter { it.value.size == 2 }.map { it.key.toLong() }.product()
    }

    fun partTwo(list: String): Int {
        val tiles = list.split("\n\n").map { Tile(it) }
        val common = tiles.map { it.num to it.commonBorders(tiles) }.toMap()
        val aCorner = common.filter { it.value.size == 2 }.map { it.key }.first()
        val length = sqrt(common.size.toDouble()).toInt()



        return 2
    }

    val regex = "Tile (\\d+):".toRegex()

    fun Forrest.rotateR() = (0..this[0].size).map { colum(this[0].size - 1 - it) }
    fun Forrest.rotateL() = rotateR().rotateR().rotateR()
    fun Forrest.rotate2() = rotateR().rotateR()
    fun Forrest.colum(col: Int) = this.map { it[col] }
    fun Forrest.flipH() = map { it.reversed() }
    fun Forrest.flipV() = reversed()

    fun Forrest.commonBorders(forrest: Forrest): Set<List<Char>> = borders().intersect(forrest.borders())
    fun Forrest.borders() = listOf(vBorders(), hBorders()).flatten()
    fun Forrest.vBorders() = listOf(topBorder(), bottomBorder()).flatten()
    fun Forrest.hBorders() = listOf(leftBorder(), rightBorder()).flatten()
    fun Forrest.leftBorder() = map { it.first() }.let { listOf(it, it.reversed()) }
    fun Forrest.rightBorder() = map { it.last() }.let { listOf(it, it.reversed()) }
    fun Forrest.topBorder() = first().toList().let { listOf(it, it.reversed()) }
    fun Forrest.bottomBorder() = last().toList().let { listOf(it, it.reversed()) }

    data class Tile(val num: Int, val forrest: Forrest) {
        constructor(str: String, split: List<String> = str.split("\n")) : this(regex.find(split[0])?.groups?.get(1)?.value?.toInt() ?: -1, split.subList(1, split.size).map { row -> row.map { it } })

        fun commonBorders(tiles: List<Tile>): List<Tile> = tiles.filterNot { it == this }.filterNot { forrest.commonBorders(it.forrest).isEmpty() }

        override fun toString(): String {
            return "Tile: $num"
        }
    }
}