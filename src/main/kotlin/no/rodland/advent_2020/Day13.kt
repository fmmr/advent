package no.rodland.advent_2020

object Day13 {
    fun partOne(list: List<String>): Int {
        val earliestDeparture = list[0].toInt()
        val busses = list[1].split(",").filterNot { it == "x" }.map { it.toInt() }
        val int: Int? = null
        val pair = generateSequence(earliestDeparture to int) { (nextInt, _) ->
            (nextInt + 1) to busses.firstOrNull { nextInt % it == 0 }

        }.takeWhileInclusive { it.second == null }.last()
        val minute = pair.first - 1
        val bus = pair.second!!
        return (bus * (minute - earliestDeparture))
    }

    fun partTwo(list: List<String>): Int {
        return 2
    }

    fun <T> Sequence<T>.takeWhileInclusive(pred: (T) -> Boolean): Sequence<T> {
        var shouldContinue = true
        return takeWhile {
            val result = shouldContinue
            shouldContinue = pred(it)
            result
        }
    }
}
