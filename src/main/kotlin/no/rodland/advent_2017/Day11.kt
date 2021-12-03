package no.rodland.advent_2017

import java.util.Locale
import kotlin.math.abs
import kotlin.math.max

object Day11 {
    fun partOne(directions: String): Int {
        val origo = Hex(0, 0, 0)
        val end = directions.split(",").map { Dir.of(it) }.fold(origo) { acc, d ->
            acc.goTo(d)
        }
        return origo.distanceTo(end)
    }

    fun partTwo(directions: String): Int {
        val origo = Hex(0, 0, 0)
        var maxDistance = 0
        directions.split(",").map { Dir.of(it) }.fold(origo) { acc, d ->
            val now = acc.goTo(d)
            maxDistance = max(maxDistance, origo.distanceTo(now))
            now
        }
        return maxDistance
    }

    // https://www.redblobgames.com/grids/hexagons/#distances
    data class Hex(val x: Int, val y: Int, val z: Int) {

        fun goTo(dir: Dir): Hex {
            return when (dir) {
                Dir.N -> copy(y = y + 1, z = z - 1)
                Dir.NE -> copy(x = x + 1, z = z - 1)
                Dir.NW -> copy(x = x - 1, y = y + 1)
                Dir.S -> copy(z = z + 1, y = y - 1)
                Dir.SE -> copy(x = x + 1, y = y - 1)
                Dir.SW -> copy(x = x - 1, z = z + 1)
            }
        }

        fun distanceTo(other: Hex): Int {
            return (abs(x - other.x) + abs(y - other.y) + abs(z - other.z)) / 2
        }
    }

    enum class Dir {
        N, NE, NW, S, SE, SW;

        companion object {
            fun of(str: String): Dir {
                return valueOf(str.uppercase(Locale.getDefault()))
            }
        }
    }
}