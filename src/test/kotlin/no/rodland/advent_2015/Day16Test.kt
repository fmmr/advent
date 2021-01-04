package no.rodland.advent_2015

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@Suppress("ClassName")
@DisableSlow
internal class Day16Test {
    val data16 = "2015/input_16.txt".readFile()
    val test16 = listOf("1", "2")

    @Nested
    inner class Init {
        @Test
        fun `16,1,live,init`() {
            report {
                Day16.partOne(data16) to 213
            }
        }

        @Test
        fun `16,2,live,init`() {
            report {
                Day16.partTwo(data16) to 323
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `16,1,live,1`() {
            report {
                Day16.partOne(data16) to 213
            }
        }

        @Test
        fun `16,1,live,2`() {
            report {
                Day16.partOne(data16) to 213
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `16,2,live,1`() {
            report {
                Day16.partTwo(data16) to 323
            }
        }

        @Test
        fun `16,2,live,2`() {
            report {
                Day16.partTwo(data16) to 323
            }
        }
    }
}
