package no.rodland.advent_2022

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@Suppress("ClassName")
@DisableSlow
internal class Day10Test {
    val data10 = "2022/input_10.txt".readFile()
    val test10 = listOf("1", "2")

    val resultTestOne = 2L
    val resultOne = 2L
    val resultTestTwo = 2L
    val resultTwo = 2L

    @Nested
    inner class Init {
        @Test
        fun `10,1,live,init`() {
            report {
                Day10.partOne(data10) to resultOne
            }
        }

        @Test
        fun `10,2,live,init`() {
            report {
                Day10.partTwo(data10) to resultTwo
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `10,1,test`() {
            report {
                Day10.partOne(test10) to resultTestOne
            }
        }

        @Test
        fun `10,1,live,1`() {
            report {
                Day10.partOne(data10) to resultOne
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `10,2,test`() {
            report {
                Day10.partTwo(test10) to resultTestTwo
            }
        }

        @Test
        fun `10,2,live,1`() {
            report {
                Day10.partTwo(data10) to resultTwo
            }
        }
    }
}
