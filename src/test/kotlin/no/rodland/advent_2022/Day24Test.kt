package no.rodland.advent_2022

import no.rodland.advent.DisableSlow
import no.rodland.advent.Slow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@Suppress("ClassName")
@DisableSlow
internal class Day24Test {
    private val data24 = "2022/input_24.txt".readFile()
    private val test24 = listOf(
        "#.######",
        "#>>.<^<#",
        "#.<..<<#",
        "#>v.><>#",
        "#<^v^^>#",
        "######.#",
    )

    private val day24 = Day24(data24)
    private val day24Test = Day24(test24)

    private val resultTestOne = 18
    private val resultOne = 290
    private val resultTestTwo = 54
    private val resultTwo = 842

    @Nested
    inner class Init {
        @Test
        @Slow(200)
        fun `24,1,live,init`() {
            report {
                day24.partOne() to resultOne
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `24,1,test`() {
            report {
                day24Test.partOne() to resultTestOne
            }
        }

        @Test
        @Slow(200)
        fun `24,1,live,1`() {
            report {
                day24.partOne() to resultOne
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `24,2,test`() {
            report {
                day24Test.partTwo() to resultTestTwo
            }
        }

        @Test
        @Slow(500)
        fun `24,2,live,1`() {
            report {
                day24.partTwo() to resultTwo
            }
        }
    }
}
