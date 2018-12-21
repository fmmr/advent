package no.rodland.advent_2017

import no.rodland.advent.DisableSlow
import no.rodland.advent.Slow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@DisableSlow
internal class Day10Test {
    val data10 = "2017/input_10.txt".readFile()[0].split(",").map { it.toInt() }
    val test10 = listOf("1", "2")

    @Nested
    inner class `Part 1` {
        @Test
        fun `10,1,test`() {
            report {
                Day10.partOne(listOf(3, 4, 1, 5), 0..4) to 12
            }
        }

        @Test
        fun `10,1,live`() {
            report {
                Day10.partOne(data10) to 826
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        @Slow(380)
        fun `10,2,test,1`() {
            report {
                Day10.partTwo("1,2,3") to "3efbe78a8d82f29979031a4aa0b16a9d"
            }
        }

        @Test
        @Slow(560)
        fun `10,2,test,2`() {
            report {
                Day10.partTwo("AoC 2017") to "33efeb34ea91902bb2f59c9920caa6cd"
            }
        }

        @Test
        fun `10,2,test,3`() {
            report {
                Day10.partTwo("") to "a2582a3a0e66e6e86e3812dcb672a272"
            }
        }

        @Test
        @Slow(323)
        fun `10,2,test,4`() {
            report {
                Day10.partTwo("1,2,4") to "63960835bcdc130f0b66d7ff4f6a5a8e"
            }
        }

        @Test
        @Slow(9400)
        fun `10,2,live`() {
            report {
                Day10.partTwo("2017/input_10.txt".readFile()[0]) to "d067d3f14d07e09c2e7308c3926605c4"
            }
        }
    }
}


