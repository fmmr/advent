package no.rodland.advent_2022

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@Suppress("ClassName")
@DisableSlow
internal class Day14Test {
    val data14 = "2022/input_14.txt".readFile()
    val test14 = listOf(
        "1"
    )

    val resultTestOne = 2L
    val resultOne = 2L

    val resultTestTwo = 2L
    val resultTwo = 2L

    @Nested
    inner class Init {
        @Test
        fun `14,1,live,init`() {
            report {
                Day14.partOne(data14) to resultOne
            }
        }

        @Test
        fun `14,2,live,init`() {
            report {
                Day14.partTwo(data14) to resultTwo
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `14,1,test`() {
            report {
                Day14.partOne(test14) to resultTestOne
            }
        }

        @Test
        fun `14,1,live,1`() {
            report {
                Day14.partOne(data14) to resultOne
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `14,2,test`() {
            report {
                Day14.partTwo(test14) to resultTestTwo
            }
        }

        @Test
        fun `14,2,live,1`() {
            report {
                Day14.partTwo(data14) to resultTwo
            }
        }
    }
}
