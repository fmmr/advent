package no.rodland.advent_2023

import cartesianPairs
import no.rodland.advent.Day

// template generated: 24/12/2023
// Fredrik Rødland 2023

// https://todd.ginsberg.com/post/advent-of-code/2023/day24/
class Day24(val input: List<String>) : Day<Int, Long, List<Day24.HailStone>> {

    private val parsed = input.parse()

    override fun partOne(): Int {
        val range = if (parsed.size == 5) 7.0..27.0 else 200000000000000.0..400000000000000.0
        return checkPart1(range)
    }

    private fun checkPart1(range: ClosedFloatingPointRange<Double>) = parsed
        .cartesianPairs()
        .filter { it.first != it.second }
        .mapNotNull { it.first.crosses(it.second) }
        .count { (x, y, _) -> x in range && y in range }

    override fun partTwo(): Long {
        return 2
    }

    data class HailStone(val pos: Pos3DLong, val vel: Pos3DLong) {
        private val slope = if (vel.x == 0L) Double.NaN else vel.y / vel.x.toDouble()

        fun crosses(other: HailStone): Intersection? {
            if (slope.isNaN() || other.slope.isNaN() || slope == other.slope) return null
            val c = pos.y - slope * pos.x
            val otherC = other.pos.y - other.slope * other.pos.x
            val x = (otherC - c) / (slope - other.slope)
            val t1 = (x - pos.x) / vel.x
            val t2 = (x - other.pos.x) / other.vel.x
            if (t1 < 0 || t2 < 0) return null
            val y = slope * (x - pos.x) + pos.y
            return Intersection(x, y, t1)
        }
    }

    data class Intersection(val x: Double, val y: Double, val time: Double)

    data class Pos3DLong(val x: Long, val y: Long, val z: Long)

    override fun List<String>.parse(): List<HailStone> {
        // 20, 19, 15 @  1, -5, -3

        return map { line ->
            val split = line.split("[@, ]+".toRegex()).filterNot { it.isEmpty() }
            HailStone(Pos3DLong(split[0].toLong(), split[1].toLong(), split[2].toLong()), Pos3DLong(split[3].toLong(), split[4].toLong(), split[5].toLong()))
        }
    }

    override val day = "24".toInt()
}

