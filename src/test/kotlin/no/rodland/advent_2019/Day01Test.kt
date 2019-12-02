package no.rodland.advent_2019

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisableSlow
internal class Day01Test {
    val data01 = "2019/input_01.txt".readFile()

    @Nested
    inner class `Part 1` {
        @Test
        fun `01,1,test,1`() {
            report {
                Day01.partOne(12) to 2
            }
        }

        @Test
        fun `01,1,test,2`() {
            report {
                Day01.partOne(14) to 2
            }
        }

        @Test
        fun `01,1,test,3`() {
            report {
                Day01.partOne(1969) to 654
            }
        }

        @Test
        fun `01,1,test,4`() {
            report {
                Day01.partOne(100756) to 33583
            }
        }

        @Test
        fun `01,1,live`() {
            report {
                data01.map { Day01.partOne(it.toInt()) }.sum() to 3398090
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `01,2,test,1`() {
            report {
                Day01.partTwo(14) to 2
            }
        }

        @Test
        fun `01,2,test,2`() {
            report {
                Day01.partTwo(1969) to 966
            }
        }

        @Test
        fun `01,2,test,3`() {
            report {
                Day01.partTwo(100756) to 50346
            }
        }

        @Test
        fun `01,2,live`() {
            report {
                data01.map { Day01.partTwo(it.toInt()) }.sum() to 5094261
            }
        }
    }
}


