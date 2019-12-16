package no.rodland.advent_2019

object Day16 {
    fun partOne(list: List<Int>, numPhases: Int = 100): String {
        val result = getPhase(list, numPhases)
        return result.subList(0, 8).joinToString("") { it.toString() }
    }

    fun partTwo(list: List<Int>, numPhases: Int = 100, repeat: Int): String {
        val offset = list.subList(0, 7).joinToString("").toInt()
        val longList = (1..repeat).flatMap { list }.toMutableList()
        // num digits in input: 650
        // num digits * 10k= 6500000
        // offset is 5978199

        // println(longList.size / offset <= 2)
        // offset is almost at the end => only 1s from offset to end
        // we can calculate backwards and sum the next with the current (and % 10) for each element (phase times)

        (1..numPhases).forEach {
            for (i in (longList.size - 1) downTo offset) {
                longList[i - 1] = (longList[i - 1] + longList[i]) % 10
            }
        }
        return longList.subList(offset, 8 + offset).joinToString("") { it.toString() }
    }

    private tailrec fun getPhase(list: List<Int>, phase: Int): List<Int> {
        return if (phase == 1) {
            list.mapIndexed({ idx, el -> step(idx, list) })
        } else {
            getPhase(list.mapIndexed({ idx, el -> step(idx, list) }), phase - 1)
        }
    }

    private fun step(idx: Int, list: List<Int>): Int {
        val pattern = getPattern(idx + 1).take(list.size).toList()
        val subList = list.subList(idx, list.size)
        return Math.abs(subList.zip(pattern) { num, p -> num * p }.sum() % 10)
    }

    fun getPattern(num: Int): Sequence<Int> {
        val basePattern = listOf(0, 1, 0, -1).flatMap { e -> (1..num).map { e } }
        val mod = basePattern.size
        return sequence {
            var count = num
            while (true)
                yield(basePattern[count++ % mod])
        }
    }
}