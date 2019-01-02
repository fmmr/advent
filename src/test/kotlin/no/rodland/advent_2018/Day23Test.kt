package no.rodland.advent_2018

import no.rodland.advent.DisableSlow
import no.rodland.advent.Slow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@DisableSlow
internal class Day23Test {
    val data23 = "2018/input_23.txt".readFile()

    @Nested
    inner class `Part 1` {
        @Test
        fun `23,1,test`() {
            report {
                Day23.partOne(listOf(
                        "pos=<0,0,0>, r=4",
                        "pos=<1,0,0>, r=1",
                        "pos=<4,0,0>, r=3",
                        "pos=<0,2,0>, r=1",
                        "pos=<0,5,0>, r=3",
                        "pos=<0,0,3>, r=1",
                        "pos=<1,1,1>, r=1",
                        "pos=<1,1,2>, r=1",
                        "pos=<1,3,1>, r=1"
                )) to 7
            }
        }

        @Test
        fun `23,1,live`() {
            report {
                Day23.partOne(data23) to 297
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `23,2,test`() {
            report {
                Day23.naivePartTwo(listOf(
                        "pos=<10,12,12>, r=2",
                        "pos=<12,14,12>, r=2",
                        "pos=<16,12,12>, r=4",
                        "pos=<14,14,14>, r=6",
                        "pos=<50,50,50>, r=200",
                        "pos=<10,10,10>, r=5"
                )) to Day23.NanoBot(12, 12, 12, 0)
            }
        }

        @Test
        fun `23,2,test,2`() {
            report {
                Day23.partTwo(listOf(
                        "pos=<10,12,12>, r=2",
                        "pos=<12,14,12>, r=2",
                        "pos=<16,12,12>, r=4",
                        "pos=<14,14,14>, r=6",
                        "pos=<50,50,50>, r=200",
                        "pos=<10,10,10>, r=5"
                )) to 36
            }
        }

        @Test
        @Slow(600)
        fun `23,2,live`() {
            report {
                Day23.partTwo(data23) to 126233088
            }
        }
    }
}


