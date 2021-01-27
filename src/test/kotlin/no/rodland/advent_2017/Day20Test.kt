package no.rodland.advent_2017

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@Suppress("ClassName")
@DisableSlow
internal class Day20Test {
    val data20 = "2017/input_20.txt".readFile()
    val test20 = listOf(
            "1",
            "2",
    )

    @Nested
    inner class Init {
        @Test
        fun `20,1,live,init`() {
            report {
                Day20.partOne(data20) to 2
            }
        }

        @Test
        fun `20,2,live,init`() {
            report {
                Day20.partTwo(data20) to 2
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `20,1,test`() {
            report {
                Day20.partOne(test20) to 2
            }
        }

        @Test
        fun `20,1,live,1`() {
            report {
                Day20.partOne(data20) to 2
            }
        }

        @Test
        fun `20,1,live,2`() {
            report {
                Day20.partOne(data20) to 2
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `20,2,test`() {
            report {
                Day20.partTwo(test20) to 2
            }
        }

        @Test
        fun `20,2,live,1`() {
            report {
                Day20.partTwo(data20) to 2
            }
        }

        @Test
        fun `20,2,live,2`() {
            report {
                Day20.partTwo(data20) to 2
            }
        }
    }
}
