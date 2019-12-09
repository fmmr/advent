package no.rodland.advent_2019

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@DisableSlow
internal class Day09Test {
    val data09 = "2019/input_09.txt".readFile()
    val test09 = listOf("1", "2")

    @Nested
    inner class `Part 1` {
        @Test
        fun `09,1,test`() {
            report {
                Day09.partOne(test09) to 2
            }
        }

        @Test
        fun `09,1,live`() {
            report {
                Day09.partOne(data09) to 2
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `09,2,test`() {
            report {
                Day09.partTwo(test09) to 2
            }
        }

        @Test
        fun `09,2,live`() {
            report {
                Day09.partTwo(data09) to 2
            }
        }
    }
}


