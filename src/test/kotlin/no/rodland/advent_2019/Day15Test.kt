package no.rodland.advent_2019

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisableSlow
internal class Day15Test {
    val data15 = "2019/input_15.txt".readFirstLineStrings()
    val test15 = listOf("1", "2")

    @Nested
    inner class `Part 1` {
        @Test
        fun `15,1,live`() {
            report {
                Day15.partOne(data15) to 216
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `15,2,live`() {
            report {
                Day15.partTwo(data15) to 2
            }
        }
    }
}


