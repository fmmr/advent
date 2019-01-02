package no.rodland.advent_2018

import get

object Day23 {
    fun partOne(list: List<String>): Int {
        val bots = list.map { NanoBot(it) }
        val mostPowerFull = bots.maxBy { it.sigalRadius }
        return bots.count { mostPowerFull?.inRange(it) ?: false }
    }

    fun partTwo(list: List<String>): Int {
        return 2
    }

    val re = """pos=<([-\d]+),([-\d]+),([-\d]+)>, r=([-\d]+)""".toRegex()

    class NanoBot(val x: Int, val y: Int, val z: Int, val sigalRadius: Int) {
        constructor(str: String) : this(re.get(str), re.get(str, 2), re.get(str, 3), re.get(str, 4))

        fun inRange(otherBot: NanoBot): Boolean {
            return distanceTo(otherBot) <= sigalRadius
        }

        fun distanceTo(other: NanoBot): Int {
            return Math.abs(other.x - x) + Math.abs(other.y - y) + Math.abs(other.z - z)
        }

    }


}