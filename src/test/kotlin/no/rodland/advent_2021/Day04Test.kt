package no.rodland.advent_2021

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@Suppress("ClassName")
@DisableSlow
internal class Day04Test {
    val data04 = "2021/input_04.txt".readFile()
    val test04 = listOf(
        "7,4,9,5,11,17,23,2,0,14,21,24,10,16,13,6,15,25,12,22,18,20,8,19,3,26,1",
        "",
        "22 13 17 11  0",
        "8  2 23  4 24",
        "21  9 14 16  7",
        "6 10  3 18  5",
        "1 12 20 15 19",
        "",
        "3 15  0  2 22",
        "9 18 13 17  5",
        "19  8  7 25 23",
        "20 11 10 24  4",
        "14 21 16 12  6",
        "",
        "14 21 17 24  4",
        "10 16 15  9 19",
        "18  8 23 26 20",
        "22 11 13  6  5",
        "2  0 12  3  7",
    )

    @Nested
    inner class Init {
        @Test
        fun `04,1,live,init`() {
            report {
                Day04.partOne(data04) to 21607
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `04,1,test`() {
            report {
                Day04.partOne(test04) to 4512
            }
        }

        @Test
        fun `04,1,live,1`() {
            report {
                Day04.partOne(data04) to 21607
            }
        }

        @Test
        fun `04,1,live,2`() {
            report {
                Day04.partOne(data04) to 21607
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `04,2,test`() {
            report {
                Day04.partTwo(test04) to 1924
            }
        }

        @Test
        fun `04,2,live,1`() {
            report {
                Day04.partTwo(data04) to 19012
            }
        }

        @Test
        fun `04,2,live,2`() {
            report {
                Day04.partTwo(data04) to 19012
            }
        }
    }
}
