package no.rodland.advent_2018

import get

object Day23 {
    fun partOne(list: List<String>): Int {
        val bots = list.map { NanoBot(it) }
        val mostPowerFull = bots.maxBy { it.sigalRadius }
        return bots.count { mostPowerFull?.inRange(it) ?: false }
    }

    fun partTwo(list: List<String>): Int {
        val bots = list.map { NanoBot(it) }
        return 2
    }


    fun naivePartTwo(list: List<String>): NanoBot {
        val bots = list.map { NanoBot(it) }
        val maxX = bots.maxBy { it.x }!!.x
        val minX = bots.minBy { it.x }!!.x
        val maxY = bots.maxBy { it.y }!!.y
        val minY = bots.minBy { it.y }!!.y
        val maxZ = bots.maxBy { it.z }!!.z
        val minZ = bots.minBy { it.z }!!.z
        val points = (minX..maxX).flatMap { x ->
            (minY..maxY).flatMap { y ->
                (minZ..maxZ).map { z ->
                    val point = NanoBot(x, y, z, 0)
                    val num = bots.count { it.inRange(point) }
                    point to num
                }
            }
        }.maxBy { it.second }
        return points!!.first
    }

    val re = """pos=<([-\d]+),([-\d]+),([-\d]+)>, r=([-\d]+)""".toRegex()

    data class NanoBot(val x: Int, val y: Int, val z: Int, val sigalRadius: Int) {
        constructor(str: String) : this(re.get(str), re.get(str, 2), re.get(str, 3), re.get(str, 4))

        fun inRange(otherBot: NanoBot): Boolean {
            return distanceTo(otherBot) <= sigalRadius
        }

        fun distanceTo(other: NanoBot): Int {
            return Math.abs(other.x - x) + Math.abs(other.y - y) + Math.abs(other.z - z)
        }
    }
}