package no.rodland.advent_2016

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@Suppress("ClassName")
@DisableSlow
internal class Day06Test {
    val data06 = "2016/input_06.txt".readFile()
    val test06 = listOf(
            "eedadn",
            "drvtee",
            "eandsr",
            "raavrd",
            "atevrs",
            "tsrnev",
            "sdttsa",
            "rasrtv",
            "nssdts",
            "ntnada",
            "svetve",
            "tesnvt",
            "vntsnd",
            "vrdear",
            "dvrsen",
            "enarar",
    )

    @Nested
    inner class Init {
        @Test
        fun `06,1,live,init`() {
            report {
                Day06.partOne(data06) to "2"
            }
        }

        @Test
        fun `06,2,live,init`() {
            report {
                Day06.partTwo(data06) to "2"
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `06,1,test`() {
            report {
                Day06.partOne(test06) to "easter"
            }
        }

        @Test
        fun `06,1,live,1`() {
            report {
                Day06.partOne(data06) to "2"
            }
        }

        @Test
        fun `06,1,live,2`() {
            report {
                Day06.partOne(data06) to "2"
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `06,2,test`() {
            report {
                Day06.partTwo(test06) to "2"
            }
        }

        @Test
        fun `06,2,live,1`() {
            report {
                Day06.partTwo(data06) to "2"
            }
        }

        @Test
        fun `06,2,live,2`() {
            report {
                Day06.partTwo(data06) to "2"
            }
        }
    }
}
