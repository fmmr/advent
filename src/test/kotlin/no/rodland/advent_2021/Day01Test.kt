package no.rodland.advent_2021

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFileAsInt

@Suppress("ClassName")
@DisableSlow
internal class Day01Test {
    val data01 = "2021/input_01.txt".readFileAsInt()
    val test01 = listOf(
        199,
        200,
        208,
        210,
        200,
        207,
        240,
        269,
        260,
        263,
    )

    @Nested
    inner class Init {
        @Test
        fun `01,1,live,init`() {
            report {
                Day01.partOne(data01) to 1655
            }
        }

        @Test
        fun `01,2,live,init`() {
            report {
                Day01.partTwo(data01) to 1683
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `01,1,test`() {
            report {
                Day01.partOne(test01) to 7
            }
        }

        @Test
        fun `01,1,live,1`() {
            report {
                Day01.partOne(data01) to 1655
            }
        }

        @Test
        fun `01,1,live,2`() {
            report {
                Day01.partOne(data01) to 1655
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `01,2,test`() {
            report {
                Day01.partTwo(test01) to 5
            }
        }

        @Test
        fun `01,2,live,1`() {
            report {
                Day01.partTwo(data01) to 1683
            }
        }

        @Test
        fun `01,2,live,2`() {
            report {
                Day01.partTwo(data01) to 1683
            }
        }
    }
}
