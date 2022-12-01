package no.rodland.advent_2022

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@Suppress("ClassName")
@DisableSlow
internal class Day16Test {
    val data16 = "2022/input_16.txt".readFile()
    val test16 = listOf("1", "2")

    val resultTestOne = 2L
    val resultOne = 2L

    val resultTestTwo = 2L
    val resultTwo = 2L

    @Nested
    inner class Init {
        @Test
        fun `16,1,live,init`() {
            report {
                Day16.partOne(data16) to resultOne
            }
        }

        @Test
        fun `16,2,live,init`() {
            report {
                Day16.partTwo(data16) to resultTwo
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `16,1,test`() {
            report {
                Day16.partOne(test16) to resultTestOne
            }
        }

        @Test
        fun `16,1,live,1`() {
            report {
                Day16.partOne(data16) to resultOne
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `16,2,test`() {
            report {
                Day16.partTwo(test16) to resultTestTwo
            }
        }

        @Test
        fun `16,2,live,1`() {
            report {
                Day16.partTwo(data16) to resultTwo
            }
        }
    }
}
