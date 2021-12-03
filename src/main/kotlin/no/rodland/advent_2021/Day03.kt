package no.rodland.advent_2021

object Day03 {
    fun partOne(list: List<String>): Int {
        val gamma = (0 until list[0].length).map { mostUsedBit(list, it) }.joinToString("")
        val epsilon = gamma.flipBits()
        return gamma.toInt(2) * epsilon.toInt(2)
    }

    fun partTwo(list: List<String>): Int {
        val oxygen = findRating(list, ::mostUsedBit)
        val co2 = findRating(list, ::leastUsedBit)
        return oxygen.toInt(2) * co2.toInt(2)
    }

    private fun findRating(list: List<String>, bitCriteria: (List<String>, Int) -> Char) = list
        .indices
        .asSequence()
        .runningFold(list) { l, idx -> l.filter { it[idx] == bitCriteria(l, idx) } }
        .first { it.size == 1 }
        .first()

    // could be implemented with xor FF - "101011" xor "111111" => "010100"
    private fun String.flipBits() = map { it.flip() }.joinToString("")

    private fun Char.flip() = if (this == '0') '1' else '0'

    private fun mostUsedBit(l: List<String>, idx: Int): Char = if (2 * l.count { it[idx] == '1' } >= l.size) '1' else '0'

    private fun leastUsedBit(l: List<String>, idx: Int) = mostUsedBit(l, idx).flip()
}
