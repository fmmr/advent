package no.rodland.advent_2017

import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

internal class Day2Test {
    private val data2 = "2017/input_2.txt".readFile()
    private val test1 = listOf("5	1 9	5", "7	5	3", "2	4	6	8")
    private val test2 = listOf("5 9 2 8", "9 4 7 3", "3 8 6 5")

    @Nested
    inner class `Part 1` {
        @Test
        fun `2,1,test`() {
            report {
                Day2.partOne(test1) to 18
            }
        }

        @Test
        fun `2,1,live`() {
            report {
                Day2.partOne(data2) to 47623
            }
        }

    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `2,2,test`() {
            report {
                Day2.partTwo(test2) to 9
            }
        }

        @Test
        fun `2,2,live`() {
            report {
                Day2.partTwo(data2) to 312
            }
        }
    }
}