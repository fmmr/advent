package no.rodland.advent_2020

import no.rodland.advent.DisableSlow
import no.rodland.advent.Pos
import no.rodland.advent.Slow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@Suppress("ClassName")
@DisableSlow
internal class Day11Test {
    val data11 = "2020/input_11.txt".readFile()
    val test11 = listOf(
        "L.LL.LL.LL",
        "LLLLLLL.LL",
        "L.L.L..L..",
        "LLLL.LL.LL",
        "L.LL.LL.LL",
        "L.LLLLL.LL",
        "..L.L.....",
        "LLLLLLLLLL",
        "L.LLLLLL.L",
        "L.LLLLL.LL",
    )

    @Nested
    inner class Init {
        @Test
        fun `11,1,live,init`() {
            report {
                Day11.partOne(data11) to 2346
            }
        }

        @Test
        @Slow(700)
        fun `11,2,live,init`() {
            report {
                Day11.partTwo(data11) to 2111
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `11,1,test`() {
            report {
                Day11.partOne(test11) to 37
            }
        }

        @Test
        fun `11,1,live,1`() {
            report {
                Day11.partOne(data11) to 2346
            }
        }

        @Test
        fun `11,1,live,2`() {
            report {
                Day11.partOne(data11) to 2346
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `11,2,test`() {
            report {
                Day11.partTwo(test11) to 26
            }
        }

        @Test
        fun `11,2,test,neighbours_1`() {
            report {
                val test = listOf(
                    ".......#.",
                    "...#.....",
                    ".#.......",
                    ".........",
                    "..#L....#",
                    "....#....",
                    ".........",
                    "#........",
                    "...#.....",
                )
                Day11.findNeighboursPart2(Pos(3, 4), test.map { it.toCharArray() }.toTypedArray()).count() to 8
            }
        }

        @Test
        fun `11,2,test,neighbours_2`() {
            report {
                val test = listOf(
                    ".##.##.",
                    "#.#.#.#",
                    "##...##",
                    "...L...",
                    "##...##",
                    "#.#.#.#",
                    ".##.##.",
                )
                Day11.findNeighboursPart2(Pos(3, 3), test.map { it.toCharArray() }.toTypedArray()).count() to 0
            }
        }

        @Test
        @Slow(2000)
        fun `11,2,live,1`() {
            report {
                Day11.partTwo(data11) to 2111
            }
        }
    }
}
