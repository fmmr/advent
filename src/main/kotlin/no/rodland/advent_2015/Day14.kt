package no.rodland.advent_2015

import kotlin.math.min

@Suppress("UNUSED_PARAMETER")
object Day14 {
    val regex = """(.+) can fly (\d+) km/s for (\d+) seconds, but then must rest for (\d+) seconds.""".toRegex()

    fun partOne(list: List<String>, seconds: Int): Int {
        return list.map { Reindeer(it) }.map { it.distanceAfter(seconds) }.maxOrNull()!!
    }

    fun partTwo(list: List<String>, seconds: Int): Int {
        val reindeers = list.map { Reindeer(it) }
        val leaders = reindeers.map { it to 0 }.toMap().toMutableMap()

        (1..seconds).forEach { afterSecond ->
            val leading = reindeers.maxByOrNull { deer -> deer.distanceAfter(afterSecond) }!!
            leaders[leading] = leaders[leading]!! + 1
        }
        return leaders.maxOf { it.value }
    }

    data class Reindeer(val name: String, val speed: Int, val flyTime: Int, val restTime: Int) {
        constructor(str: String, mr: MatchResult.Destructured = regex.find(str)!!.destructured) : this(
            mr.component1(),
            mr.component2().toInt(),
            mr.component3().toInt(),
            mr.component4().toInt(),
        )

        fun distanceAfter(seconds: Int): Int {
            val wholeRounds = seconds / (flyTime + restTime)
            val remainder = seconds % (flyTime + restTime)
            val flytimeOfRemainder = min(remainder, flyTime)
            return wholeRounds * (flyTime * speed) + flytimeOfRemainder * speed
        }
    }
}
