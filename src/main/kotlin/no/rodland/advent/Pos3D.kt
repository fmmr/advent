package no.rodland.advent

import kotlin.math.abs

data class Pos3D(val x: Int, val y: Int, val z: Int) {
    constructor(triple: Triple<Int, Int, Int>) : this(triple.first, triple.second, triple.third)

    fun neighbors(): List<Pos3D> {
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

    fun manhattan(): Int = abs(x) + abs(y) + abs(z)
    operator fun minus(other: Pos3D): Pos3D = Pos3D(x - other.x, y - other.y, z - other.z)
    operator fun plus(other: Pos3D): Pos3D = Pos3D(x + other.x, y + other.y, z + other.z)

    companion object {
        fun getMinMax(coordinates: Collection<Pos3D>): Triple<Pair<Int, Int>, Pair<Int, Int>, Pair<Int, Int>> {
            val xmin = coordinates.map { it.x }.minOrNull()!!
            val xmax = coordinates.map { it.x }.maxOrNull()!!
            val ymin = coordinates.map { it.y }.minOrNull()!!
            val ymax = coordinates.map { it.y }.maxOrNull()!!
            val zmin = coordinates.map { it.z }.minOrNull()!!
            val zmax = coordinates.map { it.z }.maxOrNull()!!
            return Triple((xmin to xmax), (ymin to ymax), (zmin to zmax))
        }
    }
}