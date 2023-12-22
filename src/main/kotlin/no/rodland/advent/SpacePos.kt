package no.rodland.advent

import kotlin.math.*

internal const val ACTIVE = '#'
private fun Char.active() = this == ACTIVE

typealias Point = Pos

sealed class SpacePos {
    abstract fun neighbours(): List<SpacePos>
    abstract fun manhattan(): Int
    abstract fun any(predicate: (Int) -> Boolean): Boolean
    fun activeNeighbors(space: Map<out SpacePos, Char>): Int = neighbours().mapNotNull { space[it] }.count { it.active() }
}

data class Pos3D(val x: Int, val y: Int, val z: Int) : SpacePos() {
    constructor(triple: Triple<Int, Int, Int>) : this(triple.first, triple.second, triple.third)
    constructor(str:String, separator:Char=',') : this(str.split(separator).map { it.trim().toInt() }.let { Triple(it[0], it[1], it[2]) })

    override fun neighbours(): List<SpacePos> {
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

    fun adjacent(): List<Pos3D> {
        return listOf(1, -1).map {
            Pos3D(x + it, y, z)
        } + listOf(1, -1).map {
            Pos3D(x, y + it, z)
        } + listOf(1, -1).map {
            Pos3D(x, y, z + it)
        }
    }

    override fun manhattan(): Int = abs(x) + abs(y) + abs(z)
    override fun any(predicate: (Int) -> Boolean): Boolean = listOf(x, y, z).any(predicate)

    operator fun plus(other: Pos3D) = Pos3D(x + other.x, y + other.y, z + other.z)
    operator fun minus(other: Pos3D) = Pos3D(x - other.x, y - other.y, z - other.z)

}


data class Pos(val x: Int, val y: Int) : SpacePos(), Comparable<Pos> {
    constructor(str: String, x: Int = str.split(",").first().trim().toInt(), y: Int = str.split(",").last().trim().toInt()) : this(x, y)

    override fun compareTo(other: Pos): Int {
        val yComp = y.compareTo(other.y)
        return if (yComp == 0) {
            x.compareTo(other.x)
        } else yComp
    }

    fun distanceTo(other: Pos): Int {
        return abs(other.x - x) + abs(other.y - y)
    }

    fun directDistance(other: Pos): Double {
        return sqrt((other.x - x).toDouble().pow(2) + (other.y - y).toDouble().pow(2))
    }

    fun neighbourCellsUDLR(): List<Pos> {
        return listOf(
            Pos(x, y - 1),
            Pos(x, y + 1),
            Pos(x - 1, y),
            Pos(x + 1, y)
        )
    }

    fun neighbourCellsReadingOrder(): List<Pos> {
        return listOf(
            Pos(x, y - 1),
            Pos(x - 1, y),
            Pos(x + 1, y),
            Pos(x, y + 1)
        )
    }

    fun positiveNeighbour(limitX: Int = Int.MAX_VALUE, limitY: Int = Int.MAX_VALUE): List<Pos> {
        return neighbourCellsReadingOrder()
            .filter { it.x >= 0 && it.y >= 0 }
            .filter { it.x < limitX }
            .filter { it.y < limitY }

    }

    override fun neighbours(): List<SpacePos> = neighbourCellsAllEight()

