package no.rodland.advent_2019

import kotlinx.coroutines.ExperimentalCoroutinesApi
import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Disabled
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

    @ExperimentalCoroutinesApi
    @Nested
    @Disabled
    inner class `Part 2` {
        @Test
        fun `07,2,test,1`() {
            report {
                Day07.partTwo(listOf(3, 26, 1001, 26, -4, 26, 3, 27, 1002, 27, 2, 27, 1, 27, 26, 27,
                        4, 27, 1001, 28, -1, 28, 1005, 28, 6, 99, 0, 0, 5)) to 139629729
            }
        }

        @Test
        fun `07,2,test,2`() {
            report {
                Day07.partTwo(listOf(3, 52, 1001, 52, -5, 52, 3, 53, 1, 52, 56, 54, 1007, 54, 5, 55, 1005, 55, 26, 1001, 54,
                        -5, 54, 1105, 1, 12, 1, 53, 54, 53, 1008, 54, 0, 55, 1001, 55, 1, 55, 2, 53, 55, 53, 4,
                        53, 1001, 56, -1, 56, 1005, 56, 6, 99, 0, 0, 0, 0, 10)) to 18216
            }
        }

        @Test
        fun `07,2,live`() {
            report {
                Day07.partTwo(data07) to 70602018
            }
        }
    }
}


