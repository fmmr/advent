package no.rodland.advent_2018

import Day7
import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@DisableSlow
internal class Day7Test {

    val data7 = "2018/input_7.txt".readFile()
    val test7 = listOf("Step C must be finished before step A can begin", "Step C must be finished before step F can begin", "Step A must be finished before step B can begin", "Step A must be finished before step D can begin", "Step B must be finished before step E can begin", "Step D must be finished before step E can begin", "Step F must be finished before step E can begin")


    @Nested
    inner class `Part 1` {
        @Test
        fun `7,1,test`() {
            report {
                Day7.partOne(test7) to "CABDFE"
            }
        }

        @Test
        fun `7,1,live`() {
            report {
                Day7.partOne(data7) to "CFGHAEMNBPRDISVWQUZJYTKLOX"
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `7,2,test`() {
            report {
                Day7.partTwo(test7, 0, 2) to 15
            }
        }

        @Test
        fun `7,2,live_0_2`() {
            report {
                Day7.partTwo(data7, 0, 2) to 210
            }
        }

        @Test
        fun `7,2,live_60_5`() {
            report {
                Day7.partTwo(data7, 60, 5) to 828
            }
        }

        @Test
        fun `7,2,workplacea`() {
            report {
                Day7.WorkPlace(60, 1).getSeconds("A") to 61
            }
        }

        @Test
        fun `7,2,workplacez`() {
            report {
                Day7.WorkPlace(0, 1).getSeconds("Z") to 26
            }
        }
    }
}


