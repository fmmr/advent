package no.rodland.advent_2019

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@DisableSlow
internal class Day14Test {
    val data14 = "2019/input_14.txt".readFile()
    val test14_1 = listOf(
            "10 ORE => 10 A",
            "1 ORE => 1 B",
            "7 A, 1 B => 1 C",
            "7 A, 1 C => 1 D",
            "7 A, 1 D => 1 E",
            "7 A, 1 E => 1 FUEL"
    )
    val test14_2 = listOf(
            "9 ORE => 2 A",
            "8 ORE => 3 B",
            "7 ORE => 5 C",
            "3 A, 4 B => 1 AB",
            "5 B, 7 C => 1 BC",
            "4 C, 1 A => 1 CA",
            "2 AB, 3 BC, 4 CA => 1 FUEL"
    )

    @Nested
    inner class `Part 1` {
        @Test
        fun `14,1,test,1`() {
            report {
                Day14.partOne(test14_1) to 31
            }
        }

        @Test
        fun `14,1,test,2`() {
            report {
                Day14.partOne(test14_2) to 165
            }
        }

        @Test
        fun `14,1,live`() {
            report {
                Day14.partOne(data14) to 374457
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `14,2,test`() {
            report {
                Day14.partTwo(test14_1) to 2
            }
        }

        @Test
        fun `14,2,live`() {
            report {
                Day14.partTwo(data14) to 2
            }
        }
    }
}


