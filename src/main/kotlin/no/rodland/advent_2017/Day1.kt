package no.rodland.advent_2017

object Day1 {

    fun partOne(str: String): Int {
        var lastChar: Char = 'a'
        val sum = str.fold(0) { acc, c ->
            if (lastChar == c) {
                lastChar = c
                acc + c.toString().toInt()
            } else {
                lastChar = c
                acc
            }
        }
        return if (str[0] == str[str.length - 1]) {
            str[0].toString().toInt() + sum
        } else {
            sum
        }
    }

    fun partTwo(str: String): Int {
        return str.subSequence(0, str.length / 2)
                .zip(str.subSequence(str.length / 2, str.length))
                .filter { it.first == it.second }
                .sumBy { it.first.toString().toInt() * 2 }

    }
}