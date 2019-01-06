package no.rodland.advent_2017

object Day13 {

    fun partOne(list: List<String>): Int {
        val catches = getCatches(list, 0)
        return catches.map { it.first * it.second }.sum()
    }

    fun partTwo(list: List<String>): Int {
        var offset = 1
        var catches = getCatches(list, offset)

        while (catches.map { it.first * it.second }.sum() > 0) {
            catches = getCatches(list, offset++)
        }
        return offset - 1
    }

    private fun getCatches(list: List<String>, offset: Int): List<Pair<Int, Int>> {
        val catches = list
                .map { it.split(": ") }
                .map { (it[0].toInt() + offset) to it[1].toInt() }
                .filter { it.first % (it.second * 2 - 2) == 0 }
        return catches
    }
}