package no.rodland.advent_2017

import no.rodland.advent.DisableSlow
import no.rodland.advent.Slow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@DisableSlow
internal class Day13Test {
    val data13 = "2017/input_13.txt".readFile()
    val test13 = listOf(
            "0: 3",
            "1: 2",
            "4: 4",
            "6: 4"
    )

    @Nested
    inner class `Part 1` {
        @Test
        fun `13,1,test`() {
            report {
                Day13.partOne(test13) to 24
            }
        }

        @Test
        fun `13,1,live`() {
            report {
                Day13.partOne(data13) to 1504
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `13,2,test`() {
            report {
                Day13.partTwo(test13) to 10
            }
        }

        @Test
        @Slow(14000)
        fun `13,2,live`() {
            report {
                Day13.partTwo(data13) to 3823370
            }
        }
    }
}


