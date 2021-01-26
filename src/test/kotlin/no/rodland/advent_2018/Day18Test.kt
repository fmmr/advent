package no.rodland.advent_2018

import no.rodland.advent.DisableSlow
import no.rodland.advent.Slow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@DisableSlow
internal class Day18Test {
    val data18 = "2018/input_18.txt".readFile()
    val test18 = listOf(
            ".#.#...|#.",
            ".....#|##|",
            ".|..|...#.",
            "..|#.....#",
            "#.#|||#|#|",
            "...#.||...",
            ".|....|...",
            "||...#|.#|",
            "|.||||..|.",
            "...#.|..|.")

    @Nested
    inner class `Part 1` {
        @Test
        fun `18,1,test`() {
            report {
                Day18.partOne(test18) to 1147
            }
        }

        @Test
        fun `18,1,live`() {
            report {
                Day18.partOne(data18) to 360720
            }
        }
    }

    @Nested
    inner class `Part 2` {

        @Test
        fun `18,2,test`() {
            report {
                Day18.partTwo(test18) to 0
            }
        }

        @Test
        @Slow(2000)
        fun `18,2,live`() {
            report {
                Day18.partTwo(data18) to 197276
            }
        }
    }
}