    fun neighbourCellsAllEight(): List<Pos> {
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

    fun neighbourCellsAllEightIncludingSelf(): List<Pos> {
        return listOf(
            Pos(x - 1, y - 1), Pos(x, y - 1), Pos(x + 1, y - 1),
            Pos(x - 1, y), Pos(x, y), Pos(x + 1, y),
            Pos(x - 1, y + 1), Pos(x, y + 1), Pos(x + 1, y + 1),
        )
    }

    fun angle(another: Pos) = atan2((another.x - x).toDouble(), (another.y - y).toDouble())

    fun next(d: Direction, howMuch: Int = 1): Pos = next(d.toString()[0], howMuch)

    fun next(c: Char, howMuch: Int = 1): Pos {
        return when (c) {
            'U', 'N', '^' -> above(howMuch)
            'D', 'S', 'v' -> below(howMuch)
            'L', 'W', '<' -> left(howMuch)
            'R', 'E', '>' -> right(howMuch)
            else -> error("Unable to get pos from direction: $c")
        }
    }

    fun next(s: String, howMuch: Int = 1): Pos {
        return when (s) {
            "U", "N", "^" -> above(howMuch)
            "D", "S", "v" -> below(howMuch)
            "L", "W", "<" -> left(howMuch)
            "R", "E", ">" -> right(howMuch)
            "NE" -> ne()
            "SE" -> se()
            "NW" -> nw()
            "SW" -> sw()
            else -> error("Unable to get pos from direction: $s")
        }
    }


    fun path(s: String, howMuch: Int = 1): List<Pos> {
        return (1..howMuch).runningFold(this) { acc: Pos, _: Int ->
            acc.next(s)
        }.drop(1)
    }

    fun getSidesAfterMoving(c: Char): List<Pos> {
        return when (c) {
            'N', 'S' -> listOf(left(), right())
            'E', 'W' -> listOf(below(), above())
            else -> error("Unable to get pos from direction: $c")
        }
    }

    private fun isPositive(): Boolean = x >= 0 && y >= 0
    fun isPositiveAndWithin(maxX: Int, maxY: Int): Boolean = isPositive() && x <= maxX && y <= maxY

    fun isInGrid(list: List<*>): Boolean = x >= 0 && y >= 0 && x < list[0].size() && y < list.size
    fun isInGrid(grid: Array<CharArray>): Boolean = x >= 0 && y >= 0 && x < grid[0].size && y < grid.size
    fun isInGrid(maxX: Int, maxY: Int): Boolean = x >= 0 && y >= 0 && x < maxX && y < maxY

    fun inRange(xRange: IntRange, yRange: IntRange) = x in xRange && y in yRange

    fun above(howMuch: Int = 1): Pos = Pos(x, y - howMuch)
    fun below(howMuch: Int = 1): Pos = Pos(x, y + howMuch)
    fun left(howMuch: Int = 1): Pos = Pos(x - howMuch, y)
    fun right(howMuch: Int = 1): Pos = Pos(x + howMuch, y)
    private fun nw(): Pos = Pos(x - 1, y - 1)
    fun ne(): Pos = Pos(x + 1, y - 1)
    fun sw(): Pos = Pos(x - 1, y + 1)
    fun se(): Pos = Pos(x + 1, y + 1)

    private fun Any?.size(): Int {
        return when (this) {
            is Collection<*> -> size
            is String -> length
            is IntArray -> size
            is CharArray -> size
            else -> {
                error("unable to get size for $this")
            }
        }

    }

    override fun manhattan(): Int = abs(x) + abs(y)
    override fun any(predicate: (Int) -> Boolean): Boolean = predicate(x) || predicate(y)

    operator fun minus(other: Pos): Pos = Pos(x - other.x, y - other.y)

    fun manhattanDistance(p: Pos): Int = abs(x - p.x) + abs(y - p.y)

    operator fun plus(other: Pos): Pos = Pos(x + other.x, y + other.y)
    fun lineTo(other: Pos): List<Pos> {
        val xDelta = (other.x - x).sign
        val yDelta = (other.y - y).sign
        val steps = maxOf((x - other.x).absoluteValue, (y - other.y).absoluteValue)
        return (1..steps).scan(this) { last, _ -> Pos(last.x + xDelta, last.y + yDelta) }
    }


    fun rotateRight(times: Int): Pos {
        return when (times % 4) {
            0 -> this
            1 -> Pos(-y, x)
            2 -> Pos(-x, -y)
            3 -> Pos(y, -x)
            else -> error("unable to rotate $this $times times")
        }
    }

    fun fill(pos: Pos): List<Pos> {
        return when {
            this.x == pos.x -> {
                if (y <= pos.y) {
                    (y..pos.y).map { Pos(x, it) }
                } else {
                    (y downTo pos.y).map { Pos(x, it) }
                }
            }
            this.y == pos.y -> {
                if (x <= pos.x) {
                    (x..pos.x).map { Pos(it, y) }
                } else {
                    (x downTo pos.x).map { Pos(it, y) }
                }
            }
            else -> {
                error("only line fill is supported")
            }
        }


    }

    companion object {
        fun getMinMax(coordinates: Collection<Pos>): Pair<Pair<Int, Int>, Pair<Int, Int>> {
            val xMin = coordinates.minOf { it.x }
            val xMax = coordinates.maxOf { it.x }
            val yMin = coordinates.minOf { it.y }
            val yMax = coordinates.maxOf { it.y }
            return (xMin to xMax) to (yMin to yMax)
        }
    }

}


data class Pos4D(val x: Int, val y: Int, val z: Int, val w: Int) : SpacePos() {
    override fun neighbours(): List<SpacePos> {
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

    override fun manhattan(): Int = abs(x) + abs(y) + abs(z) + abs(w)
    override fun any(predicate: (Int) -> Boolean): Boolean = listOf(x, y, z, w).any(predicate)
}
