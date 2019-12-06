package no.rodland.advent_2019

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@DisableSlow
internal class Day06Test {
    val data06 = "2019/input_06.txt".readFile()
    val test06 = listOf("1", "2")

    @Nested
    inner class `Part 1` {
        @Test
        fun `06,1,test`() {
            report {
                Day06.partOne(test06) to 2
            }
        }

        @Test
        fun `06,1,live`() {
            report {
                Day06.partOne(data06) to 2
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `06,2,test`() {
            report {
                Day06.partTwo(test06) to 2
            }
        }

        @Test
        fun `06,2,live`() {
            report {
                Day06.partTwo(data06) to 2
            }
        }
    }
}


