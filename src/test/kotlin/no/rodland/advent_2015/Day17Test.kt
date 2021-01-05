package no.rodland.advent_2015

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@Suppress("ClassName")
@DisableSlow
internal class Day17Test {
    val data17 = "2015/input_17.txt".readFileInts()
    val test17 = listOf(20, 15, 10, 5, 5)

    @Nested
    inner class Init {
        @Test
        fun `17,1,live,init`() {
            report {
                Day17.partOne(data17, 150) to 4372
            }
        }

        @Test
        fun `17,2,live,init`() {
            report {
                Day17.partTwo(data17, 150) to 4
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `17,1,test`() {
            report {
                Day17.partOne(test17, 25) to 4
            }
        }

        @Test
        fun `17,1,live,1`() {
            report {
                Day17.partOne(data17, 150) to 4372
            }
        }

        @Test
        fun `17,1,live,2`() {
            report {
                Day17.partOne(data17, 150) to 4372
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `17,2,test`() {
            report {
                Day17.partTwo(test17, 25) to 3
            }
        }

        @Test
        fun `17,2,live,1`() {
            report {
                Day17.partTwo(data17, 150) to 4
            }
        }

        @Test
        fun `17,2,live,2`() {
            report {
                Day17.partTwo(data17, 150) to 4
            }
        }
    }
}
