package no.rodland.advent_2018

import no.rodland.advent.DisableSlow
import no.rodland.advent.Slow
import no.rodland.advent.report
import org.junit.jupiter.api.Test
import readFile

@DisableSlow
internal class Day25Test {
    val data25 = "2018/input_25.txt".readFile()

    @Test
    fun `25,1,test,1`() {
        report {
            Day25.partOne(listOf(
                    "0,0,0,0",
                    "3,0,0,0",
                    "0,3,0,0",
                    "0,0,3,0",
                    "0,0,0,3",
                    "0,0,0,6",
                    "9,0,0,0",
                    "12,0,0,0"
            )) to 2
        }
    }

    @Test
    fun `25,1,test,2`() {
        report {
            Day25.partOne(listOf(
                    "-1,2,2,0",
                    "0,0,2,-2",
                    "0,0,0,-2",
                    "-1,2,0,0",
                    "-2,-2,-2,2",
                    "3,0,2,-1",
                    "-1,3,2,2",
                    "-1,0,-1,0",
                    "0,2,1,-2",
                    "3,0,0,0"
            )) to 4
        }
    }

    @Test
    fun `25,1,test,3`() {
        report {
            Day25.partOne(listOf(
                    "1,-1,0,1",
                    "2,0,-1,0",
                    "3,2,-1,0",
                    "0,0,3,1",
                    "0,0,-1,-1",
                    "2,3,-2,0",
                    "-2,2,0,0",
                    "2,-2,0,-1",
                    "1,-1,0,-1",
                    "3,2,0,2"
            )) to 3
        }
    }

    @Test
    fun `25,1,test,4`() {
        report {
            Day25.partOne(listOf(
                    "1,-1,-1,-2",
                    "-2,-2,0,1",
                    "0,2,1,3",
                    "-2,3,-2,1",
                    "0,2,3,-2",
                    "-1,-1,1,-2",
                    "0,-2,-1,0",
                    "-2,2,3,-1",
                    "1,2,2,0",
                    "-1,-2,0,-2"
            )) to 8
        }
    }

    @Test
    @Slow(800)
    fun `25,1,live`() {
        report {
            Day25.partOne(data25) to 394
        }
    }
}


