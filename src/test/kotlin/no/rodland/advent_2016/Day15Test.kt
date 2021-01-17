package no.rodland.advent_2016

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@Suppress("ClassName")
@DisableSlow
internal class Day15Test {
    val data15 = listOf(
            "Disc #1 has 17 positions; at time=0, it is at position 1.",
            "Disc #2 has 7 positions; at time=0, it is at position 0.",
            "Disc #3 has 19 positions; at time=0, it is at position 2.",
            "Disc #4 has 5 positions; at time=0, it is at position 0.",
            "Disc #5 has 3 positions; at time=0, it is at position 0.",
            "Disc #6 has 13 positions; at time=0, it is at position 5.",
    )
    val data15_2 = data15 + "Disc #7 has 11 positions; at time=0, it is at position 0."
    val test15 = listOf(
            "Disc #1 has 5 positions; at time=0, it is at position 4.",
            "Disc #2 has 2 positions; at time=0, it is at position 1.",
    )

    @Nested
    inner class Init {
        @Test
        fun `15,1,live,init`() {
            report {
                Day15.partOne(data15) to 317371
            }
        }

        @Test
        fun `15,2,live,init`() {
            report {
                Day15.partOne(data15_2) to 2080951
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `15,1,test,logic_0_0`() {
            report {
                val discs = test15.map { Day15.Disc(it) }
                discs[0].isAtZeroGivenTZero(0) to true
            }
        }

        @Test
        fun `15,1,test,logic_0_5`() {
            report {
                val discs = test15.map { Day15.Disc(it) }
                discs[0].isAtZeroGivenTZero(5) to true
            }
        }

        @Test
        fun `15,1,test,logic_1_0`() {
            report {
                val discs = test15.map { Day15.Disc(it) }
                discs[1].isAtZeroGivenTZero(0) to false
            }
        }

        @Test
        fun `15,1,test,logic_1_5`() {
            report {
                val discs = test15.map { Day15.Disc(it) }
                discs[1].isAtZeroGivenTZero(5) to true
            }
        }

        @Test
        fun `15,1,test`() {
            report {
                Day15.partOne(test15) to 5
            }
        }

        @Test
        fun `15,1,live,1`() {
            report {
                Day15.partOne(data15) to 317371
            }
        }

        @Test
        fun `15,1,live,2`() {
            report {
                Day15.partOne(data15) to 317371
            }
        }
    }

    @Nested
    inner class `Part 2` {

        @Test
        fun `15,2,live,1`() {
            report {
                Day15.partOne(data15_2) to 2080951
            }
        }

        @Test
        fun `15,2,live,2`() {
            report {
                Day15.partOne(data15_2) to 2080951
            }
        }
    }
}
