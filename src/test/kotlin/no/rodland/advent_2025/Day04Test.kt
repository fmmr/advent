package no.rodland.advent_2025

import no.rodland.advent.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

//
//  run: download_aoc_input.sh to download input
//

@Suppress("ClassName")
@DisableSlow
internal class Day04Test {
    private val data04 = "2025/input_04.txt".readFile()
    private val test04 = "2025/input_04_test.txt".readFile()

    private val resultTestOne = 13L
    private val resultTestTwo = 43L
    private val resultOne = 1395L
    private val resultTwo = 8451L

    val test = defaultTestSuiteParseOnInit(
        Day04(data04),
        Day04(test04),
        resultTestOne,
        resultOne,
        resultTestTwo,
        resultTwo,
        { Day04(data04) },
        { Day04(test04) },
        numTestPart1 = 2,
        numTestPart2 = 2,
    )

    @Nested
    inner class Init {
        @Test
        fun `04,-,example,1`() {
            report(AOCTest({ "123".toInt() }, Unit, 123, 5, "04".toInt(), Part.TWO, false, "example"))
        }

        @Test
        fun `04,-,example,2`() {
            report(test.initTest.copy())
        }

        @Test
        fun `04,-,test,init`() {
            report(test.initTest)
        }

        @Test
        fun `04,-,live,init`() {
            report(test.initLive)
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
