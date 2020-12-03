package no.rodland.advent_2020

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@DisableSlow
internal class Day03Test {
    val data03 = "2020/input_03.txt".readFile()
    val test03 = listOf(
        "..##.......",
        "#...#...#..",
        ".#....#..#.",
        "..#.#...#.#",
        ".#...##..#.",
        "..#.##.....",
        ".#.#.#....#",
        ".#........#",
        "#.##...#...",
        "#...##....#",
        ".#..#...#.#",
    )

    @Nested
    inner class `Part 1` {
        @Test
        fun `03,1,test`() {
            report {
                Day03.partOne(test03) to 7
            }
        }

        @Test
        fun `03,1,live`() {
            report {
                Day03.partOne(data03) to 162
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `03,2,test`() {
            report {
                Day03.partTwo(test03) to 2
            }
        }

        @Test
        fun `03,2,live`() {
            report {
                Day03.partTwo(data03) to 2
            }
        }
    }
}


