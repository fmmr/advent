package no.rodland.advent_2023

import no.rodland.advent.DisableSlow
import no.rodland.advent.defaultTestSuiteParseOnInit
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@Suppress("ClassName", "PrivatePropertyName")
@DisableSlow
internal class Day07Test {
    private val data07 = "2023/input_07.txt".readFile()

    // https://www.reddit.com/r/adventofcode/comments/18cr4xr/2023_day_7_better_example_input_not_a_spoiler/
    private val data07_2 = "2023/input_07_2.txt".readFile()
    private val test07 = listOf(
        "32T3K 765",
        "T55J5 684",
        "KK677 28",
        "KTJJT 220",
        "QQQJA 483",
    )

    private val resultTestOne = 6440L
    private val resultTestTwo = 5905L
    private val resultOne = 252295678L
    private val resultTwo = 250577259L

    val test = defaultTestSuiteParseOnInit(
        Day07(data07),
        Day07(test07),
        resultTestOne,
        resultOne,
        resultTestTwo,
        resultTwo,
        { Day07(data07) },
        { Day07(test07) },
        numTestPart1 = 100,
        numTestPart2 = 100,
    )

    @Nested
    inner class Init {
        @Test
        fun `07,-,test,init`() {
            report(test.initTest)
        }

        @Test
        fun `07,-,live,init`() {
            report(test.initLive)
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `07,1,live,onlyOne`() {
            report(test.livePart1.copy(numTests = 1))
        }

        @Test
        fun `07,1,test`() {
            report(test.testPart1)
        }

        @Test
        fun `07,1,live,1`() {
            report(test.livePart1)
        }
    }

    @Nested
    inner class `Part 2` {


        @Test
        fun `07,2,test`() {
            report(test.testPart2)
        }

        @Test
        fun `07,2,live,1`() {
            report(test.livePart2)
        }
    }

    @Nested
    inner class `Edge Cases` {
        @Test
        fun `07,1,live,edge`() {
            val day = Day07(data07_2)
            report(test.livePart1.copy(function = { day.partOne() }, expected = 4466L))
        }

        @Test
        fun `07,2,live,edge`() {
            val day = Day07(data07_2)
            report(test.livePart2.copy(function = { day.partTwo() }, expected = 4657L))
        }
    }
}
