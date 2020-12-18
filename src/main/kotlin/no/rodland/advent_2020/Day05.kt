package no.rodland.advent_2020

object Day05 {
    fun partOne(list: List<String>): Int {
        return list.map { seatIdSimple(it) }.maxByOrNull { it }!!
    }

    fun partTwo(list: List<String>): Int {
        val ids = list.map { seatIdSimple(it) }.toSet()
        val min = ids.minOrNull()!!
        val max = ids.maxOrNull()!!
        return ((min..max) - ids).first()
    }

    private fun seatIdSimple(boarding: String): Int {
        return boarding
            .replace("[BR]".toRegex(), "1")
            .replace("[FL]".toRegex(), "0")
            .toInt(2)
    }
}