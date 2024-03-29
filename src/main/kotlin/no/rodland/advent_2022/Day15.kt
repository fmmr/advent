package no.rodland.advent_2022

import no.rodland.advent.Pos
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

// template generated: 28/11/2022
// Fredrik Rødland 2022

object Day15 {
    fun partOne(input: List<String>, row: Int = 10): Int {
        val areas = input.parse()
        val beacons = areas.map { it.beacon }
        val ranges = areas
            .map { it.rangeOnRow(row) }
            .filterNot { it.isEmpty() }
        val mergedRanges = ranges.merge().merge()
        return mergedRanges.sumOf { it.last - it.first + 1 } - beacons.filter { it.y == row }.distinct().size
    }

    // https://todd.ginsberg.com/post/advent-of-code/2022/day15/
    fun partTwo(input: List<String>, bound: Int): Long {
        val areas = input.parse()
        val range = (0..bound)
        val firstNotNullOf = areas.firstNotNullOf { area ->
            val up = Pos(area.sensor.x, area.sensor.y - area.manhattan - 1)
            val down = Pos(area.sensor.x, area.sensor.y + area.manhattan + 1)
            val left = Pos(area.sensor.x - area.manhattan - 1, area.sensor.y)
            val right = Pos(area.sensor.x + area.manhattan + 1, area.sensor.y)

            (up.lineTo(right) + right.lineTo(down) + down.lineTo(left) + left.lineTo(up))
                .filter { it.x in range && it.y in range }
                .firstOrNull { candidate -> areas.none { area -> area.inRange(candidate) } }
        }
        return firstNotNullOf.tuningFrequency()
    }

    private fun Pos.tuningFrequency(): Long {
        println("found $x  $y")
        return 4000000L * x + y
    }

    private fun Collection<IntRange>.merge(): MutableSet<IntRange> {
        val fold = fold<IntRange, MutableSet<IntRange>>(mutableSetOf()) { acc, intRange ->
            val overlap = acc.firstOrNull { it.overlap(intRange) }
            if (overlap == null) {
                acc += intRange
            } else {
                acc -= overlap
                acc += overlap.combine(intRange)
            }
            acc
        }
        println("size of fold: $size => ${fold.size}")
        return fold
    }

    private fun IntRange.combine(other: IntRange): IntRange {
        return min(first, other.first)..max(last, other.last)
    }

    private fun IntRange.overlap(other: IntRange): Boolean = first <= other.last && last >= other.first

    data class Area(val sensor: Pos, val beacon: Pos) {
        val manhattan = sensor.distanceTo(beacon)

        private val colRange = (sensor.x - manhattan)..(sensor.x + manhattan)
        private val rowRange = ((sensor.y - manhattan)..(sensor.y + manhattan))
        fun inRange(pos: Pos): Boolean = sensor.distanceTo(pos) <= manhattan

        private fun affectsRow(row: Int): Boolean = row in rowRange
        fun rangeOnRow(row: Int): IntRange {
            return when {
                !affectsRow(row) -> IntRange.EMPTY
                sensor.y == row -> colRange
                else -> {
                    val diff = manhattan - abs(sensor.y - row)
                    (sensor.x - diff)..(sensor.x + diff)
                }
            }
        }
    }


    // Sensor at x=2, y=18: closest beacon is at x=-2, y=15
    fun List<String>.parse(): List<Area> {
        return map {
            val sensorX = it.substringAfter("x=").substringBefore(", y=").toInt()
            val beaconX = it.substringAfterLast("x=").substringBefore(", y=").toInt()
            val sensorY = it.substringAfter("y=").substringBefore(": cl").toInt()
            val beaconY = it.substringAfterLast("y=").toInt()
            Area(Pos(sensorX, sensorY), Pos(beaconX, beaconY))
        }
    }
}

