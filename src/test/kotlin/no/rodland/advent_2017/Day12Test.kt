package no.rodland.advent_2017

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@DisableSlow
internal class Day12Test {
    val data12 = "2017/input_12.txt".readFile()

    @Nested
    inner class `Part 1` {
        @Test
        fun `12,1,test`() {
            report {
                Day12.partOne(listOf(
                        "0 <-> 2",
                        "1 <-> 1",
                        "2 <-> 0, 3, 4",
                        "3 <-> 2, 4",
                        "4 <-> 2, 3, 6",
                        "5 <-> 6",
                        "6 <-> 4, 5"
                )) to 6
            }
        }

        @Test
        fun `12,1,live`() {
            report {
                Day12.partOne(data12) to 115
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `12,2,test`() {
            report {
                Day12.partTwo(listOf(
                        "0 <-> 2",
                        "1 <-> 1",
                        "2 <-> 0, 3, 4",
                        "3 <-> 2, 4",
                        "4 <-> 2, 3, 6",
                        "5 <-> 6",
                        "6 <-> 4, 5"
                )) to 2
            }
        }

        @Test
        fun `12,2,live`() {
            report {
                Day12.partTwo(data12) to 221
            }
        }
    }
}


