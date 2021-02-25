package no.rodland.advent_2019

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisableSlow
internal class Day25Test {
    val data25 = "2019/input_25.txt".readFirstLineStrings()
    val test25 = listOf("1", "2")

    @Nested
    inner class Init {
        @Test
        fun `25,1,live,init`() {
            report {
                Day25.partOne(data25) to 2
            }
        }

        @Test
        fun `25,2,live,init`() {
            report {
                Day25.partTwo(data25) to 2
            }
        }
    }

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `25,1,test`() {
            report {
                Day25.partOne(test25) to 2
            }
        }

        @Test
        fun `25,1,live,1`() {
            report {
                Day25.partOne(data25) to 2
            }
        }

        @Test
        fun `25,1,live,2`() {
            report {
                Day25.partOne(data25) to 2
            }
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `25,2,test`() {
            report {
                Day25.partTwo(test25) to 2
            }
        }

        @Test
        fun `25,2,live,1`() {
            report {
                Day25.partTwo(data25) to 2
            }
        }

        @Test
        fun `25,2,live,2`() {
            report {
                Day25.partTwo(data25) to 2
            }
        }
    }
}
