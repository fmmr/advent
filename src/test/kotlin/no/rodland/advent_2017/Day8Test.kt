package no.rodland.advent_2017

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@DisableSlow
internal class Day8Test {
    val data8 = "2018/input_8.txt".readFile()
    val test8 = listOf("1", "2")

    @Nested
    inner class `Part 1` {
        @Test
        fun `8,1,test`() {
            report {
                Day8.partOne(test8) to 2
            }
        }

        @Test
        fun `8,1,live`() {
            report {
                Day8.partOne(data8) to 2
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `8,2,test`() {
            report {
                Day8.partTwo(test8) to 2
            }
        }

        @Test
        fun `8,2,live`() {
            report {
                Day8.partTwo(data8) to 2
            }
        }
    }
}


