package no.rodland.advent_2022

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@Suppress("ClassName")
@DisableSlow
internal class Day24Test {
    val data24 = "2022/input_24.txt".readFile()
    val test24 = listOf("1", "2")

    val resultTestOne = 2L
    val resultTestTwo = 2L
    val resultOne = 2L
    val resultTwo = 2L

    @Nested
    inner class Init {
        @Test
        fun `24,1,live,init`() {
            report {
                Day24.partOne(data24) to resultOne
            }
        }

        @Test
        fun `24,2,live,init`() {
            report {
                Day24.partTwo(data24) to resultTwo
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `24,1,test`() {
            report {
                Day24.partOne(test24) to resultTestOne
            }
        }

        @Test
        fun `24,1,live,1`() {
            report {
                Day24.partOne(data24) to resultOne
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `24,2,test`() {
            report {
                Day24.partTwo(test24) to resultTestTwo
            }
        }

        @Test
        fun `24,2,live,1`() {
            report {
                Day24.partTwo(data24) to resultTwo
            }
        }
    }
}
