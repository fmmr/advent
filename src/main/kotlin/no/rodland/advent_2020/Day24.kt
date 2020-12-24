package no.rodland.advent_2020

import no.rodland.advent_2020.Day24.Colour.BLACK
import no.rodland.advent_2020.Day24.Colour.WHITE

@Suppress("UNUSED_PARAMETER")
object Day24 {
    fun partOne(list: List<String>): Int {
        val tiles = initialize(list)
        return tiles.countBlacks()
    }

    fun partTwo(list: List<String>): Int {
        val tiles = initialize(list)
        repeat(100) {
            newDay(tiles)
        }
        return tiles.values.count { it == BLACK }
    }

    private fun newDay(tiles: MutableMap<Hex, Colour>) {
        val tilesToCheck = tiles.filter { (_, v) -> v == BLACK }.let { (it.map { it.key } + it.flatMap { it.key.neighbours }).toSet() }
        tilesToCheck
            .map { hex ->
                val colour = tiles.getColour(hex)
                val neighbours = hex.neighbours
                val blackNeighbours = neighbours.map { tiles.getColour(it) }.count { it == BLACK }
                hex to when (colour) {
                    BLACK -> if (blackNeighbours == 1 || blackNeighbours == 2) BLACK else WHITE
                    WHITE -> if (blackNeighbours == 2) BLACK else WHITE
                }
            }
            .forEach {
                tiles[it.first] = it.second
            }
    }

    private fun initialize(list: List<String>): MutableMap<Hex, Colour> {
        val tiles = mutableMapOf<Hex, Colour>()
        list.toDirections().map { it.toTile() }.forEach { hex -> tiles[hex] = tiles.getColour(hex).flip() }
        return tiles
    }

    fun String.toDirections(): List<Dir> {
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

    fun List<Dir>.toTile(): Hex = this.fold(Hex(0, 0, 0)) { acc, dir -> acc.goTo(dir) }

    private fun List<String>.toDirections(): List<List<Dir>> = this.map { it.toDirections() }

    // https://www.redblobgames.com/grids/hexagons/#distances
    data class Hex(val x: Int, val y: Int, val z: Int) {

        val neighbours: List<Hex> by lazy { Dir.values().map { goTo(it) } }

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
    }

    enum class Colour {
        WHITE, BLACK;

        fun flip() = when (this) {
            WHITE -> BLACK
            else -> WHITE
        }
    }

    enum class Dir { E, NE, NW, W, SE, SW }

    private fun Map<Hex, Colour>.countBlacks() = values.count { it == BLACK }
    private fun Map<Hex, Colour>.getColour(hex: Hex) = getOrDefault(hex, WHITE)
}



