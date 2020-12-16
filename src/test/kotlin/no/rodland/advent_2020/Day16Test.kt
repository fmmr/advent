package no.rodland.advent_2020

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@Suppress("ClassName")
@DisableSlow
internal class Day16Test {
    val data16 = "2020/input_16.txt".readFileAsOneString()
    val test16 = listOf(
        "class: 1-3 or 5-7",
        "row: 6-11 or 33-44",
        "seat: 13-40 or 45-50",
        "",
        "your ticket:",
        "7,1,14",
        "",
        "nearby tickets:",
        "7,3,47",
        "40,4,50",
        "55,2,20",
        "38,6,12",
    ).joinToString("\n")
    val test16_2 = listOf(
        "class: 0-1 or 4-19",
        "row: 0-5 or 8-19",
        "seat: 0-13 or 16-19",
        "",
        "your ticket:",
        "11,12,13",
        "",
        "nearby tickets:",
        "3,9,18",
        "15,1,5",
        "5,14,9",
    ).joinToString("\n")

    @Nested
    inner class Init {
        @Test
        fun `16,1,live,init`() {
            report {
                Day16.partOne(data16) to 26988
            }
        }

        @Test
        fun `16,2,live,init`() {
            report {
                Day16.partTwo(data16) to 2
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `16,1,test`() {
            report {
                Day16.partOne(test16) to 71
            }
        }

        @Test
        fun `16,1,live,1`() {
            report {
                Day16.partOne(data16) to 26988
            }
        }

        @Test
        fun `16,1,live,2`() {
            report {
                Day16.partOne(data16) to 26988
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `16,2,test`() {
            report {
                Day16.partTwo(test16_2) to 2
            }
        }

        @Test
        fun `16,2,live,1`() {
            report {
                Day16.partTwo(data16) to 2
            }
        }

        @Test
        fun `16,2,live,2`() {
            report {
                Day16.partTwo(data16) to 2
            }
        }
    }
}
