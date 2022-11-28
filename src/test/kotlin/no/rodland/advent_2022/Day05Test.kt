package no.rodland.advent_2022

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@Suppress("ClassName")
@DisableSlow
internal class Day05Test {
    val data05 = "2022/input_05.txt".readFile()
    val test05 = listOf("1", "2")

    val resultTestOne = 2L
    val resultTestTwo = 2L
    val resultOne = 2L
    val resultTwo = 2L

    @Nested
    inner class Init {
        @Test
        fun `05,1,live,init`() {
            report {
                Day05.partOne(data05) to resultOne
            }
        }

        @Test
        fun `05,2,live,init`() {
            report {
                Day05.partTwo(data05) to resultTwo
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `05,1,test`() {
            report {
                Day05.partOne(test05) to resultTestOne
            }
        }

        @Test
        fun `05,1,live,1`() {
            report {
                Day05.partOne(data05) to resultOne
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `05,2,test`() {
            report {
                Day05.partTwo(test05) to resultTestTwo
            }
        }

        @Test
        fun `05,2,live,1`() {
            report {
                Day05.partTwo(data05) to resultTwo
            }
        }
    }
}
