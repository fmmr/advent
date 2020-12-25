package no.rodland.advent_2020

@Suppress("UNUSED_PARAMETER")
object Day25 {
    fun partOne(list: Pair<Long, Long>): Long {
        val (cardPK, doorPK) = list
        val cardLoop = getLoop(cardPK)
        return transform(doorPK, cardLoop)
    }

    private fun getLoop(publicKey: Long): Int {
        val subject = 7L
        var current = subject
        var loop = 1
        while (current != publicKey) {
            current = calc(current, subject)
            loop++
        }
        return loop
    }

    private fun transform(num: Long, loops: Int): Long {
        var current = num
        repeat(loops - 1) {
            current = calc(current, num)
        }
        return current
    }

    private fun calc(current: Long, num: Long): Long {
        return (current * num) % 20201227
    }
}
