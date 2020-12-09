package no.rodland.advent_2020

object Day09 {
    fun partOne(list: List<Long>, preAmble: Int): Long {
        val idx = (preAmble until list.size).first { idx ->
            !list.isValid(idx, preAmble)
        }
        val l = list[idx]
//        println("found $l at $idx")
        return l
    }

    fun partTwo(list: List<Long>, number: Long, idx: Int): Long {
        val range = (2 until idx).map {
            val resultList = list.windowed(it, 1, true).firstOrNull { list ->
                list.sum() == number
            }
            resultList
        }.first { it != null }
        return range!!.maxOrNull()!! + range.minOrNull()!!
    }


    private fun List<Long>.isValid(idx: Int, preAmble: Int): Boolean = get(idx).isComposable(subList(idx - preAmble, idx))

    private fun Long.isComposable(subList: List<Long>): Boolean = elementPairs(subList).any { it.first + it.second == this }

    fun elementPairs(arr: List<Long>): Sequence<Pair<Long, Long>> = sequence {
        for (i in 0 until arr.size - 1)
            for (j in i + 1 until arr.size)
                yield(arr[i] to arr[j])
    }

}


