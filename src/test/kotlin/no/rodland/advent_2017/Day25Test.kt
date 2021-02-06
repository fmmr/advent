package no.rodland.advent_2017

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
        fun `25,1,test`() {
            report {
                Day25.partOneTest("A", 6L) to 3
            }
        }

        @Test
        @Slow(1100)
        fun `25,1,live,1`() {
            report {
                Day25.partOne("A", 12302209L) to 2
            }
        }
    }
}
