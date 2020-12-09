package no.rodland.advent_2015

object Day11 {
    fun partOne(password: String): String {
        var pwd = password.next()
        while (!pwd.valid()) {
            pwd = pwd.next()
        }
        return pwd
    }

    fun String.next(): String {
        val incr = this[length - 1].incr()
        return if (incr != 'a') {
            this.substring(0, length - 1) + incr
        } else {
            this.substring(0, length - 1).next() + 'a'
        }
    }

    fun Char.incr(): Char = if (this == 'z') {
        'a'
    } else {
        this + 1
    }


    fun String.valid(): Boolean = hasIncreasingLetters() && allowedCharacters() && pairs()
    fun String.hasIncreasingLetters(): Boolean = windowed(3).any { it[2] == it[1] + 1 && it[1] == it[0] + 1 }
    fun String.allowedCharacters() = all { it !in setOf('i', 'o', 'l') }
    fun String.pairs(): Boolean {
        val w = windowed(2).mapIndexed { index: Int, s: String -> index to s }.filter { it.second[0] == it.second[1] }
        return if (w.size == 2) {
            w[1].first - w[0].first > 1
        } else {
            w.size > 2
        }

    }

}
