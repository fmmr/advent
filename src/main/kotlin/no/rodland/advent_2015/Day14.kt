package no.rodland.advent_2015

@Suppress("UNUSED_PARAMETER")
object Day14 {
    val regex = """(.+) can fly (\d+) km/s for (\d+) seconds, but then must rest for (\d+) seconds.""".toRegex()

    fun partOne(list: List<String>): Int {
        val reindeers = list.map { Reindeer(it) }
        return 2
    }

    fun partTwo(list: List<String>): Int {
        return 2
    }

    data class Reindeer(val name: String, val fly: Int, val seconds: Int, val rest: Int) {
        constructor(str: String, mr: MatchResult.Destructured = regex.find(str)!!.destructured) : this(
            mr.component1(),
            mr.component2().toInt(),
            mr.component3().toInt(),
            mr.component4().toInt(),
        )
    }
}
