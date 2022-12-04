package no.rodland.advent_2022

// template generated: 28/11/2022
// Fredrik Rødland 2022

object Day04 {
    fun partOne(list: List<String>): Int {
        return list.map { CleaningPair(it) }.count { it.fullyContained }
    }

    fun partTwo(list: List<String>): Int {
        return list.map { CleaningPair(it) }.count { it.overlap }
    }

    data class CleaningPair(val input: String) {
        val first = input.split(",").first().let { firstStr -> firstStr.split("-").let { it.first().toInt()..it.last().toInt() } }
        val second = input.split(",").last().let { secondStr -> secondStr.split("-").let { it.first().toInt()..it.last().toInt() } }

        val fullyContained = first.all { it in second } || second.all { it in first }
        val overlap = first.any { it in second } || second.any { it in first }
    }
}
