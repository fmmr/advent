package no.rodland.advent_2015

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@Suppress("ClassName")
@DisableSlow
internal class Day02Test {
    val data02 = "2015/input_02.txt".readFile()

    @Nested
    inner class Init {
        @Test
        fun `02,1,live,init`() {
            report {
                Day02.partOne(data02) to 1588178
            }
        }

        @Test
        fun `02,2,live,init`() {
            report {
                Day02.partTwo(data02) to 3783758
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `02,1,test,1`() {
            report {
                Day02.partOne(listOf("2x3x4")) to 58
            }
        }

        @Test
        fun `02,1,test,2`() {
            report {
                Day02.partOne(listOf("1x1x10")) to 43
            }
        }

        @Test
        fun `02,1,live,1`() {
            report {
                Day02.partOne(data02) to 1588178
            }
        }

        @Test
        fun `02,1,live,2`() {
            report {
                Day02.partOne(data02) to 1588178
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `02,2,test,1`() {
            report {
                Day02.partTwo(listOf("2x3x4")) to 34
            }
        }

        @Test
        fun `02,2,test,2`() {
            report {
                Day02.partTwo(listOf("1x1x10")) to 14
            }
        }

        @Test
        fun `02,2,live,1`() {
            report {
                Day02.partTwo(data02) to 3783758
            }
        }

        @Test
        fun `02,2,live,2`() {
            report {
                Day02.partTwo(data02) to 3783758
            }
        }
    }
}
