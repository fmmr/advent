package no.rodland.advent_2022

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@Suppress("ClassName")
@DisableSlow
internal class Day07Test {
    val data07 = "2022/input_07.txt".readFile()
    val test07 = listOf("1", "2")

    val resultTestOne = 2L
    val resultOne = 2L

    val resultTestTwo = 2L
    val resultTwo = 2L

    @Nested
    inner class Init {
        @Test
        fun `07,1,live,init`() {
            report {
                Day07.partOne(data07) to resultOne
            }
        }

        @Test
        fun `07,2,live,init`() {
            report {
                Day07.partTwo(data07) to resultTwo
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `07,1,test`() {
            report {
                Day07.partOne(test07) to resultTestOne
            }
        }

        @Test
        fun `07,1,live,1`() {
            report {
                Day07.partOne(data07) to resultOne
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `07,2,test`() {
            report {
                Day07.partTwo(test07) to resultTestTwo
            }
        }

        @Test
        fun `07,2,live,1`() {
            report {
                Day07.partTwo(data07) to resultTwo
            }
        }
    }
}
