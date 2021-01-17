package no.rodland.advent_2016

@Suppress("UNUSED_PARAMETER")
object Day16 {
    fun partOne(str: String, diskLength: Int): String {
        val data = generateSequence(str) { calcOneStep(it) }.first { it.length >= diskLength }.take(diskLength)
        val checkSum = data.toCheckSum()
        return checkSum
    }


    fun calcOneStep(a: String): String {
        return a + "0" + a.reversed().map { if (it == '1') '0' else '1' }.joinToString("")
    }

    fun String.toCheckSum(): String {
        return generateSequence(this) { str ->
            str.chunked(2).joinToString("") {
                when (it) {
                    "00", "11" -> "1"
                    else -> "0"
                }
            }
        }.first { it.length % 2 != 0 }
    }

    fun partTwo(str: String): Int {
        return 2
    }
}

