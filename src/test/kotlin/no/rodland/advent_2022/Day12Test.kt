package no.rodland.advent_2022

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@Suppress("ClassName")
@DisableSlow
internal class Day12Test {
    val data12 = "2022/input_12.txt".readFile()
    val test12 = listOf("1", "2")

    val resultTestOne = 2L
    val resultOne = 2L

    val resultTestTwo = 2L
    val resultTwo = 2L

    @Nested
    inner class Init {
        @Test
        fun `12,1,live,init`() {
            report {
                Day12.partOne(data12) to resultOne
            }
        }

        @Test
        fun `12,2,live,init`() {
            report {
                Day12.partTwo(data12) to resultTwo
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `12,1,test`() {
            report {
                Day12.partOne(test12) to resultTestOne
            }
        }

        @Test
        fun `12,1,live,1`() {
            report {
                Day12.partOne(data12) to resultOne
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `12,2,test`() {
            report {
                Day12.partTwo(test12) to resultTestTwo
            }
        }

        @Test
        fun `12,2,live,1`() {
            report {
                Day12.partTwo(data12) to resultTwo
            }
        }
    }
}
