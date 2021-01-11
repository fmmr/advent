package no.rodland.advent_2015

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@Suppress("ClassName")
@DisableSlow
internal class Day22Test {
    @Nested
    inner class Init {
        @Test
        fun `22,1,live,init`() {
            report {
                Day22.partOne(51, 9) to 900
            }
        }

        @Test
        fun `22,2,live,init`() {
            report {
                Day22.partTwo(51, 9) to 1216
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `22,1,live,1`() {
            report {
                Day22.partOne(51, 9) to 900
            }
        }

        @Test
        fun `22,1,live,2`() {
            report {
                Day22.partOne(51, 9) to 900
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `22,2,live,1`() {
            report {
                Day22.partTwo(51, 9) to 1216
            }
        }

        @Test
        fun `22,2,live,2`() {
            report {
                Day22.partTwo(51, 9) to 1216
            }
        }
    }
}
