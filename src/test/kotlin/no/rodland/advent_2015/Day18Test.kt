package no.rodland.advent_2015

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@Suppress("ClassName")
@DisableSlow
internal class Day18Test {
    val data18 = "2015/input_18.txt".readFile()
    val test18 = listOf(
            ".#.#.#",
            "...##.",
            "#....#",
            "..#...",
            "#.#..#",
            "####.."
    )

    @Nested
    inner class Init {
        @Test
        fun `18,1,live,init`() {
            report {
                Day18.partOne(data18, 100) to 1061
            }
        }

        @Test
        fun `18,2,live,init`() {
            report {
                Day18.partTwo(data18) to 1006
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `18,1,test`() {
            report {
                Day18.partOne(test18, 4) to 4
            }
        }

        @Test
        fun `18,1,live,1`() {
            report {
                Day18.partOne(data18, 100) to 1061
            }
        }

        @Test
        fun `18,1,live,2`() {
            report {
                Day18.partOne(data18, 100) to 1061
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `18,2,test`() {
            report {
                Day18.partTwo(test18, 5) to 19
            }
        }

        @Test
        fun `18,2,live,1`() {
            report {
                Day18.partTwo(data18) to 1006
            }
        }

        @Test
        fun `18,2,live,2`() {
            report {
                Day18.partTwo(data18) to 1006
            }
        }
    }
}
