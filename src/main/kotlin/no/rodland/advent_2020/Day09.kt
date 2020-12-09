package no.rodland.advent_2020

object Day09 {
    fun partOne(list: List<Long>, preAmble: Int): Long {
        val idx = (preAmble until list.size).first { idx -> !list.isValid(idx, preAmble) }
        return list[idx]
    }

    fun partTwo(list: List<Long>, number: Long, idx: Int): Long {
        val range = (2 until idx)
            .asSequence()
            .map { list.windowed(it, 1).firstOrNull { list -> list.sum() == number } }
            .first { it != null }
        return range!!.maxOrNull()!! + range.minOrNull()!!
    }

    private fun List<Long>.isValid(idx: Int, preAmble: Int): Boolean = get(idx).isComposable(subList(idx - preAmble, idx))

    private fun Long.isComposable(subList: List<Long>): Boolean = subList.elementPairs().any { it.first + it.second == this }

    private fun List<Long>.elementPairs(): Sequence<Pair<Long, Long>> = sequence {
        for (i in 0 until size - 1)
            for (j in i + 1 until size)
                yield(this@elementPairs[i] to this@elementPairs[j])
    }
}


