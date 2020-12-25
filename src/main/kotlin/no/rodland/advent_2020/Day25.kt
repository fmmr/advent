package no.rodland.advent_2020

@Suppress("UNUSED_PARAMETER")
object Day25 {
    fun partOne(list: Pair<Long, Long>): Long {
        val (cardPK, doorPK) = list
        return transform(doorPK, getLoop(cardPK))
    }

    private fun getLoop(publicKey: Long): Int = generateSequence(1L) { it.calc() }.indexOf(publicKey)

    private fun transform(num: Long, loops: Int) = generateSequence(1L) { it.calc(num) }.drop(loops).first()

    private fun Long.calc(num: Long = 7L): Long = (this * num) % 20201227
}
