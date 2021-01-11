package no.rodland.advent_2015

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@Suppress("ClassName")
@DisableSlow
internal class Day24Test {
    val data24 = "2015/input_24.txt".readFileInts()
    val test24 = listOf(1, 2, 3, 4, 5, 7, 8, 9, 10, 11)

    @Nested
    inner class Init {
        @Test
        fun `24,1,live,init`() {
            report {
                Day24.partOne(data24) to 11266889531
            }
        }

        @Test
        fun `24,2,live,init`() {
            report {
                Day24.partTwo(data24) to 77387711
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `24,1,test`() {
            report {
                Day24.partOne(test24) to 99
            }
        }

        @Test
        fun `24,1,live,1`() {
            report {
                Day24.partOne(data24) to 11266889531
            }
        }

        @Test
        fun `24,1,live,2`() {
            report {
                Day24.partOne(data24) to 11266889531
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `24,2,test`() {
            report {
                Day24.partTwo(test24) to 44
            }
        }

        @Test
        fun `24,2,live,1`() {
            report {
                Day24.partTwo(data24) to 77387711
            }
        }

        @Test
        fun `24,2,live,2`() {
            report {
                Day24.partTwo(data24) to 77387711
            }
        }
    }
}
