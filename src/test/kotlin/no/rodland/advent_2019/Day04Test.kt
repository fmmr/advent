package no.rodland.advent_2019

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisableSlow
internal class Day04Test {
    val test04 = listOf("1", "2")
    val input = 146810 to 612564

    @Nested
    inner class `Part 1` {
        @Test
        fun `04,1,live`() {
            report {
                Day04.partOne(input) to 1748
            }
        }

        @Test
        fun `04,1,adjacent,1`() {
            report {
                1234454.twoAdjacentEqual() to true
            }
        }

        @Test
        fun `04,1,decrease,1`() {
            report {
                12345678.neverDecrease() to true
            }
        }

        @Test
        fun `04,1,decrease,2`() {
            report {
                123446788.neverDecrease() to true
            }
        }

        @Test
        fun `04,1,decrease,3`() {
            report {
                123446768.neverDecrease() to false
            }
        }

        @Test
        fun `04,1,adjacent,2`() {
            report {
                1234254.twoAdjacentEqual() to false
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `04,2,test`() {
            report {
                Day04.partTwo(test04) to 2
            }
        }

        @Test
        fun `04,2,live`() {
            report {
                Day04.partTwo(test04) to 2
            }
        }
    }
}


