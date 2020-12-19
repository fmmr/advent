package no.rodland.advent_2020

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@Suppress("ClassName")
@DisableSlow
internal class Day19Test {
    val data19 = "2020/input_19.txt".readFile()
    val test19 = listOf(
        "0: 4 1 5",
        "1: 2 3 | 3 2",
        "2: 4 4 | 5 5",
        "3: 4 5 | 5 4",
        "4: a",
        "5: b",
        "",
        "ababbb",
        "bababa",
        "abbbab",
        "aaabbb",
        "aaaabbb",
    )

    @Nested
    inner class Init {
        @Test
        fun `19,1,live,init`() {
            report {
                Day19.partOne(data19) to 171
            }
        }

        @Test
        fun `19,2,live,init`() {
            report {
                Day19.partTwo(data19) to 369
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `19,1,test`() {
            report {
                Day19.partOne(test19) to 2
            }
        }

        @Test
        fun `19,1,live,1`() {
            report {
                Day19.partOne(data19) to 171
            }
        }

        @Test
        fun `19,1,live,2`() {
            report {
                Day19.partOne(data19) to 171
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `19,2,live,1`() {
            report {
                Day19.partTwo(data19) to 369
            }
        }

        @Test
        fun `19,2,live,2`() {
            report {
                Day19.partTwo(data19) to 369
            }
        }
    }
}
