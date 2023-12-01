package no.rodland.advent_2023

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@Suppress("ClassName", "PrivatePropertyName")
@DisableSlow
internal class Day01Test {
    private val data25 = "2023/input_01.txt".readFile()
    private val test25 = listOf(
        "1abc2",
        "pqr3stu8vwx",
        "a1b2c3d4e5f",
        "treb7uchet"
    )
    private val test25_2 = listOf(
        "two1nine",
        "eightwothree",
        "abcone2threexyz",
        "xtwone3four",
        "4nineeightseven2",
        "zoneight234",
        "7pqrstsixteen",
    )

    private val day25 = Day01(data25)
    private val day25Test = Day01(test25)
    private val day25Test_2 = Day01(test25_2)

    private val resultTestOne = 142L
    private val resultTestTwo = 281L
    private val resultOne = 54877L
    private val resultTwo = 54100L


    @Nested
    inner class `Part 1` {
        @Test
        fun `25,1,test`() {
            report {
                day25Test.partOne() to resultTestOne
            }
        }

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
        fun `25,2,test,1`() {
            report {
                day25Test_2.partTwo() to resultTestTwo
            }
        }

        @Test
        fun `25,2,test,2`() {
            report {
                day25Test_2.fix("243") to 23
            }
        }

        @Test
        fun `25,2,test,3`() {
            report {
                day25Test_2.fix("two4three") to 23
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
