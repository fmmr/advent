package no.rodland.advent_2019

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisableSlow
internal class Day17Test {
    val data17 = "2019/input_17.txt".readFirstLineStrings()
    val test17 = listOf("1", "2")

    @Nested
    inner class `Part 1` {
        @Test
        fun `17,1,live`() {
            report {
                Day17.partOne(data17) to 2
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `17,2,live`() {
            report {
                Day17.partTwo(data17) to 2
            }
        }
    }
}


