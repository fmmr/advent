package no.rodland.advent_2018

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@DisableSlow
internal class Day13Test {
    val data13 = "2018/input_13.txt".readFile()
    val test13Simple = listOf("|", "v", "|", "|", "|", "^", "|")
    val test13 = "2018/input_13_test.txt".readFile()
    val test132 = "2018/input_13_test2.txt".readFile()

    @Nested
    inner class `Part 1` {
        @Test
        fun `13,1,test`() {
            report {
                Day13.partOne(test13Simple) to (0 to 3)
            }
        }

        @Test
        fun `13,1,test,2`() {
            report {
                Day13.partOne(test13) to (7 to 3)
            }
        }

        @Test
        fun `13,1,live`() {
            report {
                Day13.partOne(data13) to (123 to 18)
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `13,2,test`() {
            report {
                Day13.partTwo(test132) to (6 to 4)
            }
        }

        @Test
        fun `13,2,live`() {
            report {
                Day13.partTwo(data13) to (71 to 123)
            }
        }
    }
}


