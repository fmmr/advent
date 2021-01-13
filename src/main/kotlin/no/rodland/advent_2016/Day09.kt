package no.rodland.advent_2016

@Suppress("UNUSED_PARAMETER")
object Day09 {
    fun partOne(str: String): Int {
        return decompressPart1(str).length
    }

    fun partTwo(str: String): Long {
        return getDecompressedLengthPart2(str)
    }

    private fun getDecompressedLengthPart2(str: String): Long {
        var counter = 0L
        val sb = StringBuilder()
        var buffer = sb
        var i = 0
        while (i < str.length) {
            val c = str[i++]
            if (c == '(') {
                buffer = StringBuilder()
            } else if (c == ')' && buffer != sb) {
                val (num, times) = buffer.toString().split("x").map { it.toInt() }
                val subStr = str.substring(i, i + num)
                counter += times * getDecompressedLengthPart2(subStr)
                i += num
                buffer = sb
            } else {
                buffer.append(c)
                if (buffer == sb) {
                    counter++
                }
            }
        }
        return counter
    }

    fun decompressPart1(str: String): String {
        val sb = StringBuilder()
        var buffer = sb
        var i = 0
        while (i < str.length) {
            val c = str[i++]
            if (c == '(') {
                buffer = StringBuilder()
            } else if (c == ')' && buffer != sb) {
                val (num, times) = buffer.toString().split("x").map { it.toInt() }
                val subStr = str.substring(i, i + num).repeat(times)
                sb.append(subStr)
                i += num
                buffer = sb
            } else {
                buffer.append(c)
            }
        }
        return sb.toString()
    }

}
