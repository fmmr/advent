package no.rodland.advent_2020

import product
import java.lang.Math.sqrt


// --- Day 20: Jurassic Jigsaw ---
@Suppress("UNUSED_PARAMETER")
object Day20 {
    fun partOne(list: String): Long {
        val tiles = list.split("\n\n").map { Tile(it) }
        return tiles.map { it.num to it.commonBorders(tiles) }.filter { it.second.size == 2 }.map { it.first.toLong() }.product()
    }


//    val seaMonster = """
//                  #
//#    ##    ##    ###
// #  #  #  #  #  #"""

    val seaMonster = listOf(
        listOf(18),
        listOf(0, 5, 6, 11, 12, 17, 18, 19),
        listOf(1, 4, 7, 10, 13, 16)
    )

    fun partTwo(list: String): Int {
        val tiles = list.split("\n\n").map { Tile(it) }.map { it.num to it }.toMap()
        val size = sqrt(tiles.size.toDouble()).toInt()
        val common = tiles.map { it.key to it.value.commonBorders(tiles.values.toList()) }.toMap()
        val topLeftTile = orientTopLeft(common, common.filter { it.value.size == 2 }.map { tiles[it.key]!! }.first())
        val sea = getSea(topLeftTile, size, common)

        val entireSea = toTile(sea).forest

        // will not work if they overlap
        val numberSeaMonsters = entireSea.allVariations().map { it.countSeaMonsters(seaMonster) }.first { it > 0 }
        val numHash = entireSea.map { it.count { it == '#' } }.sum()
        val seaMonsterHashes = numberSeaMonsters * (seaMonster.map { it.size }.sum())
        return numHash - seaMonsterHashes
    }

    private fun toTile(sea: List<List<Tile>>): Tile {
        return Tile(-1, Forest(sea.flatMap { tilesRow ->
            val inner = tilesRow.map { Tile(it.num, it.forest.inner()) }
            val rows = inner.first().size
            (0 until rows).map { row ->
                inner.map { it.forest[row] }.flatten()
            }
        }))
    }

    private fun getSea(topLeftTile: Tile, size: Int, common: Map<Int, List<Tile>>): List<List<Tile>> {
        var over = topLeftTile
        var left = topLeftTile
        return (0 until size).map { row ->
            (0 until size).map { col ->
                if (row == 0 && col == 0) {
                    topLeftTile
                } else if (col == 0) {
                    // look at the one above
                    val bottomRow = over.forest.bottom()
                    val neighbours = common[over.num]!!
                    val neighbour = neighbours.first { tile -> tile.forest.allVariations().any { it.top() == bottomRow } }
                    over = over.orient(neighbour, Dir.DOWN)
                    left = over
                    over
                } else {
                    val rightRow = left.forest.right()
                    val neighbours = common[left.num]!!
                    val neighbour = neighbours.first { tile -> tile.forest.allVariations().any { it.left() == rightRow } }
                    left = left.orient(neighbour, Dir.RIGHT)
                    left
                }
            }
        }
    }

    private fun orientTopLeft(common: Map<Int, List<Tile>>, topLeft: Tile): Tile {
        val (n1, n2) = common[topLeft.num]!!.first() to common[topLeft.num]!!.last()
        val orientation = topLeft.allVariations().filter { f ->
            (n1.allVariations().any { it.left() == f.right() } && n2.allVariations().any { it.top() == f.bottom() }) || (n2.allVariations().any { it.left() == f.right() } && n1.allVariations().any { it.top() == f.bottom() })
        }
        val topLeftTile = Tile(topLeft.num, orientation.first())
        return topLeftTile
    }

    val regex = "Tile (\\d+):".toRegex()


    data class Forest(val map: List<List<Char>>) : List<List<Char>> by map {


        fun inner() = Forest(drop(1).dropLast(1).map { it.drop(1).dropLast(1) })


        fun allVariations() = sequence {
            yield(this@Forest)
            yield(flipV())
            yield(flipH())
            rotateR().let { rotated ->
                yield(rotated)
                yield(rotated.flipV())
                yield(rotated.flipH())
            }
            yield(rotate2())
            yield(rotateL())
        }

        fun getSide(dir: Dir): List<Char> = when (dir) {
            Dir.RIGHT -> right()
            Dir.LEFT -> left()
            Dir.UP -> top()
            Dir.DOWN -> bottom()
        }

        fun getOtherSide(dir: Dir): List<Char> = getSide(dir.other())

        fun rotateR() = Forest((0 until size).map { column(it).reversed() })
        fun rotateL() = rotateR().rotateR().rotateR()
        fun rotate2() = rotateR().rotateR()
        fun flipH() = Forest(map { it.reversed() })
        fun flipV() = Forest(reversed())

        fun column(col: Int) = this.map { it[col] }
        fun right() = column(size - 1)
        fun left() = column(0)
        fun top() = first()
        fun bottom() = last()

        fun commonBorders(forest: Forest): Set<List<Char>> = borders().intersect(forest.borders())
        fun borders() = listOf(vBorders(), hBorders()).flatten()
        fun vBorders() = listOf(topBorder(), bottomBorder()).flatten()
        fun hBorders() = listOf(leftBorder(), rightBorder()).flatten()
        fun leftBorder() = map { it.first() }.let { listOf(it, it.reversed()) }
        fun rightBorder() = map { it.last() }.let { listOf(it, it.reversed()) }
        fun topBorder() = first().toList().let { listOf(it, it.reversed()) }
        fun bottomBorder() = last().toList().let { listOf(it, it.reversed()) }

        fun countSeaMonsters(seaMonster: List<List<Int>>): Int {
            val seaMonsters = (indices).flatMap { x ->
                (get(0).indices).map { y ->
                    val isSeamonster = seaMonster.flatMapIndexed { dx, yPos ->
                        yPos.map { dy ->
                            if ((x + dx) < size && ((y + dy) < get(0).size)) {
                                get(x + dx)[y + dy]
                            } else {
                                'T'
                            }
                        }
                    }.all { it == '#' }
                    isSeamonster
                }
            }
            return seaMonsters.count { it }
        }
    }

    data class Tile(val num: Int, val forest: Forest) {
        constructor(str: String, split: List<String> = str.split("\n")) : this(
            regex.find(split[0])?.groups?.get(1)?.value?.toInt() ?: -1,
            Forest(split.subList(1, split.size).map { row -> row.map { it } })
        )

        val size = forest.size

        fun commonBorders(tiles: List<Tile>): List<Tile> = tiles.filterNot { it == this }.filterNot { forest.commonBorders(it.forest).isEmpty() }
        fun allVariations() = forest.allVariations()

        override fun toString(): String {
            return "Tile: $num"
        }

        fun orient(aNeighbour: Tile, dir: Dir): Tile {
            val toMatch = forest.getSide(dir)
            return Tile(aNeighbour.num, aNeighbour.allVariations().first { toMatch == it.getOtherSide(dir) })
        }
    }

    enum class Dir {
        RIGHT, DOWN, LEFT, UP;

        fun other(): Dir = when (this) {
            RIGHT -> LEFT
            DOWN -> UP
            LEFT -> RIGHT
            UP -> DOWN
        }
    }
}