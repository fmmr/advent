package no.rodland.advent_2021

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@Suppress("ClassName")
@DisableSlow
internal class Day03Test {
    val data03 = "2021/input_03.txt".readFile()
    val test03 = listOf(
        "00100",
        "11110",
        "10110",
        "10111",
        "10101",
        "01111",
        "00111",
        "11100",
        "10000",
        "11001",
        "00010",
        "01010",
    )

    @Nested
    inner class Init {
        @Test
        fun `03,1,live,init`() {
            report {
                Day03.partOne(data03) to 1997414
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `03,1,test`() {
            report {
                Day03.partOne(test03) to 198
            }
        }

        @Test
        fun `03,1,live,1`() {
            report {
                Day03.partOne(data03) to 1997414
            }
        }

        @Test
        fun `03,1,live,2`() {
            report {
                Day03.partOne(data03) to 1997414
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `03,2,test`() {
            report {
                Day03.partTwo(test03) to 230
            }
        }

        @Test
        fun `03,2,live,1`() {
            report {
                Day03.partTwo(data03) to 1032597
            }
        }

        @Test
        fun `03,2,live,2`() {
            report {
                Day03.partTwo(data03) to 1032597
            }
        }
    }
}
