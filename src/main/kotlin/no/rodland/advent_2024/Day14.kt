package no.rodland.advent_2024

import no.rodland.advent.Day
import no.rodland.advent.Pos
import product

// template generated: 14/12/2024
// Fredrik Rødland 2024

class Day14(val input: List<String>, val width: Int, val height: Int) : Day<Long, Int, List<Day14.Robot>> {

    private val robots = input.parse()

    override fun partOne(): Long {
        return robots.map { it.move(100, width, height) }
            .groupBy { it.quadrant(width, height) }
            .filterKeys { i: Int -> i != 0 } // middle
            .values
            .map { it.size }
            .product()
    }

    override fun partTwo(): Int {
        // no overlaps?
        val hei = (0..width * height).map { i ->
            val overlaps = robots.map { it.move(i, width, height) }
                .groupBy { it }
                .count { (_, v) -> v.size > 1 }
            i to overlaps
        }.first { it.second == 0 }
        return hei.first
    }

    data class Robot(val pos: Pos, val vel: Pos) {
        fun move(iterations: Int, width: Int, height: Int): Pos {
            val x = (pos.x + (((iterations) * vel.x) % width) + width) % width
            val y = (pos.y + (((iterations) * vel.y) % height) + height) % height
            return Pos(x, y)
        }
    }

    private fun Pos.quadrant(width: Int, height: Int): Int {
        val firstX = this.x < (width / 2)
        val lastX = this.x >= ((width / 2) + 1)
        val firstY = this.y < (height / 2)
        val lastY = this.y >= ((height / 2) + 1)
        return when {
            firstX && firstY -> 1
            lastX && firstY -> 2
            firstX && lastY -> 3
            lastX && lastY -> 4
            else -> 0
        }
    }

    override fun List<String>.parse(): List<Robot> {
        return map { line ->
            val (px, py) = line.substringAfter("p=").substringBefore(" v=").split(",").map { it.toInt() }
            val (vx, vy) = line.substringAfter("v=").split(",").map { it.toInt() }
            Robot(Pos(px, py), Pos(vx, vy))
        }
    }

    override val day = "14".toInt()
}

