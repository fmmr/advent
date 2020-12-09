package no.rodland.advent_2020

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@Suppress("ClassName")
@DisableSlow
internal class Day09Test {
    val data09 = "2020/input_09.txt".readFileLong()
    val test09 = listOf(
        "35",
        "20",
        "15",
        "25",
        "47",
        "40",
        "62",
        "55",
        "65",
        "95",
        "102",
        "117",
        "150",
        "182",
        "127",
        "219",
        "299",
        "277",
        "309",
        "576",
    ).map { it.toLong() }

    @Nested
    inner class Init {
        @Test
        fun `09,1,live,init`() {
            report {
                Day09.partOne(data09, 25) to 23278925
            }
        }

        @Test
        fun `09,2,live,init`() {
            report {
                Day09.partTwo(data09, 23278925, 514) to 4011064
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `09,1,test`() {
            report {
                Day09.partOne(test09, 5) to 127
            }
        }

        @Test
        fun `09,1,live,1`() {
            report {
                Day09.partOne(data09, 25) to 23278925
            }
        }

        @Test
        fun `09,1,live,2`() {
            report {
                Day09.partOne(data09, 25) to 23278925
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `09,2,test`() {
            report {
                Day09.partTwo(test09, 127, 14) to 62
            }
        }

        @Test
        fun `09,2,live,1`() {
            report {
                Day09.partTwo(data09, 23278925, 514) to 4011064
            }
        }

        @Test
        fun `09,2,live,2`() {
            report {
                Day09.partTwo(data09, 23278925, 514) to 4011064
            }
        }
    }
}
