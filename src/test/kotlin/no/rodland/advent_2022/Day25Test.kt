package no.rodland.advent_2022

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@Suppress("ClassName")
@DisableSlow
internal class Day25Test {
    val data25 = "2022/input_25.txt".readFile()
    val test25 = listOf("1", "2")

    val resultTestOne = 2L
    val resultTestTwo = 2L
    val resultOne = 2L
    val resultTwo = 2L

    @Nested
    inner class Init {
        @Test
        fun `25,1,live,init`() {
            report {
                Day25.partOne(data25) to resultOne
            }
        }

        @Test
        fun `25,2,live,init`() {
            report {
                Day25.partTwo(data25) to resultTwo
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `25,1,test`() {
            report {
                Day25.partOne(test25) to resultTestOne
            }
        }

        @Test
        fun `25,1,live,1`() {
            report {
                Day25.partOne(data25) to resultOne
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `25,2,test`() {
            report {
                Day25.partTwo(test25) to resultTestTwo
            }
        }

        @Test
        fun `25,2,live,1`() {
            report {
                Day25.partTwo(data25) to resultTwo
            }
        }
    }
}
