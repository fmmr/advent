package no.rodland.advent_2022

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFileAsString

@Suppress("ClassName")
@DisableSlow
internal class Day17Test {
    val data17 = "2022/input_17.txt".readFileAsString()
    val test17 = ">>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>"
    val resultTestOne = 3068L
    val resultOne = 3186L

    val resultTestTwo = 1514285714288L
    val resultTwo = 1566376811584L

    @Nested
    inner class Init {
        @Test
        fun `17,1,live,init`() {
            report {
                Day17.partOne(data17) to resultOne
            }
        }

        @Test
        fun `17,2,live,init`() {
            report {
                Day17.partTwo(data17) to resultTwo
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `17,1,test`() {
            report {
                Day17.partOne(test17) to resultTestOne
            }
        }

        @Test
        fun `17,1,live,1`() {
            report {
                Day17.partOne(data17) to resultOne
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `17,2,test`() {
            report {
                Day17.partTwo(test17) to resultTestTwo
            }
        }

        @Test
        fun `17,2,live,1`() {
            report {
                Day17.partTwo(data17) to resultTwo
            }
        }
    }
}
