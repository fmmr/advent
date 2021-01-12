package no.rodland.advent_2016

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@Suppress("ClassName")
@DisableSlow
internal class Day03Test {
    val data03 = "2016/input_03.txt".readFile()
    val test03 = listOf(
            "5 10 25",
            "5 10 10",
    )

    @Nested
    inner class Init {
        @Test
        fun `03,1,live,init`() {
            report {
                Day03.partOne(data03) to 1032
            }
        }

        @Test
        fun `03,2,live,init`() {
            report {
                Day03.partTwo(data03) to 2
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `03,1,test`() {
            report {
                Day03.partOne(test03) to 1
            }
        }

        @Test
        fun `03,1,live,1`() {
            report {
                Day03.partOne(data03) to 1032
            }
        }

        @Test
        fun `03,1,live,2`() {
            report {
                Day03.partOne(data03) to 1032
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `03,2,test`() {
            report {
                Day03.partTwo(test03) to 2
            }
        }

        @Test
        fun `03,2,live,1`() {
            report {
                Day03.partTwo(data03) to 2
            }
        }

        @Test
        fun `03,2,live,2`() {
            report {
                Day03.partTwo(data03) to 2
            }
        }
    }
}
