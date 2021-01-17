package no.rodland.advent_2016

@Suppress("UNUSED_PARAMETER")
object Day15 {

    // "Disc #1 has 17 positions; at time=0, it is at position 1."
    val regex = "Disc #(\\d) has (\\d+) positions; at time=0, it is at position (\\d).".toRegex()

    fun partOne(list: List<String>): Int {
        val discs = list.map { Disc(it) }
        return 2
    }

    fun partTwo(list: List<String>): Int {
        return 2
    }

    data class Disc(val num: Int, val positions: Int, val start: Int) {
        constructor(str: String, r: MatchResult.Destructured = regex.find(str)!!.destructured) : this(r.component1().toInt(), r.component2().toInt(), r.component3().toInt())

    }
}
