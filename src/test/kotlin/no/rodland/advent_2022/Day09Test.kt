package no.rodland.advent_2022

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@Suppress("ClassName")
@DisableSlow
internal class Day09Test {
    val data09 = "2022/input_09.txt".readFile()
    val test09 = listOf(
        "R 4",
        "U 4",
        "L 3",
        "D 1",
        "R 4",
        "D 1",
        "L 5",
        "R 2",
    )
    val test092 = listOf(
        "R 5",
        "U 8",
        "L 8",
        "D 3",
        "R 17",
        "D 10",
        "L 25",
        "U 20",
    )


    val resultTestOne = 13
    val resultOne = 6391

    val resultTestTwo = 2
    val resultTestTwoB = 2
    val resultTwo = 2

    @Nested
    inner class Init {
        @Test
        fun `09,1,live,init`() {
            report {
                Day09.partOne(data09) to resultOne
            }
        }

        @Test
        fun `09,2,live,init`() {
            report {
                Day09.partTwo(data09) to resultTwo
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `09,1,test`() {
            report {
                Day09.partOne(test09) to resultTestOne
            }
        }

        @Test
        fun `09,1,live,1`() {
            report {
                Day09.partOne(data09) to resultOne
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `09,2,test,1`() {
            report {
                Day09.partTwo(test09) to resultTestTwo
            }
        }
        @Test
        fun `09,2,test,2`() {
            report {
                Day09.partTwo(test092) to resultTestTwoB
            }
        }

        @Test
        fun `09,2,live,1`() {
            report {
                Day09.partTwo(data09) to resultTwo
            }
        }
    }
}
