package no.rodland.advent_2019

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisableSlow
internal class Day13Test {
    val data13 = "2019/input_13.txt".readFirstLineStrings()
    val test13 = listOf("1", "2")

    @Nested
    inner class `Part 1` {
        @Test
        fun `13,1,live`() {
            report {
                Day13.partOne(data13) to 228
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `13,2,live`() {
            report {
                Day13.partTwo(data13, "2") to 10776
            }
        }
    }
}


