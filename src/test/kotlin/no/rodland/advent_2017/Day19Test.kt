package no.rodland.advent_2017

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@Suppress("ClassName")
@DisableSlow
internal class Day19Test {
    val data19 = "2017/input_19.txt".readFile()
    val test19 = listOf(
            "     |          ",
            "     |  +--+    ",
            "     A  |  C    ",
            " F---|----E|--+ ",
            "     |  |  |  D ",
            "     +B-+  +--+ ",
    )

    @Nested
    inner class Init {
        @Test
        fun `19,1,live,init`() {
            report {
                Day19.partOne(data19) to "PVBSCMEQHY"
            }
        }

        @Test
        fun `19,2,live,init`() {
            report {
                Day19.partTwo(data19) to 17736
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `19,1,test`() {
            report {
                Day19.partOne(test19) to "ABCDEF"
            }
        }

        @Test
        fun `19,1,live,1`() {
            report {
                Day19.partOne(data19) to "PVBSCMEQHY"
            }
        }

        @Test
        fun `19,1,live,2`() {
            report {
                Day19.partOne(data19) to "PVBSCMEQHY"
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `19,2,test`() {
            report {
                Day19.partTwo(test19) to 38
            }
        }

        @Test
        fun `19,2,live,1`() {
            report {
                Day19.partTwo(data19) to 17736
            }
        }

        @Test
        fun `19,2,live,2`() {
            report {
                Day19.partTwo(data19) to 17736
            }
        }
    }
}
