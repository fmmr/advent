package no.rodland.advent_2019

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisableSlow
internal class Day08Test {
    val data08 = "2019/input_08.txt".readFirstLineConvertToInts()
    val test08 = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 1, 2)

    @Nested
    inner class `Part 1` {
        @Test
        fun `08,1,test`() {
            report {
                Day08.partOne(test08, 3, 2) to 1
            }
        }

        @Test
        fun `08,1,live`() {
            report {
                Day08.partOne(data08, 25, 6) to 1584
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `08,2,test`() {
            report {
                Day08.partTwo(listOf(0, 2, 2, 2, 1, 1, 2, 2, 2, 2, 1, 2, 0, 0, 0, 0), 2, 2) to
                        listOf(
                                listOf(0, 1),
                                listOf(1, 0)
                        )
            }
        }

        @Test
        fun `08,2,live`() {
            report {
                Day08.partTwo(data08, 25, 6) to
                        listOf(
                                listOf(1, 0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 0, 0),
                                listOf(1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 0),
                                listOf(1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 1, 0, 0, 0, 0),
                                listOf(1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0),
                                listOf(1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 0),
                                listOf(1, 0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 1, 1, 0, 0)
                        )
            }
        }
    }
}


