package no.rodland.advent_2015

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@Suppress("ClassName")
@DisableSlow
internal class Day14Test {
    val data14 = "2015/input_14.txt".readFile()
    val test14 = listOf(
        "Comet can fly 14 km/s for 10 seconds, but then must rest for 127 seconds.",
        "Dancer can fly 16 km/s for 11 seconds, but then must rest for 162 seconds.",
    )

    @Nested
    inner class Init {
        @Test
        fun `14,1,live,init`() {
            report {
                Day14.partOne(data14) to 2
            }
        }

        @Test
        fun `14,2,live,init`() {
            report {
                Day14.partTwo(data14) to 2
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `14,1,test`() {
            report {
                Day14.partOne(test14) to 2
            }
        }

        @Test
        fun `14,1,live,1`() {
            report {
                Day14.partOne(data14) to 2
            }
        }

        @Test
        fun `14,1,live,2`() {
            report {
                Day14.partOne(data14) to 2
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `14,2,test`() {
            report {
                Day14.partTwo(test14) to 2
            }
        }

        @Test
        fun `14,2,live,1`() {
            report {
                Day14.partTwo(data14) to 2
            }
        }

        @Test
        fun `14,2,live,2`() {
            report {
                Day14.partTwo(data14) to 2
            }
        }
    }
}
