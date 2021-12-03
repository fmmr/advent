package no.rodland.advent_2021

object Day03 {
    fun partOne(list: List<String>): Int {
        val length = list[0].length
        val gamma = (0 until length).map { mostUsedBit(list, it) }.joinToString("")
        val epsilon = gamma.flipBits()
        return gamma.toInt(2) * epsilon.toInt(2)
    }

    fun partTwo(list: List<String>): Int {
        val oxygen = find(list, ::mostUsedBit)
        val co2 = find(list, ::leastUsedBit)
        return oxygen.toInt(2) * co2.toInt(2)
    }

    private fun find(list: List<String>, func: (List<String>, Int) -> Char) = list
        .indices
        .asSequence()
        .runningFold(list) { l, idx ->
            val bitCriteria = func(l, idx)
            l.filter { it[idx] == bitCriteria }
        }
        .first { it.size == 1 }
        .first()

    private fun String.flipBits() = map { if (it == '0') '1' else '0' }.joinToString("")

    private fun mostUsedBit(l: List<String>, idx: Int): Char {
        val (zero, one) = l.map { it[idx] }.partition { it == '0' }
        return if (zero.size > one.size) '0' else '1'
    }

    private fun leastUsedBit(l: List<String>, idx: Int): Char {
        val (zero, one) = l.map { it[idx] }.partition { it == '0' }
        return if (one.size < zero.size) '1' else '0'
    }
}
