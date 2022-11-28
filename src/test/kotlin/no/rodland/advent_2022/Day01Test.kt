package no.rodland.advent_2022

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@Suppress("ClassName")
@DisableSlow
internal class Day01Test {
    val data01 = "2022/input_01.txt".readFile()
    val test01 = listOf("1", "2")

    val resultTestOne = 2L
    val resultTestTwo = 2L
    val resultOne = 2L
    val resultTwo = 2L

    @Nested
    inner class Init {
        @Test
        fun `01,1,live,init`() {
            report {
                Day01.partOne(data01) to resultOne
            }
        }

        @Test
        fun `01,2,live,init`() {
            report {
                Day01.partTwo(data01) to resultTwo
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `01,1,test`() {
            report {
                Day01.partOne(test01) to resultTestOne
            }
        }

        @Test
        fun `01,1,live,1`() {
            report {
                Day01.partOne(data01) to resultOne
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `01,2,test`() {
            report {
                Day01.partTwo(test01) to resultTestTwo
            }
        }

        @Test
        fun `01,2,live,1`() {
            report {
                Day01.partTwo(data01) to resultTwo
            }
        }
    }
}
