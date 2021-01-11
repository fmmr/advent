package no.rodland.advent_2015

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@Suppress("ClassName")
@DisableSlow
internal class Day23Test {
    val data23 = "2015/input_23.txt".readFile()
    val test23 = listOf(
            "inc a",
            "jio a, +2",
            "tpl a",
            "inc a",
    )

    @Nested
    inner class Init {
        @Test
        fun `23,1,live,init`() {
            report {
                Day23.partOne(data23, "b") to 255
            }
        }

        @Test
        fun `23,2,live,init`() {
            report {
                Day23.partTwo(data23) to 334
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `23,1,test`() {
            report {
                Day23.partOne(test23, "a") to 2
            }
        }

        @Test
        fun `23,1,live,1`() {
            report {
                Day23.partOne(data23, "b") to 255
            }
        }

        @Test
        fun `23,1,live,2`() {
            report {
                Day23.partOne(data23, "b") to 255
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `23,2,live,1`() {
            report {
                Day23.partTwo(data23) to 334
            }
        }

        @Test
        fun `23,2,live,2`() {
            report {
                Day23.partTwo(data23) to 334
            }
        }
    }
}
