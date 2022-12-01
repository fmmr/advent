package no.rodland.advent_2022

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@Suppress("ClassName")
@DisableSlow
internal class Day03Test {
    val data03 = "2022/input_03.txt".readFile()
    val test03 = listOf("1", "2")

    val resultTestOne = 2L
    val resultOne = 2L

    val resultTestTwo = 2L
    val resultTwo = 2L

    @Nested
    inner class Init {
        @Test
        fun `03,1,live,init`() {
            report {
                Day03.partOne(data03) to resultOne
            }
        }

        @Test
        fun `03,2,live,init`() {
            report {
                Day03.partTwo(data03) to resultTwo
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `03,1,test`() {
            report {
                Day03.partOne(test03) to resultTestOne
            }
        }

        @Test
        fun `03,1,live,1`() {
            report {
                Day03.partOne(data03) to resultOne
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `03,2,test`() {
            report {
                Day03.partTwo(test03) to resultTestTwo
            }
        }

        @Test
        fun `03,2,live,1`() {
            report {
                Day03.partTwo(data03) to resultTwo
            }
        }
    }
}
