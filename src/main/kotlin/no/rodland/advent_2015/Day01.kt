package no.rodland.advent_2015

object Day01 {
    fun partOne(list: String): Int {
        return traverse(list)
    }

    fun partTwo(list: String): Int {
        return traverse(list, true)
    }

    private fun traverse(list: String, stopOnBasement: Boolean = false) = list.foldIndexed(0) { idx, acc, c ->
        val newAcc = if (c == '(') acc + 1 else acc - 1
        if (stopOnBasement && newAcc < 0) {
            // println("A IDX: $idx, acc: $acc, c: $c")
            return idx + 1
        }
        newAcc
    }
}
