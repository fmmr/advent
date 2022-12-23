package no.rodland.advent_2022

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@Suppress("ClassName")
@DisableSlow
internal class Day24Test {
    private val data24 = "2022/input_24.txt".readFile()
    private val test24 = listOf("1", "2")

    private val day24 = Day24(data24)
    private val day24Test = Day24(test24)

    private val resultTestOne = 2L
    private val resultTestTwo = 2L
    private val resultOne = 2L
    private val resultTwo = 2L

    @Nested
    inner class Init {
        @Test
        fun `24,1,live,init`() {
            report {
                day24.partOne() to resultOne
            }
        }

        @Test
        fun `24,2,live,init`() {
            report {
                day24.partTwo() to resultTwo
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `24,1,test`() {
            report {
                day24Test.partOne() to resultTestOne
            }
        }

        @Test
        fun `24,1,live,1`() {
            report {
                day24.partOne() to resultOne
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `24,2,test`() {
            report {
                day24Test.partTwo() to resultTestTwo
            }
        }

        @Test
        fun `24,2,live,1`() {
            report {
                day24.partTwo() to resultTwo
            }
        }
    }
}
