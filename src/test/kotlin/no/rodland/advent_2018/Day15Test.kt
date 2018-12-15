package no.rodland.advent_2018

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@DisableSlow
internal class Day15Test {
    val data15 = "2018/input_15.txt".readFile()
    val test15 = listOf("1", "2")

    @Nested
    inner class `Part 1` {
        @Test
        fun `15,1,test`() {
            report {
                Day15.partOne(test15) to 2
            }
        }

        @Test
        fun `15,1,live`() {
            report {
                Day15.partOne(data15) to 2
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `15,2,test`() {
            report {
                Day15.partTwo(test15) to 2
            }
        }

        @Test
        fun `15,2,live`() {
            report {
                Day15.partTwo(data15) to 2
            }
        }
    }
}


