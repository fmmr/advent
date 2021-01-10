package no.rodland.advent_2015

@Suppress("UNUSED_PARAMETER")
object Day20 {
    fun partOne(input: Long): Int {
        val max = 1000000
        val houses = IntArray(max)
        (1..max).forEach { elf ->
            var visited = elf
            while (visited < max) {
                houses[visited] += elf * 10
                visited += elf
            }
        }
        return houses.indexOfFirst { it >= input }
    }


    fun partTwo(input: Long): Int {
        val max = 1000000
        val houses = IntArray(max)
        (1..max).forEach { elf ->
            var visited = elf
            while (visited < max && visited <= elf * 50) {
                houses[visited] += elf * 11
                visited += elf
            }
        }
        return houses.indexOfFirst { it >= input }
    }


    fun presents(house: Int): Pair<Int, Int> {
        val i = when (house) {
            1 -> 10
            2 -> 30
            else -> (1..(house / 2 + 1))
                    .filter { house % it == 0 }
                    .map { it * 10 }
                    .sum() + (house * 10)
        }
        return house to i
    }
}
