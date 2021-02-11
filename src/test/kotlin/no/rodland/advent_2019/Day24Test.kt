package no.rodland.advent_2019

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisableSlow
internal class Day24Test {
    val data24 = "2019/input_24.txt".readFile()
    val test24 = listOf(
            "....#",
            "#..#.",
            "#..##",
            "..#..",
            "#....",
    )

    @Nested
    inner class Init {
        @Test
        fun `24,1,live,init`() {
            report {
                Day24.partOne(data24) to 2130474
            }
        }

        @Test
        fun `24,2,live,init`() {
            report {
                Day24.partTwo(data24) to 2
            }
        }
    }

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `24,1,test`() {
            report {
                Day24.partOne(test24) to 2129920
            }
        }

        @Test
        fun `24,1,live,1`() {
            report {
                Day24.partOne(data24) to 2130474
            }
        }

        @Test
        fun `24,1,live,2`() {
            report {
                Day24.partOne(data24) to 2130474
            }
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `24,2,test`() {
            report {
                Day24.partTwo(test24) to 2
            }
        }

        @Test
        fun `24,2,live,1`() {
            report {
                Day24.partTwo(data24) to 2
            }
        }

        @Test
        fun `24,2,live,2`() {
            report {
                Day24.partTwo(data24) to 2
            }
        }
    }
}
