package no.rodland.advent_2015

object Day10 {
    fun partOne(number: String, i: Int): Int {
        return (1..i).fold(number) { acc, _ -> next(acc) }.length
    }

    fun next(number: String) = sequence {
        var lastChar = 'a'
        var count = 0
        number.forEach {
            if (it == lastChar) {
                count++
            } else {
                if (count > 0) {
                    yield(count to lastChar)
                }
                count = 1
                lastChar = it
            }
        }
        yield(count to lastChar)
    }
        .map { "${it.first}${it.second}" }
        .joinToString("")
}