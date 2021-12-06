package no.rodland.advent_2021

@Suppress("UNUSED_PARAMETER")
object Day06 {


    fun partOne(list: List<Int>): Int {
        val fishes = list.groupingBy { it }.eachCount()
        val ar = IntArray(9) { fishes[it] ?: 0 }
        return (1..80).fold(ar) { acc, i -> acc.cycle() }.sum()
    }

    fun IntArray.cycle(): IntArray {
        return IntArray(this.size) { idx ->
            when (idx) {
                6 -> this[0] + this[7]
                8 -> this[0]
                else -> this[idx + 1]
            }
        }
    }

    fun partTwo(list: List<Int>): Int {
        return 2
    }
}
