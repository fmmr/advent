package no.rodland.advent_2019

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisableSlow
internal class Day16Test {
    val data16 = "2019/input_16.txt".readFirstLineConvertToInts()
    val test16 = listOf(1, 2)

    @Nested
    inner class `Part 1` {
        @Test
        fun `16,1,test`() {
            report {
                Day16.partOne(test16) to 2
            }
        }

        @Test
        fun `16,1,live`() {
            report {
                Day16.partOne(data16) to 2
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `16,2,test`() {
            report {
                Day16.partTwo(test16) to 2
            }
        }

        @Test
        fun `16,2,live`() {
            report {
                Day16.partTwo(data16) to 2
            }
        }
    }
}


