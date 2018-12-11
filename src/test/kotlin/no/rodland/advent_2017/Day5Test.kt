package no.rodland.advent_2017

import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

internal class Day5Test {
    val data5 = "2017/input_5.txt".readFile()
    val test5 = listOf(0, 3, 0, 1, -3)

    @Nested
    inner class `Part 1` {
        @Test
        fun `5,1,test`() {
            report {
                Day5.partOne(test5) to 2
            }
        }
    }

    @Nested
    inner class `Part 2` {
    }
}