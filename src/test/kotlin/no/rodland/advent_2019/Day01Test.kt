package no.rodland.advent_2019

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@DisableSlow
internal class Day01Test {
    val data01 = "2019/input_01.txt".readFile()
    val test01 = listOf("1", "2")

    @Nested
    inner class `Part 1` {
        @Test
        fun `01,1,test`() {
            report {
                Day01.partOne(test01) to 2
            }
        }

        @Test
        fun `01,1,live`() {
            report {
                Day01.partOne(data01) to 2
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `01,2,test`() {
            report {
                Day01.partTwo(test01) to 2
            }
        }

        @Test
        fun `01,2,live`() {
            report {
                Day01.partTwo(data01) to 2
            }
        }
    }
}


