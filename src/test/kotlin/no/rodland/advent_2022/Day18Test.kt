package no.rodland.advent_2022

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@Suppress("ClassName", "PropertyName")
@DisableSlow
internal class Day18Test {
    val data18 = "2022/input_18.txt".readFile()
    val test18_1 = listOf(
        "1,1,1",
        "2,1,1"
    )
    val test18_2 = listOf(
        "2,2,2",
        "1,2,2",
        "3,2,2",
        "2,1,2",
        "2,3,2",
        "2,2,1",
        "2,2,3",
        "2,2,4",
        "2,2,6",
        "1,2,5",
        "3,2,5",
        "2,1,5",
        "2,3,5",
    )

    val resultTestOne_1 = 10
    val resultTestOne_2 = 64
    val resultOne = 4332

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
        fun `18,1,test,1`() {
            report {
                Day18.partOne(test18_1) to resultTestOne_1
            }
        }

        @Test
        fun `18,1,test,2`() {
            report {
                Day18.partOne(test18_2) to resultTestOne_2
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
                Day18.partTwo(test18_1) to resultTestTwo
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
