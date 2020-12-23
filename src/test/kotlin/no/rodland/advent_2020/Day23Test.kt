package no.rodland.advent_2020

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@Suppress("ClassName")
@DisableSlow
internal class Day23Test {
    val data23 = "394618527"
    val test23 = "389125467"

    @Nested
    inner class Init {
        @Test
        fun `23,1,live,init`() {
            report {
                Day23.partOne(data23) to 2
            }
        }

        @Test
        fun `23,2,live,init`() {
            report {
                Day23.partTwo(data23) to 2
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `23,1,test`() {
            report {
                Day23.partOne(test23) to 2
            }
        }

        @Test
        fun `23,1,live,1`() {
            report {
                Day23.partOne(data23) to 2
            }
        }

        @Test
        fun `23,1,live,2`() {
            report {
                Day23.partOne(data23) to 2
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `23,2,test`() {
            report {
                Day23.partTwo(test23) to 2
            }
        }

        @Test
        fun `23,2,live,1`() {
            report {
                Day23.partTwo(data23) to 2
            }
        }

        @Test
        fun `23,2,live,2`() {
            report {
                Day23.partTwo(data23) to 2
            }
        }
    }
}
