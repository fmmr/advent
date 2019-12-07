package no.rodland.advent_2019

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisableSlow
internal class Day07Test {
    val data07 = "2019/input_07.txt".readFirstLineInts()

    @Nested
    inner class `Part 1` {
        @Test
        fun `07,1,test,1`() {
            report {
                Day07.partOne(listOf(3, 15, 3, 16, 1002, 16, 10, 16, 1, 16, 15, 15, 4, 15, 99, 0, 0)) to 43210
            }
        }

        @Test
        fun `07,1,test,2`() {
            report {
                Day07.partOne(listOf(3, 23, 3, 24, 1002, 24, 10, 24, 1002, 23, -1, 23, 101, 5, 23, 23, 1, 24, 23, 23, 4, 23, 99, 0, 0)) to 54321
            }
        }

        @Test
        fun `07,1,test,3`() {
            report {
                Day07.partOne(listOf(3, 31, 3, 32, 1002, 32, 10, 32, 1001, 31, -2, 31, 1007, 31, 0, 33, 1002, 33, 7, 33, 1, 33, 31, 31, 1, 32, 31, 31, 4, 31, 99, 0, 0, 0)) to 65210
            }
        }

        @Test
        fun `07,1,live`() {
            report {
                Day07.partOne(data07) to 79723
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `07,2,test`() {
            report {
                Day07.partTwo(listOf(1, 2)) to 2
            }
        }

        @Test
        fun `07,2,live`() {
            report {
                Day07.partTwo(data07) to 2
            }
        }
    }
}


