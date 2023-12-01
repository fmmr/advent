package no.rodland.advent_2023

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@Suppress("ClassName")
@DisableSlow
internal class Day02Test {
    private val data02 = "2023/input_02.txt".readFile()
    private val test02 = listOf("1", "2")

    private val day02 = Day02(data02)
    private val day02Test = Day02(test02)

    private val resultTestOne = 2L
    private val resultTestTwo = 2L
    private val resultOne = 2L
    private val resultTwo = 2L

    @Nested
    inner class Init {
        @Test
        fun `02,1,live,init`() {
            report {
                day02.partOne() to resultOne
            }
        }

        @Test
        fun `02,2,live,init`() {
            report {
                day02.partTwo() to resultTwo
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `02,1,test`() {
            report {
                day02Test.partOne() to resultTestOne
            }
        }

        @Test
        fun `02,1,live,1`() {
            report {
                day02.partOne() to resultOne
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `02,2,test`() {
            report {
                day02Test.partTwo() to resultTestTwo
            }
        }

        @Test
        fun `02,2,live,1`() {
            report {
                day02.partTwo() to resultTwo
            }
        }
    }
}
