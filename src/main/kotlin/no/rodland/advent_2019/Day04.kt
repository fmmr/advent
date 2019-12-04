package no.rodland.advent_2019

object Day04 {
    fun partOne(input: Pair<Int, Int>): Int {
        return (input.first..input.second).partition { it.valid() }.first.size
    }


    fun partTwo(list: List<String>): Int {
        return 2
    }
}

private fun Int.valid(): Boolean {
    return isSixDigit() && twoAdjacentEqual() && neverDecrease()
}

fun Int.twoAdjacentEqual(): Boolean {
    var previous = ' '
    toString().forEach {
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


