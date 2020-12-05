package no.rodland.advent_2020

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@Suppress("ClassName")
@DisableSlow
internal class Day05Test {
    val data05 = "2020/input_05.txt".readFile()

    @Nested
    inner class `Part 1` {
        @Test
        fun `05,1,test,1`() {
            report {
                Day05.partOne(listOf("BFFFBBFRRR")) to 567
            }
        }

        @Test
        fun `05,1,test,2`() {
            report {
                Day05.partOne(listOf("FFFBBBFRRR")) to 119
            }
        }

        @Test
        fun `05,1,test,3`() {
            report {
                Day05.partOne(listOf("BBFFBBFRLL")) to 820
            }
        }

        @Test
        fun `05,1,test,4`() {
            report {
                Day05.partOne(listOf("FBFBBFFRLR")) to 357
            }
        }

        @Test
        fun `05,1,test,5`() {
            report {
                Day05.partOne(listOf("FFFBBBFRRR", "BBFFBBFRLL", "BFFFBBFRRR")) to 820
            }
        }

        @Test
        fun `05,1,live`() {
            report {
                Day05.partOne(data05) to 987
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `05,2,live`() {
            report {
                Day05.partTwo(data05) to 603
            }
        }
    }
}


