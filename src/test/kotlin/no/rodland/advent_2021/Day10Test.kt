package no.rodland.advent_2021

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@Suppress("ClassName")
@DisableSlow
internal class Day10Test {
    val data10 = "2021/input_10.txt".readFile()
    val test10 = listOf(
        "1",
        "2",
    )

    @Nested
    inner class Init {
        @Test
        fun `10,1,live,init`() {
            report {
                Day10.partOne(data10) to 2
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `10,1,test`() {
            report {
                Day10.partOne(test10) to 2
            }
        }

        @Test
        fun `10,1,live,1`() {
            report {
                Day10.partOne(data10) to 2
            }
        }

        @Test
        fun `10,1,live,2`() {
            report {
                Day10.partOne(data10) to 2
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `10,2,test`() {
            report {
                Day10.partTwo(test10) to 2
            }
        }

        @Test
        fun `10,2,live,1`() {
            report {
                Day10.partTwo(data10) to 2
            }
        }

        @Test
        fun `10,2,live,2`() {
            report {
                Day10.partTwo(data10) to 2
            }
        }
    }
}
