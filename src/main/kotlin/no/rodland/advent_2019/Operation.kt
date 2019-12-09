package no.rodland.advent_2019

class Operation(val code: Int) {
    val operation = code % 100

    val steps = when (operation) {
        1 -> 4
        2 -> 4
        3 -> 2
        4 -> 2
        5 -> 3
        6 -> 3
        7 -> 4
        8 -> 4
        9 -> 2
        99 -> 0
        else -> error("Unable to find steps for operation $operation")
    }

    fun mode(pos: Int): Int {
        return when (pos) {
            1 -> (code % 1000) / 100
            2 -> (code % 10000) / 1000
            3 -> (code % 100000) / 10000
            else -> error("Unable to find mode for operation $operation")
        }
    }

    override fun toString(): String {
        return "Operation(code=$code, operation=$operation, steps=$steps)"
    }
}