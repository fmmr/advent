package no.rodland.advent_2023

import no.rodland.advent.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

//
//  run: download_aoc_input.sh to download input
//

@Suppress("ClassName")
@DisableSlow
internal class Day17Test {
    private val data17 = "2023/input_17.txt".readFile()
    private val test17 = "2023/input_17_test.txt".readFile()
    private val test17_2 = "2023/input_17_test_2.txt".readFile()

    private val resultTestOne = 102
    private val resultTestTwo = 94
    private val resultTestTwoTwo = 71
    private val resultOne = 791
    private val resultTwo = 900

    val test = defaultTestSuiteParseOnInit(
        Day17(data17),
        Day17(test17),
        resultTestOne,
        resultOne,
        resultTestTwo,
        resultTwo,
        { Day17(data17) },
        { Day17(test17) },
        numTestPart1 = 1,
        numTestPart2 = 1,
    )

    @Nested
    inner class Init {
        @Test
        fun `17,-,example,1`() {
            report(AOCTest({ "123".toInt() }, Unit, 123, 5, "17".toInt(), Part.TWO, false, "example"))
        }

        @Test
        fun `17,-,example,2`() {
            report(test.initTest.copy())
        }

        @Test
        fun `17,-,test,init`() {
            report(test.initTest)
        }

        @Test
        fun `17,-,live,init`() {
            report(test.initLive)
        }
    }

    @Nested
    inner class Tests {
        @Test
        fun `17,1,test`() {
            report(test.testPart1)
        }

        @Test
        fun `17,2,test`() {
            report(test.testPart2)
        }

        @Test
        fun `17,2,test,2`() {
            report(test.testPart2.copy(function = { Day17(test17_2).partTwo() }, expected = resultTestTwoTwo))
        }

    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `17,1,test`() {
            report(test.testPart1)
        }

        @Test
        fun `17,1,live,1`() {
            report(test.livePart1)
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `17,2,test`() {
            report(test.testPart2)
        }

        @Test
        fun `17,2,test,2`() {
            report(test.testPart2.copy(function = { Day17(test17_2).partTwo() }, expected = 71))
        }

        @Test
        @Slow(888)
        fun `17,2,live,1`() {
            report(test.livePart2)
        }
    }
}
