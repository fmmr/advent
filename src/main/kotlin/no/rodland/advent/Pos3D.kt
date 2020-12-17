package no.rodland.advent

import kotlin.math.abs

data class Pos3D(val x: Int, val y: Int, val z: Int) {

//    fun distanceTo(other: Pos3D): Int {
//        return Math.abs(other.x - x) + Math.abs(other.y - y) + Math.abs(other.z - z)
//    }


    fun neighbors(): List<Pos3D> {
        return listOf(
            Pos3D(x, y - 1, z),
            Pos3D(x - 1, y, z),
            Pos3D(x + 1, y, z),
            Pos3D(x, y + 1, z),
            Pos3D(x - 1, y - 1, z),
            Pos3D(x + 1, y - 1, z),
            Pos3D(x - 1, y + 1, z),
            Pos3D(x + 1, y + 1, z),
            Pos3D(x, y, z - 1),
            Pos3D(x, y - 1, z - 1),
            Pos3D(x - 1, y, z - 1),
            Pos3D(x + 1, y, z - 1),
            Pos3D(x, y + 1, z - 1),
            Pos3D(x - 1, y - 1, z - 1),
            Pos3D(x + 1, y - 1, z - 1),
            Pos3D(x - 1, y + 1, z - 1),
            Pos3D(x + 1, y + 1, z - 1),
            Pos3D(x, y, z + 1),
            Pos3D(x, y - 1, z + 1),
            Pos3D(x - 1, y, z + 1),
            Pos3D(x + 1, y, z + 1),
            Pos3D(x, y + 1, z + 1),
            Pos3D(x - 1, y - 1, z + 1),
            Pos3D(x + 1, y - 1, z + 1),
            Pos3D(x - 1, y + 1, z + 1),
            Pos3D(x + 1, y + 1, z + 1),
        )
    }

//    fun angle(another: Pos3D) = atan2((another.x - x).toDouble(), (another.y - y).toDouble())


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