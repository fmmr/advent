package no.rodland.advent_2023

import no.rodland.advent.DisableSlow
import no.rodland.advent.defaultTestSuiteParseOnInit
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@Suppress("ClassName")
@DisableSlow
internal class Day03Test {
    private val data03 = "2023/input_03.txt".readFile()
    private val test03 = listOf(
        "467..114..",
        "...*......",
        "..35..633.",
        "......#...",
        "617*......",
        ".....+.58.",
        "..592.....",
        "......755.",
        "...$.*....",
        ".664.598..",
        )

    private val resultTestOne = 4361
    private val resultTestTwo = 467835
    private val resultOne = 533775
    private val resultTwo = 78236071

    val test = defaultTestSuiteParseOnInit(Day03(data03), Day03(test03), resultTestOne, resultOne, resultTestTwo, resultTwo, numTestPart1 = 5, numTestPart2 = 5)

    @Nested
    inner class Init {
        @Test
        fun `03,1,live,init`() {
            report(test.initPart1)
        }

        @Test
        fun `03,2,live,init`() {
            report(test.initPart2)
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `03,1,test`() {
            report(test.testPart1)
        }

        @Test
        fun `03,1,live,1`() {
            report(test.livePart1)
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `03,2,test`() {
            report(test.testPart2)
        }

        @Test
        fun `03,2,live,1`() {
            report(test.livePart2)
        }
    }
}
