package no.rodland.advent_2021

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@Suppress("ClassName")
@DisableSlow
internal class Day06Test {
    val data06 = "2021/input_06.txt".readFile()[0].split(",").map { it.toLong() }
    val test06 = listOf(3, 4, 3, 1, 2).map { it.toLong() }

    @Nested
    inner class Init {
        @Test
        fun `06,1,live,init`() {
            report {
                Day06.partOne(data06) to 352872L
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `06,1,test`() {
            report {
                Day06.partOne(test06) to 5934L
            }
        }

        @Test
        fun `06,1,live,1`() {
            report {
                Day06.partOne(data06) to 352872L
            }
        }

        @Test
        fun `06,1,live,2`() {
            report {
                Day06.partOne(data06) to 352872L
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `06,2,test`() {
            report {
                Day06.partTwo(test06) to 26984457539L
            }
        }

        @Test
        fun `06,2,live,1`() {
            report {
                Day06.partTwo(data06) to 1604361182149L
            }
        }

        @Test
        fun `06,2,live,2`() {
            report {
                Day06.partTwo(data06) to 1604361182149L
            }
        }
    }
}
