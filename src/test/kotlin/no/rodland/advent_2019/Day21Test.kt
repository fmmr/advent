package no.rodland.advent_2019

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisableSlow
internal class Day21Test {
    val data21 = "2019/input_21.txt".readFirstLineStrings()

    @Nested
    inner class `Part 1` {
        @Test
        fun `21,1,live`() {
            report {
                Day21.partOne(data21) to 19359752L
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `21,2,live`() {
            report {
                Day21.partTwo(data21) to 1141869516L
            }
        }
    }
}


