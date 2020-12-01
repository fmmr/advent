package no.rodland.advent_2020

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisableSlow
internal class Day01Test {
    val data01 = "2020/input_01.txt".readFileInts()
    val testData = listOf(
        1721,
        979,
        366,
        299,
        675,
        1456
    )

    @Nested
    inner class `Part 1` {
        @Test
        fun `01,1,test,1`() {
            report {
                Day01.partOne(testData) to 514579
            }
        }

        @Test
        fun `01,1,live`() {
            report {
                Day01.partOne(data01) to 1018944
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `01,2,test,1`() {
            report {
                Day01.partTwo(testData) to 241861950
            }
        }

        @Test
        fun `01,2,live`() {
            report {
                Day01.partTwo(data01) to 8446464
            }
        }
    }
}
