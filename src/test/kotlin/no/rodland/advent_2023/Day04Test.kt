package no.rodland.advent_2023

import no.rodland.advent.DisableSlow
import no.rodland.advent.defaultTestSuiteParseOnInit
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@Suppress("ClassName")
@DisableSlow
internal class Day04Test {
    private val data04 = "2023/input_04.txt".readFile()
    private val test04 = listOf(
        "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53",
        "Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19",
        "Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1",
        "Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83",
        "Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36",
        "Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11",
    )

    private val resultTestOne = 13L
    private val resultTestTwo = 30L
    private val resultOne = 25571L
    private val resultTwo = 8805731L

    val test = defaultTestSuiteParseOnInit(Day04(data04), Day04(test04), resultTestOne, resultOne, resultTestTwo, resultTwo, numTestPart2 = 6)

    @Nested
    inner class Init {
        @Test
        fun `04,1,live,init`() {
            report(test.initPart1)
        }

        @Test
        fun `04,2,live,init`() {
            report(test.initPart2)
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `04,1,test`() {
            report(test.testPart1)
        }

        @Test
        fun `04,1,live,1`() {
            report(test.livePart1)
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `04,2,test`() {
            report(test.testPart2)
        }

        @Test
        fun `04,2,live,1`() {
            report(test.livePart2)
        }
    }
}
