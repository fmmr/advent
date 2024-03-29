package no.rodland.advent_2021

@Suppress("UNUSED_PARAMETER")
object Day06 {


    fun partOne(list: List<Long>): Long {
        return countFishes(list, 80)
    }

    fun partTwo(list: List<Long>): Long {
        return countFishes(list, 256)
    }

    private fun countFishes(list: List<Long>, iterations: Int): Long {
        val fishes = list.groupingBy { it }.eachCount()
        val init = LongArray(9) { fishes[it.toLong()]?.toLong() ?: 0L }
        return (1..iterations).fold(init) { acc, _ -> acc.cycle() }.sum()
    }

    private fun LongArray.cycle(): LongArray {
        return LongArray(this.size) { idx ->
            when (idx) {
                6 -> this[0] + this[7]
                8 -> this[0]
                else -> this[idx + 1]
            }
        }
    }
}
