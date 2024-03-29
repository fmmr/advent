package no.rodland.advent_2022

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@Suppress("ClassName")
@DisableSlow
internal class Day25Test {
    private val data25 = "2022/input_25.txt".readFile()
    private val test25 = listOf("1", "2")

    private val day25 = Day25(data25)
    private val day25Test = Day25(test25)

    private val resultTestOne = 2L
    private val resultTestTwo = 2L
    private val resultOne = "20=212=1-12=200=00-1"
    private val resultTwo = 2L


    @Nested
    inner class `Part 1` {
//        @Test
//        fun `25,1,test`() {
//            report {
//                day25Test.partOne() to resultTestOne
//            }
//        }

        @Test
        fun `25,1,live,1`() {
            report {
                day25.partOne() to resultOne
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `25,2,test`() {
            report {
                day25Test.partTwo() to resultTestTwo
            }
        }

        @Test
        fun `25,2,live,1`() {
            report {
                day25.partTwo() to resultTwo
            }
        }
    }
}
