package no.rodland.advent_2019

import no.rodland.advent.DisableSlow
import no.rodland.advent.Slow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisableSlow
internal class Day12Test {
    val data12 = listOf(
            listOf(14, 15, -2),
            listOf(17, -3, 4),
            listOf(6, 12, -13),
            listOf(-2, 10, -8)
    )
    val test12 = listOf(
            listOf(-1, 0, 2),
            listOf(2, -10, -7),
            listOf(4, -8, 8),
            listOf(3, 5, -1)
    )

    @Nested
    inner class `Part 1` {
        @Test
        fun `12,1,test`() {
            report {
                Day12.partOne(test12, 10) to 179
            }
        }

        @Test
        fun `12,1,live`() {
            report {
                Day12.partOne(data12, 1000) to 10189
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `12,2,test,simple`() {
            report {
                Day12.partTwoSimple(test12) to 2772
            }
        }

        @Test
        fun `12,2,test,real`() {
            report {
                Day12.partTwo(test12) to 2772
            }
        }

        @Test
        @Slow(232323232)
        fun `12,2,live`() {
            report {
                Day12.partTwo(data12) to 2
            }
        }
    }
}


