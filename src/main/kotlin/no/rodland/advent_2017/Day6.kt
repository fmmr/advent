package no.rodland.advent_2017

object Day6 {

    fun partOne(list: List<Int>): Int {
        return doTheDataDance(list).first
    }

    fun partTwo(list: List<Int>): Int {
        return doTheDataDance(list).second
    }

    private fun doTheDataDance(list: List<Int>): Pair<Int, Int> {
        val mutable = list.toMutableList()
        var count = 0

        val seen: MutableMap<List<Int>, Int> = mutableMapOf()
        seen.put(list, 0)
        var added: Int? = null
        while (added == null) {
            val maxindex = mutable.indexOf(mutable.max())
            val toDist = mutable[maxindex]
            mutable[maxindex] = 0
            val seq = sequence(maxindex + 1, list.size).iterator()
            (1..toDist).forEach {
                mutable[seq.next()] += 1
            }
            count++
            added = seen.put(mutable.toList(), count)
        }
        println("$count: $mutable last seen on step $added,  size: ${seen.size} ")
        return count to (count - added)
    }

    private fun sequence(start: Int, max: Int): Sequence<Int> {
        var index = start
        return generateSequence {
            if (index == max) {
                index = 0
            }
            index++
        }
    }
}