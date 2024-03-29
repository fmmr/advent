package no.rodland.advent_2022

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@Suppress("ClassName")
@DisableSlow
internal class Day08Test {
    val data08 = "2022/input_08.txt".readFile()
    val test08 = listOf(
        "30373",
        "25512",
        "65332",
        "33549",
        "35390",
    )

    val resultTestOne = 21
    val resultOne = 1803

    val resultTestTwo = 8
    val resultTwo = 268912

    @Nested
    inner class Init {
        @Test
        fun `08,1,live,init`() {
            report {
                Day08.partOne(data08) to resultOne
            }
        }

        @Test
        fun `08,2,live,init`() {
            report {
                Day08.partTwo(data08) to resultTwo
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `08,1,test`() {
            report {
                Day08.partOne(test08) to resultTestOne
            }
        }

        @Test
        fun `08,1,live,1`() {
            report {
                Day08.partOne(data08) to resultOne
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `08,2,test`() {
            report {
                Day08.partTwo(test08) to resultTestTwo
            }
        }

        @Test
        fun `08,2,live,1`() {
            report {
                Day08.partTwo(data08) to resultTwo
            }
        }
    }
}
