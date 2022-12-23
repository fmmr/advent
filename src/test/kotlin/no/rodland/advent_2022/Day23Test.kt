package no.rodland.advent_2022

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@Suppress("ClassName")
@DisableSlow
internal class Day23Test {
    private val data23 = "2022/input_23.txt".readFile()
    private val test23 = listOf(
        "....#..",
        "..###.#",
        "#...#.#",
        ".#...##",
        "#.###..",
        "##.#.##",
        ".#..#..",
    )
    private val test23Small = listOf(
        ".....",
        "..##.",
        "..#..",
        ".....",
        "..##.",
        ".....",
    )

    private val day23 = Day23(data23)
    private val day23Test = Day23(test23)
    private val day23TestSmall = Day23(test23Small)

    private val resultTestOne = 110
    private val resultTestOneSmall = 25
    private val resultOne = 4146
    private val resultTestTwo = 2L
    private val resultTwo = 2L

    @Nested
    inner class Init {
        @Test
        fun `23,1,live,init`() {
            report {
                day23.partOne() to resultOne
            }
        }

        @Test
        fun `23,2,live,init`() {
            report {
                day23.partTwo() to resultTwo
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `23,1,test`() {
            report {
                day23Test.partOne() to resultTestOne
            }
        }
        @Test
        fun `23,1,test,small`() {
            report {
                day23TestSmall.partOne() to resultTestOneSmall
            }
        }
        @Test
        fun `23,1,live,1`() {
            report {
                day23.partOne() to resultOne
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `23,2,test`() {
            report {
                day23Test.partTwo() to resultTestTwo
            }
        }


        @Test
        fun `23,2,live,1`() {
            report {
                day23.partTwo() to resultTwo
            }
        }
    }
}
