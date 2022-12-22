package no.rodland.advent_2022

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@Suppress("ClassName")
@DisableSlow
internal class Day22Test {
    val data22 = "2022/input_22.txt".readFile()
    val test22 = listOf(
        "        ...#",
        "        .#..",
        "        #...",
        "        ....",
        "...#.......#",
        "........#...",
        "..#....#....",
        "..........#.",
        "        ...#....",
        "        .....#..",
        "        .#......",
        "        ......#.",
        "",
        "10R5L5R10L4R5L5",
    )

    val resultTestOne = 6032
    val resultOne = 95358

    val resultTestTwo = 2
    val resultTwo = 2

    @Nested
    inner class Init {
        @Test
        fun `22,1,live,init`() {
            report {
                Day22.partOne(data22) to resultOne
            }
        }

        @Test
        fun `22,2,live,init`() {
            report {
                Day22.partTwo(data22) to resultTwo
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `22,1,test`() {
            report {
                Day22.partOne(test22) to resultTestOne
            }
        }

        @Test
        fun `22,1,live,1`() {
            report {
                Day22.partOne(data22) to resultOne
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `22,2,test`() {
            report {
                Day22.partTwo(test22) to resultTestTwo
            }
        }

        @Test
        fun `22,2,live,1`() {
            report {
                Day22.partTwo(data22) to resultTwo
            }
        }
    }
}
