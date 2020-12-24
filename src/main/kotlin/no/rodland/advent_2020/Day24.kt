package no.rodland.advent_2020

import no.rodland.advent_2020.Day24.Colour.BLACK
import no.rodland.advent_2020.Day24.Colour.WHITE

@Suppress("UNUSED_PARAMETER")
object Day24 {
    fun partOne(list: List<String>): Int {
        val tiles = mutableMapOf<Hex, Colour>()
        val directions = list.toDirections().map { it.toTile() }.forEach { hex ->
            val previous = tiles.getOrDefault(hex, WHITE)
            tiles.put(hex, previous.flip())

        }
        println(tiles.values)

        return tiles.values.count { it == BLACK }
    }

    private fun String.toDirections(): List<Dir> {
        val replaced = this.replace("se", "1").replace("sw", "2").replace("ne", "3").replace("nw", "4")
        return replaced.map {
            when (it) {
                '1' -> Dir.SE
                '2' -> Dir.SW
                '3' -> Dir.NE
                '4' -> Dir.NW
                'w' -> Dir.W
                'e' -> Dir.E
                else -> error("unable to find dir for $it")
            }
        }
    }

    private fun List<Dir>.toTile(): Hex = this.fold(Hex(0, 0, 0)) { acc, dir -> acc.goTo(dir) }

    private fun List<String>.toDirections(): List<List<Dir>> = this.map { it.toDirections() }

    fun partTwo(list: List<String>): Int {
        return 2
    }

    // https://www.redblobgames.com/grids/hexagons/#distances
    data class Hex(val x: Int, val y: Int, val z: Int) {

        fun goTo(dir: Dir): Hex {
            return when (dir) {
                Dir.W -> copy(x = x - 1, y = y + 1)
                Dir.NE -> copy(x = x + 1, z = z - 1)
                Dir.NW -> copy(y = y + 1, z = z - 1)
                Dir.E -> copy(x = x + 1, y = y - 1)
                Dir.SE -> copy(z = z + 1, y = y - 1)
                Dir.SW -> copy(x = x - 1, z = z + 1)
            }
        }

        fun distanceTo(other: Hex): Int {
            return (Math.abs(x - other.x) + Math.abs(y - other.y) + Math.abs(z - other.z)) / 2
        }
    }

    enum class Colour {
        WHITE, BLACK;

        fun flip() = when (this) {
            WHITE -> BLACK
            else -> WHITE
        }
    }

    enum class Dir {
        E, NE, NW, W, SE, SW;

        companion object {
            fun of(str: String): Dir {
                return valueOf(str.toUpperCase())
            }
        }
    }
}


