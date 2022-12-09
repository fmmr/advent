package no.rodland.advent_2022

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@Suppress("ClassName")
@DisableSlow
internal class Day13Test {
    val data13 = "2022/input_13.txt".readFile()
    val test13 = listOf(
        "1"
    )

    val resultTestOne = 2L
    val resultOne = 2L

    val resultTestTwo = 2L
    val resultTwo = 2L

    @Nested
    inner class Init {
        @Test
        fun `13,1,live,init`() {
            report {
                Day13.partOne(data13) to resultOne
            }
        }

        @Test
        fun `13,2,live,init`() {
            report {
                Day13.partTwo(data13) to resultTwo
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `13,1,test`() {
            report {
                Day13.partOne(test13) to resultTestOne
            }
        }

        @Test
        fun `13,1,live,1`() {
            report {
                Day13.partOne(data13) to resultOne
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `13,2,test`() {
            report {
                Day13.partTwo(test13) to resultTestTwo
            }
        }

        @Test
        fun `13,2,live,1`() {
            report {
                Day13.partTwo(data13) to resultTwo
            }
        }
    }
}
