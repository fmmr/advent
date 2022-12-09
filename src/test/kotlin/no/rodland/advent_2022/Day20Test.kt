package no.rodland.advent_2022

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@Suppress("ClassName")
@DisableSlow
internal class Day20Test {
    val data20 = "2022/input_20.txt".readFile()
    val test20 = listOf(
        "1"
    )

    val resultTestOne = 2L
    val resultOne = 2L

    val resultTestTwo = 2L
    val resultTwo = 2L

    @Nested
    inner class Init {
        @Test
        fun `20,1,live,init`() {
            report {
                Day20.partOne(data20) to resultOne
            }
        }

        @Test
        fun `20,2,live,init`() {
            report {
                Day20.partTwo(data20) to resultTwo
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `20,1,test`() {
            report {
                Day20.partOne(test20) to resultTestOne
            }
        }

        @Test
        fun `20,1,live,1`() {
            report {
                Day20.partOne(data20) to resultOne
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `20,2,test`() {
            report {
                Day20.partTwo(test20) to resultTestTwo
            }
        }

        @Test
        fun `20,2,live,1`() {
            report {
                Day20.partTwo(data20) to resultTwo
            }
        }
    }
}
