package no.rodland.advent_2015

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@Suppress("ClassName")
@DisableSlow
internal class Day07Test {
    val data07 = "2015/input_07.txt".readFile()
    val test07 = listOf("1", "2")

    @Nested
    inner class Init {
        @Test
        fun `07,1,live,init`() {
            report {
                Day07.partOne(data07) to 2
            }
        }

        @Test
        fun `07,2,live,init`() {
            report {
                Day07.partTwo(data07) to 2
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `07,1,test`() {
            report {
                Day07.partOne(test07) to 2
            }
        }

        @Test
        fun `07,1,live,1`() {
            report {
                Day07.partOne(data07) to 2
            }
        }

        @Test
        fun `07,1,live,2`() {
            report {
                Day07.partOne(data07) to 2
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `07,2,test`() {
            report {
                Day07.partTwo(test07) to 2
            }
        }

        @Test
        fun `07,2,live,1`() {
            report {
                Day07.partTwo(data07) to 2
            }
        }

        @Test
        fun `07,2,live,2`() {
            report {
                Day07.partTwo(data07) to 2
            }
        }
    }
}
