package no.rodland.advent_2021

@Suppress("UNUSED_PARAMETER")
object Day03 {
    fun partOne(list: List<String>): Int {
        val length = list[0].length
        val gamma = (0 until length).map { idx ->
            mostUsedBit(list, idx)
        }.joinToString("")

        val epsilon = gamma.flipBits()
        val powerConsumption = gamma.toInt(2) * epsilon.toInt(2)
        return powerConsumption
    }

    private fun String.flipBits() = map { if (it == '0') '1' else '0' }.joinToString("")

    fun partTwo(list: List<String>): Int {
        val length = list[0].length
        val oxygen = (0 until length).fold(list) { l, idx ->
            val bitCriteria = mostUsedBit(l, idx)
            l.filter { it[idx] == bitCriteria }
        }.first()
        val c02 = (0 until length).fold(list) { l, idx ->
            val bitCriteria = leastUsedBit(l, idx)
            l.filter { it[idx] == bitCriteria }
        }
        println("oxygen = ${oxygen}")
        println("oxygen = ${oxygen.toInt(2)}")
        println("c02 = ${c02}")
        return 2
    }

    private fun mostUsedBit(l: List<String>, idx: Int): Char {
        val (zero, one) = l.map { it[idx] }.partition { it == '0' }
        return if (zero.size > one.size) '0' else '1'
    }

    private fun leastUsedBit(l: List<String>, idx: Int): Char {
        val (zero, one) = l.map { it[idx] }.partition { it == '0' }
        return if (one.size < zero.size) '1' else '0'
    }
}
