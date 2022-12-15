package no.rodland.advent_2022

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@Suppress("ClassName")
@DisableSlow
internal class Day15Test {
    val data15 = "2022/input_15.txt".readFile()
    val test15 = listOf(
        "Sensor at x=2, y=18: closest beacon is at x=-2, y=15",
        "Sensor at x=9, y=16: closest beacon is at x=10, y=16",
        "Sensor at x=13, y=2: closest beacon is at x=15, y=3",
        "Sensor at x=12, y=14: closest beacon is at x=10, y=16",
        "Sensor at x=10, y=20: closest beacon is at x=10, y=16",
        "Sensor at x=14, y=17: closest beacon is at x=10, y=16",
        "Sensor at x=8, y=7: closest beacon is at x=2, y=10",
        "Sensor at x=2, y=0: closest beacon is at x=2, y=10",
        "Sensor at x=0, y=11: closest beacon is at x=2, y=10",
        "Sensor at x=20, y=14: closest beacon is at x=25, y=17",
        "Sensor at x=17, y=20: closest beacon is at x=21, y=22",
        "Sensor at x=16, y=7: closest beacon is at x=15, y=3",
        "Sensor at x=14, y=3: closest beacon is at x=15, y=3",
        "Sensor at x=20, y=1: closest beacon is at x=15, y=3",
    )

    val resultTestOne = 26
    val resultOne = 4748135

    val resultTestTwo = 2 //56000011
    val resultTwo = 2

    @Nested
    inner class Init {
        @Test
        fun `15,1,live,init`() {
            report {
                Day15.partOne(data15, 2000000) to resultOne
            }
        }

        @Test
        fun `15,2,live,init`() {
            report {
                Day15.partTwo(data15) to resultTwo
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `15,1,test`() {
            report {
                Day15.partOne(test15, 10) to resultTestOne
            }
        }

        @Test
        fun `15,1,live,1`() {
            report {
                Day15.partOne(data15, 2000000) to resultOne
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `15,2,test`() {
            report {
                Day15.partTwo(test15) to resultTestTwo
            }
        }

        @Test
        fun `15,2,live,1`() {
            report {
                Day15.partTwo(data15) to resultTwo
            }
        }
    }
}
