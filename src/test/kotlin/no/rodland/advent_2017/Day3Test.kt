package no.rodland.advent_2017

import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class Day3Test {

    @Nested
    inner class `Part 1` {
        @Test
        fun `2,1,test`() {
            report {
                Day3.partOne(1) to 0
            }
        }

        @Test
        fun `2,1,test2`() {
            report {
                Day3.partOne(12) to 3
            }
        }

        @Test
        fun `2,1,test3`() {
            report {
                Day3.partOne(23) to 2
            }
        }

        @Test
        fun `2,1,test4`() {
            report {
                Day3.partOne(1024) to 31
            }
        }

        @Test
        fun `2,1,test5`() {
            report {
                Day3.partOne(347991) to 480
            }
        }
    }

    @Nested
    inner class `Part 2` {
    }
}