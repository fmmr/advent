package no.rodland.advent_2018

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@DisableSlow
internal class Day21Test {
    val data21 = "2018/input_21.txt".readFile()
    val test21 = listOf("1", "2")

    @Nested
    inner class `Part 1` {
        @Test
        fun `21,1,test`() {
            report {
                Day21.partOne(test21) to 2
            }
        }

        @Test
        fun `21,1,live`() {
            report {
                Day21.partOne(data21) to 2
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `21,2,test`() {
            report {
                Day21.partTwo(test21) to 2
            }
        }

        @Test
        fun `21,2,live`() {
            report {
                Day21.partTwo(data21) to 2
            }
        }
    }
}


