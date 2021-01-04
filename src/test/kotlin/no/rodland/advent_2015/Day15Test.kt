package no.rodland.advent_2015

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@Suppress("ClassName")
@DisableSlow
internal class Day15Test {
    val data15 = "2015/input_15.txt".readFile()
    val test15 = listOf(
        "Butterscotch: capacity -1, durability -2, flavor 6, texture 3, calories 8",
        "Cinnamon: capacity 2, durability 3, flavor -2, texture -1, calories 3"
    )

    @Nested
    inner class Init {
        @Test
        fun `15,1,live,init`() {
            report {
                Day15.partOne(data15) to 2
            }
        }

        @Test
        fun `15,2,live,init`() {
            report {
                Day15.partTwo(data15) to 2
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `15,1,test`() {
            report {
                Day15.partOne(test15) to 2
            }
        }

        @Test
        fun `15,1,live,1`() {
            report {
                Day15.partOne(data15) to 2
            }
        }

        @Test
        fun `15,1,live,2`() {
            report {
                Day15.partOne(data15) to 2
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `15,2,test`() {
            report {
                Day15.partTwo(test15) to 2
            }
        }

        @Test
        fun `15,2,live,1`() {
            report {
                Day15.partTwo(data15) to 2
            }
        }

        @Test
        fun `15,2,live,2`() {
            report {
                Day15.partTwo(data15) to 2
            }
        }
    }
}
