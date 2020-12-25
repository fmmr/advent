package no.rodland.advent_2020

@Suppress("UNUSED_PARAMETER")
object Day25 {

    const val magic = 20201227L
    const val subject = 7L

    fun partOne(list: Pair<Long, Long>): Long {
        val (cardPK, doorPK) = list

        val cardLoop = getLoop(cardPK)
//        val doorLoop = getLoop(doorPK)
        val enc1 = transform(doorPK, cardLoop)
//        val enc2 = transform(cardPK, doorLoop)

        println("enc1: $enc1")
//        println("enc2: $enc2")

        return enc1
    }

    private fun getLoop(publicKey: Long): Int {
        var current = subject
        var loop = 1
        while (current != publicKey) {
            current = current * subject
            current = current % 20201227
            loop++
        }
        return loop
    }

    private fun transform(num: Long, loops: Int): Long {
        var current = num
        repeat(loops - 1) {
            current = current * num
            current = current % 20201227
        }
        return current
    }

    fun partTwo(list: Pair<Long, Long>): Int {
        val (cardPK, doorPK) = list
        return 2
    }
}
