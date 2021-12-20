package no.rodland.advent_2021

@Suppress("UNUSED_PARAMETER")
object Day20 {
    fun partOne(list: List<String>): Int {
        val (algorithm, grid) = list.parse()
        return 2
    }

    fun partTwo(list: List<String>): Int {
        return 2
    }

    private fun List<String>.parse(): Pair<String, Array<CharArray>> = first() to drop(2).map { it.toCharArray() }.toTypedArray()
}

