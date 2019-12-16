package no.rodland.advent_2019

object Day16 {


    fun partOne(list: List<Int>, numPhases: Int = 100): String {
        val result = getPhase(list, numPhases)
        return result.subList(0, 8).joinToString("") { it.toString() }
    }

    private fun getPhase(list: List<Int>, phase: Int): List<Int> {
        return if (phase == 1) {
            list.mapIndexed { idx, el ->
                val pattern = getPattern(idx + 1).take(list.size).toList()
                Math.abs(list.zip(pattern) { num, p -> num * p }.sum() % 10)
            }

        } else {
            getPhase(list.mapIndexed { idx, el ->
                val pattern = getPattern(idx + 1).take(list.size).toList()
                Math.abs(list.zip(pattern) { num, p -> num * p }.sum() % 10)
            }, phase - 1)
        }
    }

    fun getPattern(num: Int): Sequence<Int> {
        val basePattern = listOf(0, 1, 0, -1).flatMap { e -> (1..num).map { e } }
        val mod = basePattern.size
        return sequence {
            var count = 1
            while (true)
                yield(basePattern[count++ % basePattern.size])
        }
    }

    fun partTwo(list: List<Int>): Int {
        return 2
    }
}