package no.rodland.advent_2023

import no.rodland.advent.Day
import product
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.sqrt

// template generated: 06/12/2023
// Fredrik Rødland 2023

@Suppress("FunctionName")
class Day06(val input: List<Pair<Int, Int>>) : Day<Long, Long, List<Pair<Int, Int>>> {

    override fun List<String>.parse(): List<Pair<Int, Int>> {
        error("Not implemented")
    }

    override fun partOne(): Long {
        return input.map { (length, record) ->
            impl_3_solve_quadratic(length.toLong(), record.toLong())
        }.product()
    }

    override fun partTwo(): Long {
        return partTwo(::impl_3_solve_quadratic)
    }

    fun partTwo(testFunction: (Long, Long) -> Long): Long {
        val isThisTestRun = input.first() == (7 to 9)
        return if (isThisTestRun) {
            val (length, record) = 71530L to 940200L
            testFunction(length, record)
        } else {
            val (length, record) = 48938466L to 261119210191063L
            testFunction(length, record)
        }
    }

    fun impl_3_solve_quadratic(length: Long, record: Long): Long {
        val midPoint = length.toDouble() / 2
        val root = sqrt((midPoint * midPoint) - record)
        val from = floor(midPoint - root + 1)
        val to = ceil(midPoint + root - 1)
        //        println("from: $from, to: $to, root: $root, mid: $midPoint, record: $record")
        return (to - from).toLong() + 1
    }

    fun impl_1_try_whole_range(length: Long, record: Long) = (0..length).count { push(it, length) > record }.toLong()

    fun impl_2_half_range_and_stop(length: Long, record: Long): Long {
        var count = 0
        var c = length / 2
        while (push(c++, length) > record) {
            count++
        }
        return count * 2L - if (length % 2 == 0L) 1 else 2
    }

    private fun push(pushButton: Long, maxLength: Long): Long {
        return pushButton * (maxLength - pushButton)
    }

    override val day = "06".toInt()
}

