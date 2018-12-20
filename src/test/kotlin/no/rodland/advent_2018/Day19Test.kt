package no.rodland.advent_2018

import no.rodland.advent.DisableSlow
import no.rodland.advent.Slow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@DisableSlow
internal class Day19Test {
    val data19 = "2018/input_19.txt".readFile()
    val test19 = listOf(
            "#ip 0",
            "seti 5 0 1",
            "seti 6 0 2",
            "addi 0 1 0",
            "addr 1 2 3",
            "setr 1 0 0",
            "seti 8 0 4",
            "seti 9 0 5"
    )

    @Nested
    inner class `Part 1` {
        @Test
        fun `19,1,test`() {
            report {
                Day19.partOne(test19, 0) to 6
            }
        }

        @Test
        fun `19,1,live`() {
            report {
                Day19.partOne(data19, 0) to 1228
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `19,2,live`() {
            report {
                Day19.partTwo() to 15285504
            }
        }

        @Test
        @Slow(2000000000)
        fun `19,2,run`() {
            report {
                Day19.partOne(data19, 1) to 15285504
            }
        }
    }
}


