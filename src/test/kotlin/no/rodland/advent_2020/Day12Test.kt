package no.rodland.advent_2020

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@Suppress("ClassName")
@DisableSlow
internal class Day12Test {
    val data12 = "2020/input_12.txt".readFile()
    val test12 = listOf(
        "F10",
        "N3",
        "F7",
        "R90",
        "F11",
    )

    @Nested
    inner class Init {
        @Test
        fun `12,1,live,init`() {
            report {
                Day12.partOne(data12) to 1496
            }
        }

        @Test
        fun `12,2,live,init`() {
            report {
                Day12.partTwo(data12) to 63843
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `12,1,test`() {
            report {
                Day12.partOne(test12) to 25
            }
        }

        @Test
        fun `12,1,live,1`() {
            report {
                Day12.partOne(data12) to 1496
            }
        }

        @Test
        fun `12,1,live,2`() {
            report {
                Day12.partOne(data12) to 1496
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `12,2,test`() {
            report {
                Day12.partTwo(test12) to 286
            }
        }

        @Test
        fun `12,2,live,1`() {
            report {
                Day12.partTwo(data12) to 63843
            }
        }

        @Test
        fun `12,2,live,2`() {
            report {
                Day12.partTwo(data12) to 63843
            }
        }
    }
}
