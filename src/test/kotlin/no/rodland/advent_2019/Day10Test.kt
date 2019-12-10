package no.rodland.advent_2019

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@DisableSlow
internal class Day10Test {
    val data10 = "2019/input_10.txt".readFile()
    val test10 = listOf(".#..#",
            ".....",
            "#####",
            "....#",
            "...##")

    @Nested
    inner class `Part 1` {
        @Test
        fun `10,1,test`() {
            report {
                Day10.partOne(test10) to 8
            }
        }

        @Test
        fun `10,1,live`() {
            report {
                Day10.partOne(data10) to 221
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `10,2,live`() {
            report {
                Day10.partTwo(data10) to 806
            }
        }
    }
}


