package no.rodland.advent_2022

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@Suppress("ClassName")
@DisableSlow
internal class Day04Test {
    val data04 = "2022/input_04.txt".readFile()
    val test04 = listOf("1", "2")

    val resultTestOne = 2L
    val resultTestTwo = 2L
    val resultOne = 2L
    val resultTwo = 2L

    @Nested
    inner class Init {
        @Test
        fun `04,1,live,init`() {
            report {
                Day04.partOne(data04) to resultOne
            }
        }

        @Test
        fun `04,2,live,init`() {
            report {
                Day04.partTwo(data04) to resultTwo
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `04,1,test`() {
            report {
                Day04.partOne(test04) to resultTestOne
            }
        }

        @Test
        fun `04,1,live,1`() {
            report {
                Day04.partOne(data04) to resultOne
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `04,2,test`() {
            report {
                Day04.partTwo(test04) to resultTestTwo
            }
        }

        @Test
        fun `04,2,live,1`() {
            report {
                Day04.partTwo(data04) to resultTwo
            }
        }
    }
}
