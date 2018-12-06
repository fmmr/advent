package no.rodland.advent_2017

import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

internal class Day1Test {
    private val data1 = "2017/input_1.txt".readFile()[0]

    @Nested
    inner class `Part 1` {
        @Test
        fun `1,1,test`() {
            report {
                Day1.partOne("91212129") to 9
            }
        }

        @Test
        fun `1,1,test2`() {
            report {
                Day1.partOne("1111") to 4
            }
        }

        @Test
        fun `1,1,live`() {
            report {
                Day1.partOne(data1) to 1182
            }
        }

    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `2,1,test`() {
            report {
                Day1.partTwo("12131415") to 4
            }
        }

        @Test
        fun `2,2,test`() {
            report {
                Day1.partTwo(data1) to 1152
            }
        }
    }
}