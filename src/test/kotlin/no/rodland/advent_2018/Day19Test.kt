package no.rodland.advent_2018

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@DisableSlow
internal class Day19Test {
    val data19 = "2018/input_19.txt".readFile()
    val test19 = listOf("1", "2")

    @Nested
    inner class `Part 1` {
        @Test
        fun `19,1,test`() {
            report {
                Day19.partOne(test19) to 2
            }
        }

        @Test
        fun `19,1,live`() {
            report {
                Day19.partOne(data19) to 2
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `19,2,test`() {
            report {
                Day19.partTwo(test19) to 2
            }
        }

        @Test
        fun `19,2,live`() {
            report {
                Day19.partTwo(data19) to 2
            }
        }
    }
}


