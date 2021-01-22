package no.rodland.advent_2016

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@Suppress("ClassName")
@DisableSlow
internal class Day25Test {
    val data25 = "2016/input_25.txt".readFile()

    @Nested
    inner class `Part 1` {
        @Test
        fun `25,1,live,1`() {
            report {
                Day25.partOne(data25) to 158
            }
        }
    }
}
