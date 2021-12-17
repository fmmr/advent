package no.rodland.advent_2021

import no.rodland.advent.Pos
import kotlin.math.absoluteValue
import kotlin.math.sign

@Suppress("UNUSED_PARAMETER")
object Day17 {
    private val ORIGO = Pos(0, 0)

    // chriswk
    fun partOne(ranges: String): Int {
        //        val xRange = ranges.substringAfter(": x=").substringBefore(", y=").split("..").let { (from, to) -> from.toInt()..to.toInt() }
        val yRange = ranges.substringAfter("y=").split("..").let { (from, to) -> from.toInt()..to.toInt() }
        val minY = yRange.minOrNull()!!.absoluteValue
        return ((minY - 1) * minY) / 2
    }

    fun Pos.step(velocity: Pos): Pair<Pos, Pos> = Pos(x + velocity.x, y + velocity.y) to Pos(velocity.x - velocity.x.sign, velocity.y - 1)

    fun partTwo(ranges: String): Int {
        val xRange = ranges.substringAfter(": x=").substringBefore(", y=").split("..").let { (from, to) -> from.toInt()..to.toInt() }
        val yRange = ranges.substringAfter("y=").split("..").let { (from, to) -> from.toInt()..to.toInt() }
        val minY = yRange.minOrNull()!!.absoluteValue
        val yTests = minY downTo -minY
        val xTests = 2..xRange.maxOrNull()!!
        return yTests.flatMap { y -> xTests.map { x -> test(Pos(x, y), xRange, yRange) } }.filterNotNull().count()
    }

    private fun test(velocity: Pos, xRange: IntRange, yRange: IntRange): Pos? {
        return generateSequence(ORIGO to velocity) { it.first.step(it.second) }.takeWhile { it.first.y >= yRange.minOrNull()!! }.firstOrNull { it.first.inRange(xRange, yRange) }?.first
    }
}
