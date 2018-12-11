package no.rodland.advent_2017

import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFileAsInt

internal class Day5Test {
    val data5 = "2017/input_5.txt".readFileAsInt()
    val test5 = listOf(0, 3, 0, 1, -3)

    @Nested
    inner class `Part 1` {
        @Test
        fun `5,1,test`() {
            report {
                Day5.partOne(test5.toMutableList()) to 5
            }
        }

        @Test
        fun `5,1,live`() {
            report {
                Day5.partOne(data5.toMutableList()) to 318883
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `5,2,test`() {
            report {
                Day5.partTwo(test5.toMutableList()) to 10
            }
        }

        @Test
        fun `5,2,live`() {
            report {
                Day5.partTwo(data5.toMutableList()) to 23948711
            }
        }
    }
}