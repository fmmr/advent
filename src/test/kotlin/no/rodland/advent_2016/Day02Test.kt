package no.rodland.advent_2016

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@Suppress("ClassName")
@DisableSlow
internal class Day02Test {
    val data02 = "2016/input_02.txt".readFile()
    val test02 = listOf("1", "2")

    @Nested
    inner class Init {
        @Test
        fun `02,1,live,init`() {
            report {
                Day02.partOne(data02) to 2
            }
        }

        @Test
        fun `02,2,live,init`() {
            report {
                Day02.partTwo(data02) to 2
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `02,1,test`() {
            report {
                Day02.partOne(test02) to 2
            }
        }

        @Test
        fun `02,1,live,1`() {
            report {
                Day02.partOne(data02) to 2
            }
        }

        @Test
        fun `02,1,live,2`() {
            report {
                Day02.partOne(data02) to 2
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `02,2,test`() {
            report {
                Day02.partTwo(test02) to 2
            }
        }

        @Test
        fun `02,2,live,1`() {
            report {
                Day02.partTwo(data02) to 2
            }
        }

        @Test
        fun `02,2,live,2`() {
            report {
                Day02.partTwo(data02) to 2
            }
        }
    }
}
