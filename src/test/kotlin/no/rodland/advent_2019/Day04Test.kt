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
                1234454.hasTwoAdjacent() to true
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
                1234254.hasTwoAdjacent() to false
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `04,2,hasTwoAdjecentNotPartOfLargerGroup,1`() {
            report {
                112233.digits().groupBy { it }.entries.any { it.value.size == 2 } to true
            }
        }

        @Test
        fun `04,2,hasTwoAdjecentNotPartOfLargerGroup,2`() {
            report {
                123444.digits().groupBy { it }.entries.any { it.value.size == 2 } to false
            }
        }

        @Test
        fun `04,2,hasTwoAdjecentNotPartOfLargerGroup,3`() {
            report {
                111122.digits().groupBy { it }.entries.any { it.value.size == 2 } to true
            }
        }

        @Test
        fun `04,2,hasTwoAdjecentNotPartOfLargerGroup,4`() {
            report {
                22124424.digits().groupBy { it }.entries.any { it.value.size == 2 } to false
            }
        }

        @Test
        fun `04,2,live`() {
            report {
                Day04.partTwo(input) to 1180
            }
        }
    }
}


