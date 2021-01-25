package no.rodland.advent_2017

@Suppress("UNUSED_PARAMETER")
object Day15 {

    private const val factorA = 16807
    private const val factorB = 48271
    private val bitmask16LSB = "00000000000000001111111111111111".toLong(2)

    fun partOne(list: List<Int>): Int {
        val (a, b) = list.map { it.toLong() }
        val seqA = getSeq(a, factorA)
        val seqB = getSeq(b, factorB)
        val zip = seqA.zip(seqB).take(40000000)
        return zip.count { it.first == it.second }
    }

    fun partTwo(list: List<Int>): Int {
        val (a, b) = list.map { it.toLong() }
        val seqA = getSeq(a, factorA, 4)
        val seqB = getSeq(b, factorB, 8)
        val zip = seqA.zip(seqB).take(5000000)
        return zip.count { it.first == it.second }
    }

    @Suppress("SameParameterValue")
    private fun getSeq(initial: Long, factor: Int, filterModValue: Int? = null) = generateSequence(initial) { l: Long ->
        ((factor * l) % 2147483647L)
    }
            .filter { (filterModValue == null) || (it % filterModValue == 0L) }
            .map { (it and bitmask16LSB) }
            .drop(1)

}
