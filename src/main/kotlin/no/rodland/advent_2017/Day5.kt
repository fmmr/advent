package no.rodland.advent_2017

object Day5 {

    fun partOne(list: MutableList<Int>): Int {
        return walkThroughIt(list, increase(list))
    }

    fun partTwo(list: MutableList<Int>): Int {
        return walkThroughIt(list, decrease(list))
    }

    private fun increase(list: MutableList<Int>): (Int) -> Int {
        return { i: Int -> list[i]++ }
    }

    private fun decrease(list: MutableList<Int>): (Int) -> Int {
        return { i: Int ->
            if (list[i] >= 3) {
                list[i]--
            } else {
                list[i]++
            }
        }
    }

    private fun walkThroughIt(list: MutableList<Int>, func: (Int) -> Int): Int {
        var i = 0
        var numSteps = 0
        while (!(i >= list.size || i < 0)) {
            val stepsToMove = func(i)
            i += stepsToMove
            numSteps++
        }
        return numSteps
    }

}