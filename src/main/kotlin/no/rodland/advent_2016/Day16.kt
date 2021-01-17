package no.rodland.advent_2016

@Suppress("UNUSED_PARAMETER")
object Day16 {
    fun partOne(str: String, diskLength: Int): String {
        val data = generateSequence(str) { calcOneStep(it) }.first { it.length >= diskLength }.take(diskLength)
        val checkSum = data.toCheckSum()
        return checkSum
    }


    fun calcOneStep(a: String): String {
        val b = a.reversed().replace("0", "X").replace("1", "0").replace("X", "1")
        return a + "0" + b
    }

    fun String.toCheckSum(): String {
        return generateSequence(this) { str ->
            str.chunked(2).map {
                when (it) {
                    "00", "11" -> "1"
                    else -> "0"
                }
            }.joinToString("")
        }.first { it.length % 2 != 0 }
    }

    fun partTwo(str: String): Int {
        return 2
    }
}

