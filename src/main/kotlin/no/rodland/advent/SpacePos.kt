package no.rodland.advent

import kotlin.math.abs
import kotlin.math.atan2
import kotlin.math.pow

internal const val ACTIVE = '#'
private fun Char.active() = this == ACTIVE

typealias Pos2D = Pos

sealed class SpacePos {
    abstract fun neighbors(): List<SpacePos>
    fun activeNeighbors(space: Map<out SpacePos, Char>): Int = neighbors().mapNotNull { space[it] }.count { it.active() }
}

data class Pos3D(val x: Int, val y: Int, val z: Int) : SpacePos() {
    constructor(triple: Triple<Int, Int, Int>) : this(triple.first, triple.second, triple.third)

    override fun neighbors(): List<SpacePos> {
        return listOf(0, 1, -1)
            .flatMap { dx ->
                listOf(0, 1, -1).flatMap { dy ->
                    listOf(0, 1, -1).map { dz ->
                        Triple(x + dx, y + dy, z + dz)
                    }
                }
            }
            .map { Pos3D(it) }
            .filterNot { it == this }
    }
}


data class Pos(val x: Int, val y: Int) : SpacePos(), Comparable<Pos> {
    override fun compareTo(other: Pos): Int {
        val yComp = y.compareTo(other.y)
        return if (yComp == 0) {
            x.compareTo(other.x)
        } else yComp
    }

    fun distanceTo(other: Pos): Int {
        return Math.abs(other.x - x) + Math.abs(other.y - y)
    }

    fun directDistance(other: Pos): Double {
        return Math.sqrt((other.x - x).toDouble().pow(2) + (other.y - y).toDouble().pow(2))
    }

    fun neighboorCellsReadingOrder(): List<Pos> {
        return listOf(
            Pos(x, y - 1),
            Pos(x - 1, y),
            Pos(x + 1, y),
            Pos(x, y + 1)
        )
    }

    fun positiveNeighboor(limitX: Int = Int.MAX_VALUE, limitY: Int = Int.MAX_VALUE): List<Pos> {
        return neighboorCellsReadingOrder()
            .filter { it.x >= 0 && it.y >= 0 }
            .filter { it.x < limitX }
            .filter { it.y < limitY }

    }

    fun getNeighboorsNotInMap(map: Map<Pos, *>): List<Pos> {
        return neighboorCellsReadingOrder().filter { !map.containsKey(it) }
    }

    override fun neighbors(): List<SpacePos> = neighboorCellsAllEight()

    fun neighboorCellsAllEight(): List<Pos> {
        return listOf(
            Pos(x, y - 1),
            Pos(x - 1, y),
            Pos(x + 1, y),
            Pos(x, y + 1),
            Pos(x - 1, y - 1),
            Pos(x + 1, y - 1),
            Pos(x - 1, y + 1),
            Pos(x + 1, y + 1)
        )
    }

    fun angle(another: Pos) = atan2((another.x - x).toDouble(), (another.y - y).toDouble())

    fun getDownNeighbors(): List<Pos> {
        return listOf(
            Pos(x - 1, y),
            Pos(x + 1, y),
            Pos(x, y + 1)
        )
    }

    fun next(c: Char, howMuch: Int = 1): Pos {
        return when (c) {
            'N', '^' -> above(howMuch)
            'S', 'v' -> below(howMuch)
            'W', '<' -> left(howMuch)
            'E', '>' -> right(howMuch)
            else -> error("Unable to get pos from direction: $c")
        }
    }

    fun next(s: String, howMuch: Int = 1): Pos {
        return when (s) {
            "N", "^" -> above(howMuch)
            "S", "v" -> below(howMuch)
            "W", "<" -> left(howMuch)
            "E", ">" -> right(howMuch)
            "NE" -> ne()
            "SE" -> se()
            "NW" -> nw()
            "SW" -> sw()
            else -> error("Unable to get pos from direction: $s")
        }
    }

    fun getSidesAfterMoving(c: Char): List<Pos> {
        return when (c) {
            'N', 'S' -> listOf(left(), right())
            'E', 'W' -> listOf(below(), above())
            else -> error("Unable to get pos from direction: $c")
        }
    }

    fun isPositive(): Boolean = x >= 0 && y >= 0

    fun isInGrid(maxX: Int, maxY: Int): Boolean = x >= 0 && y >= 0 && x < maxX && y < maxY

    fun above(howMuch: Int = 1): Pos = Pos(x, y - howMuch)
    fun below(howMuch: Int = 1): Pos = Pos(x, y + howMuch)
    fun left(howMuch: Int = 1): Pos = Pos(x - howMuch, y)
    fun right(howMuch: Int = 1): Pos = Pos(x + howMuch, y)
    fun nw(): Pos = Pos(x - 1, y - 1)
    fun ne(): Pos = Pos(x + 1, y - 1)
    fun sw(): Pos = Pos(x - 1, y + 1)
    fun se(): Pos = Pos(x + 1, y + 1)

    fun manhattan(): Int = abs(x) + abs(y)

    operator fun minus(other: Pos): Pos = Pos(x - other.x, y - other.y)
    operator fun plus(other: Pos): Pos = Pos(x + other.x, y + other.y)


    fun rotateRight(times: Int): Pos {
        return when (times % 4) {
            0 -> this
            1 -> Pos(-y, x)
            2 -> Pos(-x, -y)
            3 -> Pos(y, -x)
            else -> error("unable to rotate $this $times times")
        }
    }

    companion object {
        fun getMinMax(coordinates: Collection<Pos>): Pair<Pair<Int, Int>, Pair<Int, Int>> {
            val xmin = coordinates.map { it.x }.minOrNull()!!
            val xmax = coordinates.map { it.x }.maxOrNull()!!
            val ymin = coordinates.map { it.y }.minOrNull()!!
            val ymax = coordinates.map { it.y }.maxOrNull()!!
            return (xmin to xmax) to (ymin to ymax)
        }
    }

}


data class Pos4D(val x: Int, val y: Int, val z: Int, val w: Int) : SpacePos() {
    override fun neighbors(): List<SpacePos> {
        return listOf(0, 1, -1)
            .flatMap { dx ->
                listOf(0, 1, -1).flatMap { dy ->
                    listOf(0, 1, -1).flatMap { dz ->
                        listOf(0, 1, -1).map { dw ->
                            Pos4D(x + dx, y + dy, z + dz, w + dw)
                        }
                    }
                }
            }
            .filterNot { it == this }
    }
}
