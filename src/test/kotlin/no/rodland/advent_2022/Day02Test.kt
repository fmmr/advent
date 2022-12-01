package no.rodland.advent_2022

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@Suppress("ClassName")
@DisableSlow
internal class Day02Test {
    val data02 = "2022/input_02.txt".readFile()
    val test02 = listOf("1", "2")

    val resultTestOne = 2L
    val resultOne = 2L

    val resultTestTwo = 2L
    val resultTwo = 2L

    @Nested
    inner class Init {
        @Test
        fun `02,1,live,init`() {
            report {
                Day02.partOne(data02) to resultOne
            }
        }

        @Test
        fun `02,2,live,init`() {
            report {
                Day02.partTwo(data02) to resultTwo
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `02,1,test`() {
            report {
                Day02.partOne(test02) to resultTestOne
            }
        }

        @Test
        fun `02,1,live,1`() {
            report {
                Day02.partOne(data02) to resultOne
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `02,2,test`() {
            report {
                Day02.partTwo(test02) to resultTestTwo
            }
        }

        @Test
        fun `02,2,live,1`() {
            report {
                Day02.partTwo(data02) to resultTwo
            }
        }
    }
}
