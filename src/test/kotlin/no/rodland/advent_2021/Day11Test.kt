package no.rodland.advent_2021

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@Suppress("ClassName")
@DisableSlow
internal class Day11Test {
    val data11 = "2021/input_11.txt".readFile()
    val test11 = listOf(
        "1",
        "2",
    )

    @Nested
    inner class Init {
        @Test
        fun `11,1,live,init`() {
            report {
                Day11.partOne(data11) to 2
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `11,1,test`() {
            report {
                Day11.partOne(test11) to 2
            }
        }

        @Test
        fun `11,1,live,1`() {
            report {
                Day11.partOne(data11) to 2
            }
        }

        @Test
        fun `11,1,live,2`() {
            report {
                Day11.partOne(data11) to 2
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `11,2,test`() {
            report {
                Day11.partTwo(test11) to 2
            }
        }

        @Test
        fun `11,2,live,1`() {
            report {
                Day11.partTwo(data11) to 2
            }
        }

        @Test
        fun `11,2,live,2`() {
            report {
                Day11.partTwo(data11) to 2
            }
        }
    }
}
