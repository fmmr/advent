package no.rodland.advent_2016

@Suppress("UNUSED_PARAMETER")
object Day03 {
    fun partOne(list: List<String>): Int {
        return list.count { line ->
            val nums = line.trim().split(" +".toRegex()).map { it.toInt() }
            val max = nums.maxOrNull()!!
            (nums - max).sum() > max
        }
    }

    fun partTwo(list: List<String>): Int {
        return 2
    }
}
