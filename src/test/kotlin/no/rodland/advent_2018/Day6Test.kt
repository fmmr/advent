package no.rodland.advent_2018

import Day6
import no.rodland.advent.DisableSlow
import no.rodland.advent.Slow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@DisableSlow
internal class Day6Test {

    val data6 = "2018/input_6.txt".readFile().map { it.split(", ")[0].toInt() to it.split(", ")[1].toInt() }
    val test6 = listOf("1, 1", "1, 6", "8, 3", "3, 4", "5, 5", "8, 9").map { it.split(", ")[0].toInt() to it.split(", ")[1].toInt() }

    @Nested
    inner class `Part 1` {
        @Test
        fun `6,1,test`() {
            report {
                Day6.partOne(test6) to 17
            }
        }

        @Test
        @Slow(270)
        fun `6,1,live`() {
            report {
                Day6.partOne(data6) to 5626
            }
        }

        @Test
        fun `6,1,test,getClosest`() {
            report {
                Day6.getClosestPos(test6, 4, 6) to (5 to 5)
            }
        }

        @Test
        fun `6,1,test,getClosestMultiple`() {
            report {
                Day6.getClosestPos(test6, 0, 4) to null
            }
        }

        @Test
        fun `6,1,test,getdistance`() {
            report {
                Day6.getDistance((1 to 2), 4, 6) to 7
            }
        }

    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `6,2,test`() {
            report {
                Day6.partTwo(test6, 32) to 16
            }
        }

        @Test
        fun `6,2,live`() {
            report {
                Day6.partTwo(data6, 10000) to 46554
            }
        }

    }
}

