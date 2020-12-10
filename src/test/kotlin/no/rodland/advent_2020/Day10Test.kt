package no.rodland.advent_2020

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@Suppress("ClassName")
@DisableSlow
internal class Day10Test {
    val data10 = "2020/input_10.txt".readFileInts()
    val test10 = listOf(16, 10, 15, 5, 1, 11, 7, 19, 6, 12, 4)
    val test10_2 = listOf(28, 33, 18, 42, 31, 14, 46, 20, 48, 47, 24, 23, 49, 45, 19, 38, 39, 11, 1, 32, 25, 35, 8, 17, 7, 9, 4, 2, 34, 10, 3)

    @Nested
    inner class Init {
        @Test
        fun `10,1,live,init`() {
            report {
                Day10.partOne(data10) to 1914
            }
        }

        @Test
        fun `10,2,live,init`() {
            report {
                Day10.partTwo(data10) to 9256148959232
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `10,1,test`() {
            report {
                Day10.partOne(test10) to 35
            }
        }

        @Test
        fun `10,1,test_2`() {
            report {
                Day10.partOne(test10_2) to 220
            }
        }

        @Test
        fun `10,1,live,1`() {
            report {
                Day10.partOne(data10) to 1914
            }
        }

        @Test
        fun `10,1,live,2`() {
            report {
                Day10.partOne(data10) to 1914
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `10,2,test`() {
            report {
                Day10.partTwo(test10) to 8
            }
        }

        @Test
        fun `10,2,test,2`() {
            report {
                Day10.partTwo(test10_2) to 19208
            }
        }

        @Test
        fun `10,2,live,1`() {
            report {
                Day10.partTwo(data10) to 9256148959232
            }
        }

        @Test
        fun `10,2,live,2`() {
            report {
                Day10.partTwo(data10) to 9256148959232
            }
        }
    }
}
