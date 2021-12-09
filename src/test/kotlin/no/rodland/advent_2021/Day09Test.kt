package no.rodland.advent_2021

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@Suppress("ClassName")
@DisableSlow
internal class Day09Test {
    val data09 = "2021/input_09.txt".readFile()
    val test09 = listOf(
        "2199943210",
        "3987894921",
        "9856789892",
        "8767896789",
        "9899965678",
    )

    @Nested
    inner class Init {
        @Test
        fun `09,1,live,init`() {
            report {
                Day09.partOne(data09) to 518
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `09,1,test`() {
            report {
                Day09.partOne(test09) to 15
            }
        }

        @Test
        fun `09,1,live,1`() {
            report {
                Day09.partOne(data09) to 518
            }
        }

        @Test
        fun `09,1,live,2`() {
            report {
                Day09.partOne(data09) to 518
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `09,2,test`() {
            report {
                Day09.partTwo(test09) to 1134
            }
        }

        @Test
        fun `09,2,live,1`() {
            report {
                Day09.partTwo(data09) to 949905
            }
        }

        @Test
        fun `09,2,live,2`() {
            report {
                Day09.partTwo(data09) to 949905
            }
        }
    }
}
