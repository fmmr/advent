package no.rodland.advent_2017

import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

internal class Day4Test {
    val data4 = "2017/input_4.txt".readFile()
    val test4 = listOf(2, 3, 0, 3, 10, 11, 12, 1, 1, 0, 1, 99, 2, 1, 1, 2)

    @Nested
    inner class `Part 1` {
        @Test
        fun `4,1,test`() {
            report {
                Day4.partOne(listOf("aa bb cc dd ee")) to 1
            }
        }

        @Test
        fun `4,1,test,2`() {
            report {
                Day4.partOne(listOf("aa bb cc dd aa")) to 0
            }
        }

        @Test
        fun `4,1,test,3`() {
            report {
                Day4.partOne(listOf("aa bb cc dd aaa")) to 1
            }
        }

        @Test
        fun `4,1,live`() {
            report {
                Day4.partOne(data4) to 337
            }
        }

    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `4,2,test`() {
            report {
                Day4.partTwo(listOf("abcde fghij")) to 1
            }
        }

        @Test
        fun `4,2,test,2`() {
            report {
                Day4.partTwo(listOf("abcde xyz ecdab")) to 0
            }
        }

        @Test
        fun `4,2,test,3`() {
            report {
                Day4.partTwo(listOf("a ab abc abd abf abj")) to 1
            }
        }

        @Test
        fun `4,2,test,4`() {
            report {
                Day4.partTwo(listOf("iiii oiii ooii oooi oooo")) to 1
            }
        }

        @Test
        fun `4,2,test,5`() {
            report {
                Day4.partTwo(listOf("oiii ioii iioi iiio")) to 0
            }
        }

        @Test
        fun `4,2,test,6`() {
            report {
                Day4.partTwo(listOf("aa bb cc dd aa")) to 0
            }
        }

        @Test
        fun `4,2,live`() {
            report {
                Day4.partTwo(data4) to 231
            }
        }
    }
}