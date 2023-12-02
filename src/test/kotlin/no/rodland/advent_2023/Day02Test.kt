package no.rodland.advent_2023

import no.rodland.advent.DisableSlow
import no.rodland.advent.defaultTestSuiteParseOnInit
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@Suppress("ClassName")
@DisableSlow
internal class Day02Test {
    private val data02 = "2023/input_02.txt".readFile()
    private val test02 = listOf(
        "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green",
        "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue",
        "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red",
        "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red",
        "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green",
    )

    private val resultTestOne = 8
    private val resultTestTwo = 2286
    private val resultOne = 2545
    private val resultTwo = 78111

    val test = defaultTestSuiteParseOnInit(Day02(data02), Day02(test02), resultTestOne, resultOne, resultTestTwo, resultTwo)

    @Nested
    inner class Init {
        @Test
        fun `02,1,live,init`() {
            report(test.initPart1)
        }

        @Test
        fun `02,2,live,init`() {
            report(test.initPart2)
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `02,1,test`() {
            report(test.testPart1)
        }

        @Test
        fun `02,1,live,1`() {
            report(test.livePart1)
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `02,2,test`() {
            report(test.testPart2)
        }

        @Test
        fun `02,2,live,1`() {
            report(test.livePart2)
        }
    }
}
