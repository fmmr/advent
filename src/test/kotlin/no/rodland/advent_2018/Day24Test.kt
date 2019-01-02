package no.rodland.advent_2018

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@DisableSlow
internal class Day24Test {
    val data24 = "2018/input_24.txt".readFile()
    val test24 = listOf("1", "2")

    @Nested
    inner class `Part 1` {
        @Test
        fun `24,1,test`() {
            report {
                Day24.partOne(test24) to 2
            }
        }

        @Test
        fun `24,1,live`() {
            report {
                Day24.partOne(data24) to 2
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `24,2,test`() {
            report {
                Day24.partTwo(test24) to 2
            }
        }

        @Test
        fun `24,2,live`() {
            report {
                Day24.partTwo(data24) to 2
            }
        }
    }
}


