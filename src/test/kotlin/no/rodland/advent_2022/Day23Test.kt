package no.rodland.advent_2022

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@Suppress("ClassName")
@DisableSlow
internal class Day23Test {
    val data23 = "2022/input_23.txt".readFile()
    val test23 = listOf("1", "2")

    val resultTestOne = 2L
    val resultOne = 2L

    val resultTestTwo = 2L
    val resultTwo = 2L

    @Nested
    inner class Init {
        @Test
        fun `23,1,live,init`() {
            report {
                Day23.partOne(data23) to resultOne
            }
        }

        @Test
        fun `23,2,live,init`() {
            report {
                Day23.partTwo(data23) to resultTwo
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `23,1,test`() {
            report {
                Day23.partOne(test23) to resultTestOne
            }
        }

        @Test
        fun `23,1,live,1`() {
            report {
                Day23.partOne(data23) to resultOne
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `23,2,test`() {
            report {
                Day23.partTwo(test23) to resultTestTwo
            }
        }

        @Test
        fun `23,2,live,1`() {
            report {
                Day23.partTwo(data23) to resultTwo
            }
        }
    }
}
