package no.rodland.advent_2022

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@Suppress("ClassName")
@DisableSlow
internal class Day06Test {
    val data06 = "2022/input_06.txt".readFile()
    val test06 = listOf("1", "2")

    val resultTestOne = 2L
    val resultOne = 2L

    val resultTestTwo = 2L
    val resultTwo = 2L

    @Nested
    inner class Init {
        @Test
        fun `06,1,live,init`() {
            report {
                Day06.partOne(data06) to resultOne
            }
        }

        @Test
        fun `06,2,live,init`() {
            report {
                Day06.partTwo(data06) to resultTwo
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `06,1,test`() {
            report {
                Day06.partOne(test06) to resultTestOne
            }
        }

        @Test
        fun `06,1,live,1`() {
            report {
                Day06.partOne(data06) to resultOne
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `06,2,test`() {
            report {
                Day06.partTwo(test06) to resultTestTwo
            }
        }

        @Test
        fun `06,2,live,1`() {
            report {
                Day06.partTwo(data06) to resultTwo
            }
        }
    }
}
