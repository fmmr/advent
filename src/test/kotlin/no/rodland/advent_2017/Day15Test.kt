package no.rodland.advent_2017

import no.rodland.advent.DisableSlow
import no.rodland.advent.Slow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@Suppress("ClassName")
@DisableSlow
internal class Day15Test {
    val data15 = listOf(722, 354)
    val test15 = listOf(65, 8921)

    @Nested
    @Slow(3500)
    inner class Init {
        @Test
        fun `15,1,live,init`() {
            report {
                Day15.partOne(data15) to 612
            }
        }

        @Test
        fun `15,2,live,init`() {
            report {
                Day15.partTwo(data15) to 285
            }
        }
    }

    @Nested
    @Slow(3500)
    inner class `Part 1` {
        @Test
        fun `15,1,test`() {
            report {
                Day15.partOne(test15) to 588
            }
        }

        @Test
        fun `15,1,live,1`() {
            report {
                Day15.partOne(data15) to 612
            }
        }
    }

    @Nested
    @Slow(3500)
    inner class `Part 2` {
        @Test
        fun `15,2,test`() {
            report {
                Day15.partTwo(test15) to 309
            }
        }

        @Test
        fun `15,2,live,1`() {
            report {
                Day15.partTwo(data15) to 285
            }
        }

        @Test
        fun `15,2,live,2`() {
            report {
                Day15.partTwo(data15) to 285
            }
        }
    }
}
