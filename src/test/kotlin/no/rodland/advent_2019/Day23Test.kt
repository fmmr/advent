package no.rodland.advent_2019

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisableSlow
internal class Day23Test {
    val data23 = "2019/input_23.txt".readFirstLineStrings()
    val test23 = listOf("1", "2")

    @Nested
    inner class `Part 1` {
        @Test
        fun `23,1,test`() {
            report {
                Day23.partOne(test23) to 2L
            }
        }

        @Test
        fun `23,1,live`() {
            report {
                Day23.partOne(data23) to 2L
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `23,2,test`() {
            report {
                Day23.partTwo(test23) to 2L
            }
        }

        @Test
        fun `23,2,live`() {
            report {
                Day23.partTwo(data23) to 2L
            }
        }
    }
}


