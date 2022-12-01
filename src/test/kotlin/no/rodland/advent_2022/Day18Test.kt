package no.rodland.advent_2022

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@Suppress("ClassName")
@DisableSlow
internal class Day18Test {
    val data18 = "2022/input_18.txt".readFile()
    val test18 = listOf("1", "2")

    val resultTestOne = 2L
    val resultOne = 2L

    val resultTestTwo = 2L
    val resultTwo = 2L

    @Nested
    inner class Init {
        @Test
        fun `18,1,live,init`() {
            report {
                Day18.partOne(data18) to resultOne
            }
        }

        @Test
        fun `18,2,live,init`() {
            report {
                Day18.partTwo(data18) to resultTwo
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `18,1,test`() {
            report {
                Day18.partOne(test18) to resultTestOne
            }
        }

        @Test
        fun `18,1,live,1`() {
            report {
                Day18.partOne(data18) to resultOne
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `18,2,test`() {
            report {
                Day18.partTwo(test18) to resultTestTwo
            }
        }

        @Test
        fun `18,2,live,1`() {
            report {
                Day18.partTwo(data18) to resultTwo
            }
        }
    }
}
