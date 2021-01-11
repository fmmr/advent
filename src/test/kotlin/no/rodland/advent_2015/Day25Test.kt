package no.rodland.advent_2015

import no.rodland.advent.DisableSlow
import no.rodland.advent.Slow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@Suppress("ClassName")
@DisableSlow
internal class Day25Test {
    @Nested
    inner class `Part 1` {
        @Test
        @Slow(400)
        fun `25,1,live,1`() {
            report {
                Day25.partOne() to 19980801
            }
        }

        @Test
        @Slow(400)
        fun `25,1,live,2`() {
            report {
                Day25.partOne() to 19980801
            }
        }
    }
}
