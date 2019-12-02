package no.rodland.advent_2019

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisableSlow
internal class Day02Test {
    val data02 = "2019/input_02.txt".readFirstLine()
    val test02 = listOf(1, 2)

    @Nested
    inner class `Part 1` {
        @Test
        fun `02,1,test,1`() {
            report {
                Day02.partOne(mutableListOf(1, 0, 0, 0, 99)) to listOf(2, 0, 0, 0, 99)
            }
        }

        @Test
        fun `02,1,test,2`() {
            report {
                Day02.partOne(mutableListOf(2, 3, 0, 3, 99)) to listOf(2, 3, 0, 6, 99)
            }
        }

        @Test
        fun `02,1,test,3`() {
            report {
                Day02.partOne(mutableListOf(2, 4, 4, 5, 99, 0)) to listOf(2, 4, 4, 5, 99, 9801)
            }
        }

        @Test
        fun `02,1,test,4`() {
            report {
                Day02.partOne(mutableListOf(1, 1, 1, 4, 99, 5, 6, 0, 99)) to listOf(30, 1, 1, 4, 2, 5, 6, 0, 99)
            }
        }

        @Test
        fun `02,1,live`() {
            report {
                Day02.partOneMod(data02.toMutableList()) to 5305097
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `02,2,live`() {
            report {
                Day02.partTwo(data02) to 4925
            }
        }
    }
}


