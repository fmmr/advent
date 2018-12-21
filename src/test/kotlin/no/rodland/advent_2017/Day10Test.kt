package no.rodland.advent_2017

import no.rodland.advent.DisableSlow
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
                Day10.partOne(listOf(3, 4, 1, 5), 0..4) to 2
            }
        }

        @Test
        fun `10,1,live`() {
            report {
                Day10.partOne(data10) to 2
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `10,2,test`() {
            report {
                Day10.partTwo(listOf(3, 4, 1, 5)) to 2
            }
        }

        @Test
        fun `10,2,live`() {
            report {
                Day10.partTwo(data10) to 2
            }
        }
    }
}


