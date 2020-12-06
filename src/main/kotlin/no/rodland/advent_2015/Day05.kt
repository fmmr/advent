package no.rodland.advent_2015

object Day05 {

    private val vowels = setOf('a', 'e', 'i', 'o', 'u')
    private val badCombos = setOf("ab", "cd", "pq", "xy")
    private val twoLetterTwiceregex = "(..).*\\1".toRegex()

    fun partOne(list: List<String>): Int {
        return list.count { it.isNicePart1() }
    }

    fun partTwo(list: List<String>): Int {
        return list.count { it.isNicePart2() }
    }

    private fun String.isNicePart1() = threeVovwels() && appearsTwice() && notBadComobos()

    private fun String.isNicePart2() = pairOfTwoLetters() && repeatingLetter()

    private fun String.pairOfTwoLetters(): Boolean = contains(twoLetterTwiceregex)

    private fun String.repeatingLetter(): Boolean = windowed(size = 3, step = 1).any { it[0] == it[2] }

    private fun String.notBadComobos(): Boolean = badCombos.none { contains(it) }

    private fun String.appearsTwice(): Boolean = windowed(size = 2, step = 1).any { it[0] == it[1] }

    private fun String.threeVovwels(): Boolean = count { it.isVowel() } > 2

    private fun Char.isVowel() = this in vowels
}

