package no.rodland.advent_2021

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@Suppress("ClassName")
@DisableSlow
internal class Day13Test {
    val data13 = "2021/input_13.txt".readFile()
    val test13 = listOf(
        "1",
        "2",
    )

    @Nested
    inner class Init {
        @Test
        fun `13,1,live,init`() {
            report {
                Day13.partOne(data13) to 2
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `13,1,test`() {
            report {
                Day13.partOne(test13) to 2
            }
        }

        @Test
        fun `13,1,live,1`() {
            report {
                Day13.partOne(data13) to 2
            }
        }

        @Test
        fun `13,1,live,2`() {
            report {
                Day13.partOne(data13) to 2
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `13,2,test`() {
            report {
                Day13.partTwo(test13) to 2
            }
        }

        @Test
        fun `13,2,live,1`() {
            report {
                Day13.partTwo(data13) to 2
            }
        }

        @Test
        fun `13,2,live,2`() {
            report {
                Day13.partTwo(data13) to 2
            }
        }
    }
}
