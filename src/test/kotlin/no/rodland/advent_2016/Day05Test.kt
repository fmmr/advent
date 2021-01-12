package no.rodland.advent_2016

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@Suppress("ClassName")
@DisableSlow
internal class Day05Test {
    val data05 = "abbhdwsy"
    val test05 = "abc"

    @Nested
    inner class Init {
        @Test
        fun `05,1,live,init`() {
            report {
                Day05.partOne(data05) to 2
            }
        }

        @Test
        fun `05,2,live,init`() {
            report {
                Day05.partTwo(data05) to 2
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `05,1,test`() {
            report {
                Day05.partOne(test05) to 2
            }
        }

        @Test
        fun `05,1,live,1`() {
            report {
                Day05.partOne(data05) to 2
            }
        }

        @Test
        fun `05,1,live,2`() {
            report {
                Day05.partOne(data05) to 2
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `05,2,test`() {
            report {
                Day05.partTwo(test05) to 2
            }
        }

        @Test
        fun `05,2,live,1`() {
            report {
                Day05.partTwo(data05) to 2
            }
        }

        @Test
        fun `05,2,live,2`() {
            report {
                Day05.partTwo(data05) to 2
            }
        }
    }
}
