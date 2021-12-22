package no.rodland.advent_2021

import no.rodland.advent.Pos3D
import kotlin.math.max
import kotlin.math.min

// heavily "inspired" by https://github.com/MikeEnRegalia/AdventOfCode2021/blob/main/kotlin/src/main/kotlin/Day22.kt
object Day22 {

    fun partOneNaive(list: List<String>): Long {
        val instructions = list.parse().filter { it.isPartOne }
        return instructions.fold<Ranges, Set<Pos3D>>(emptySet()) { acc, ranges ->
            val pos = ranges.toPos3D()
            when (ranges.on) {
                false -> acc - pos
                true -> acc + pos
            }
        }.size.toLong()
    }

    fun partOne(list: List<String>): Long {
        val instructions = list.parse().filter { it.isPartOne }
        return instructions
            .fold(setOf<Ranges>()) { space, r -> space.add(r) }
            .sumOf { it.size }
    }

    fun partTwo(list: List<String>): Long {
        val instructions = list.parse()
        return instructions
            .fold(setOf<Ranges>()) { space, r -> space.add(r) }
            .sumOf { it.size }
    }

    private fun List<String>.parse() = map { it.split(" ") }.map { (first, second) -> second.toRanges(first == "on") }


    private fun String.toRanges(on: Boolean): Ranges {
        val split = split(",")
        return Ranges(split[0].toInRange(), split[1].toInRange(), split[2].toInRange(), on)
    }

    private fun String.toInRange(): IntRange = substringAfter("=").split("..").let { (start, stop) -> IntRange(start.toInt(), stop.toInt()) }

    fun Set<Ranges>.add(ranges: Ranges): Set<Ranges> = buildSet {
        ranges.also { r ->
            val (overlapping, nonOverlapping) = this@add.partition { it.overlaps(r) }
            addAll(nonOverlapping)
            addAll(overlapping.flatMap { it.remove(r) })
            if (ranges.on) add(r)
        }
    }

    data class Ranges(val x: IntRange, val y: IntRange, val z: IntRange, val on: Boolean) {
        val isPartOne by lazy { listOf(x, y, z).all { it.intersect(-50..50)?.size() == it.toList().size.toLong() } }
        val size by lazy { x.size() * y.size() * z.size() }

        private fun intersect(other: Ranges): Ranges? {
            return x.intersect(other.x)?.let { x ->
                y.intersect(other.y)?.let { y ->
                    z.intersect(other.z)?.let { z ->
                        Ranges(x, y, z, on)
                    }
                }
            }
        }

        fun overlaps(other: Ranges) = other.x.intersect(x) != null && other.y.intersect(y) != null && other.z.intersect(z) != null
        fun includes(other: Ranges) = x.includes(other.x) && y.includes(other.y) && z.includes(other.z)
        fun remove(r: Ranges): Set<Ranges> {
            if (r.includes(this)) return setOf()
            val i = intersect(r) ?: return setOf(this)
            val beyond = Ranges(x, y, i.z.last + 1..z.last, on)
            val before = Ranges(x, y, z.first until i.z.first, on)
            val left = Ranges(x.first until i.x.first, y, i.z, on)
            val right = Ranges(i.x.last + 1..x.last, y, i.z, on)
            val below = Ranges(i.x, y.first until i.y.first, i.z, on)
            val above = Ranges(i.x, i.y.last + 1..y.last, i.z, on)
            return listOfNotNull(beyond, before, left, right, below, above).filterNot { it.size == 0L }.toSet()
        }

        fun toPos3D(): Set<Pos3D> = x.flatMap { xVal ->
            y.flatMap { yVal ->
                z.map { zVal -> Pos3D(xVal, yVal, zVal) }
            }
        }.toSet()
    }

    fun IntRange.size() = last - first + 1L

    private fun IntRange.intersect(o: IntRange): IntRange? {
        val maxFirst = max(first, o.first)
        val minLast = min(last, o.last)
        return if (maxFirst > minLast) null else maxFirst..minLast
    }

    private fun IntRange.includes(o: IntRange) = first <= o.first && last >= o.last
}

