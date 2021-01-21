package no.rodland.advent_2016

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@Suppress("ClassName")
@DisableSlow
internal class Day23Test {
    val data23 = "2016/input_23.txt".readFile()
    val test23 = listOf(
            "cpy 2 a",
            "tgl a",
            "tgl a",
            "tgl a",
            "cpy 1 a",
            "dec a",
            "dec a",
    )

    @Nested
    inner class Init {
        @Test
        fun `23,1,live,init`() {
            report {
                Day23.partOne(data23, 7) to 10365
            }
        }

        @Test
        fun `23,2,live,init`() {
            report {
                Day23.partTwo(data23) to 2
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `23,1,test`() {
            report {
                Day23.partOne(test23, 0) to 3
            }
        }

        @Test
        fun `23,1,live,1`() {
            report {
                Day23.partOne(data23, 7) to 10365
            }
        }

        @Test
        fun `23,1,live,2`() {
            report {
                Day23.partOne(data23, 7) to 10365
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `23,2,test`() {
            report {
                Day23.partTwo(test23) to 2
            }
        }

        @Test
        fun `23,2,live,1`() {
            report {
                Day23.partTwo(data23) to 2
            }
        }

        @Test
        fun `23,2,live,2`() {
            report {
                Day23.partTwo(data23) to 2
            }
        }
    }
}