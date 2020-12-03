package no.rodland.advent_2020

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@DisableSlow
internal class Day04Test {
    val data04 = "2020/input_04.txt".readFile()
    val test04 = listOf("1", "2")

    @Nested
    inner class `Part 1` {
        @Test
        fun `04,1,test`() {
            report {
                Day04.partOne(test04) to 2
            }
        }

        @Test
        fun `04,1,live`() {
            report {
                Day04.partOne(data04) to 2
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `04,2,test`() {
            report {
                Day04.partTwo(test04) to 2
            }
        }

        @Test
        fun `04,2,live`() {
            report {
                Day04.partTwo(data04) to 2
            }
        }
    }
}


