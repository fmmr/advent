package no.rodland.advent_2017

import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class Day6Test {
    val data6 = listOf(10, 3, 15, 10, 5, 15, 5, 15, 9, 2, 5, 8, 5, 2, 3, 6)
    val test6 = listOf(0, 2, 7, 0)

    @Nested
    inner class `Part 1` {
        @Test
        fun `6,1,test`() {
            report {
                Day6.partOne(test6) to 5
            }
        }

        @Test
        fun `6,1,live`() {
            report {
                Day6.partOne(data6) to 14029
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `6,2,test`() {
            report {
                Day6.partTwo(test6) to 4
            }
        }

        @Test
        fun `6,2,live`() {
            report {
                Day6.partTwo(data6) to 2765
            }
        }
    }
}