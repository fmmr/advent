package no.rodland.advent

import kotlin.math.atan2
import kotlin.math.pow

data class Pos(val x: Int, val y: Int) : Comparable<Pos> {
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

    fun getNext(c: Char): Pos {
        return when (c) {
            'N', '^' -> above()
            'S', 'v' -> below()
            'W', '<' -> left()
            'E', '>' -> right()
            else -> error("Unable to get pos from direction: $c")
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
    fun above(): Pos = Pos(x, y - 1)
    fun below(): Pos = Pos(x, y + 1)
    fun left(): Pos = Pos(x - 1, y)
    fun right(): Pos = Pos(x + 1, y)

    companion object {
        fun getMinMax(coordinates: Collection<Pos>): Pair<Pair<Int, Int>, Pair<Int, Int>> {
            val xmin = coordinates.map { it.x }.min()!!
            val xmax = coordinates.map { it.x }.max()!!
            val ymin = coordinates.map { it.y }.min()!!
            val ymax = coordinates.map { it.y }.max()!!
            return (xmin to xmax) to (ymin to ymax)
        }
    }
}