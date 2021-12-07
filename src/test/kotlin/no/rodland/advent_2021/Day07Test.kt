package no.rodland.advent_2021

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@Suppress("ClassName")
@DisableSlow
internal class Day07Test {
    val data07 = "2021/input_07.txt".readFile()[0].split(",").map { it.toInt() }
    val test07 = listOf(16, 1, 2, 0, 4, 2, 7, 1, 2, 14)

    @Nested
    inner class Init {
        @Test
        fun `07,1,live,init`() {
            report {
                Day07.partOne(data07) to 342534
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `07,1,test`() {
            report {
                Day07.partOne(test07) to 37
            }
        }

        @Test
        fun `07,1,live,1`() {
            report {
                Day07.partOne(data07) to 342534
            }
        }

        @Test
        fun `07,1,live,2`() {
            report {
                Day07.partOne(data07) to 342534
            }
        }
    }

    @Nested
    inner class `Part 2 cost` {
        @Test
        fun `07,2,costPart2 16 5`() {
            report {
                Day07.costPart2(16, 5) to 66
            }
        }

        @Test
        fun `07,2,costPart2 5 16`() {
            report {
                Day07.costPart2(5, 16) to 66
            }
        }

        @Test
        fun `07,2,costPart2 5 4`() {
            report {
                Day07.costPart2(5, 4) to 1
            }
        }

        @Test
        fun `07,2,costPart2 2 5`() {
            report {
                Day07.costPart2(2, 5) to 6
            }
        }

        @Test
        fun `07,2,costPart2 3 5`() {
            report {
                Day07.costPart2(3, 5) to 3
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `07,2,test`() {
            report {
                Day07.partTwo(test07) to 168
            }
        }

        @Test
        fun `07,2,live,1`() {
            report {
                Day07.partTwo(data07) to 94004208
            }
        }

        @Test
        fun `07,2,live,2`() {
            report {
                Day07.partTwo(data07) to 94004208
            }
        }
    }
}
