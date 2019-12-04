package no.rodland.advent_2019

object Day04 {
    fun partOne(input: Pair<Int, Int>): Int {
        return (input.first..input.second).partition { it.validPart1() }.first.size
    }


    fun partTwo(input: Pair<Int, Int>): Int {
        return (input.first..input.second).partition { it.validPart2() }.first.size
    }
}

private fun Int.validPart1(): Boolean {
    return isSixDigit() && hasTwoAdjacent() && neverDecrease()
}

private fun Int.validPart2(): Boolean {
    return isSixDigit() &&
            digits().groupBy { it }.entries.any { it.value.size == 2 } &&
            neverDecrease()
}

fun Int.hasTwoAdjacent(): Boolean {
    var previous = -1
    digits().forEach {
        if (it == previous) return true
        previous = it
    }
    return false
}

fun Int.neverDecrease(): Boolean {
    var previous = ' '
    toString().forEach {
        if (it < previous) return false
        previous = it
    }
    return true
}

fun Int.isSixDigit(): Boolean {
    return this in 100000..999999
}

fun Int.digits(): List<Int> {
    return toString().map { it.toString().toInt() }
}



