package no.rodland.advent_2017

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@DisableSlow
internal class Day9Test {
    val data9 = "2017/input_9.txt".readFile()[0]

    @Nested
    inner class `Part 1` {
        @Test
        fun `9,1,test`() {
            report {
                Day9.partOne("<>") to 2
            }
        }

        @Test
        fun `9,1,live`() {
            report {
                Day9.partOne(data9) to 2
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `9,2,test`() {
            report {
                Day9.partTwo("<>") to 2
            }
        }

        @Test
        fun `9,2,live`() {
            report {
                Day9.partTwo(data9) to 2
            }
        }
    }
}


