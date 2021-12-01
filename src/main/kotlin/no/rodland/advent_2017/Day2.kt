package no.rodland.advent_2017

object Day2 {

    fun partOne(list: List<String>): Int {
        return list
            .spread()
            .map { it.maxOrNull()!! - it.minOrNull()!! }
                .sum()
    }

    fun partTwo(list: List<String>): Int {
        return list
                .spread()
                .flatMap { row ->
                    row.flatMap { value ->
                        row.map {
                            if (value % it == 0 && value != it) {
                                value / it
                            } else {
                                null
                            }
                        }.filterNotNull()
                    }
                }.sum()
    }

    private fun List<String>.spread(): List<List<Int>> {
        return map { it.split("\t", " ").map { it.toInt() } }
    }
}