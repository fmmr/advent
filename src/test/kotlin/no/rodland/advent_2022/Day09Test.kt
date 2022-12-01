package no.rodland.advent_2022

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@Suppress("ClassName")
@DisableSlow
internal class Day09Test {
    val data09 = "2022/input_09.txt".readFile()
    val test09 = listOf("1", "2")

    val resultTestOne = 2L
    val resultOne = 2L

    val resultTestTwo = 2L
    val resultTwo = 2L

    @Nested
    inner class Init {
        @Test
        fun `09,1,live,init`() {
            report {
                Day09.partOne(data09) to resultOne
            }
        }

        @Test
        fun `09,2,live,init`() {
            report {
                Day09.partTwo(data09) to resultTwo
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `09,1,test`() {
            report {
                Day09.partOne(test09) to resultTestOne
            }
        }

        @Test
        fun `09,1,live,1`() {
            report {
                Day09.partOne(data09) to resultOne
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `09,2,test`() {
            report {
                Day09.partTwo(test09) to resultTestTwo
            }
        }

        @Test
        fun `09,2,live,1`() {
            report {
                Day09.partTwo(data09) to resultTwo
            }
        }
    }
}
