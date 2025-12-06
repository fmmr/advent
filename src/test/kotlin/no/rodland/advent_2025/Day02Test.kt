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
internal class Day02Test {
    private val data02 = "2025/input_02.txt".readFile()
    private val test02 = "2025/input_02_test.txt".readFile()

    private val resultTestOne = 1227775554L
    private val resultTestTwo = 4174379265L
    private val resultOne = 18893502033L
    private val resultTwo = 26202168557L

   val test = defaultTestSuiteParseOnInit(
        Day02(data02),
        Day02(test02),
        resultTestOne,
        resultOne,
        resultTestTwo,
        resultTwo,
        { Day02(data02) },
        { Day02(test02) },
       numTestPart1 = 2,
       numTestPart2 = 1,
    )

    @Nested
    inner class Init {
        @Test
        fun `02,-,example,1`() {
            report(AOCTest({ "123".toInt() }, Unit, 123, 5, "02".toInt(), Part.TWO, false, "example"))
        }

        @Test
        fun `02,-,example,2`() {
            report(test.initTest.copy())
        }

        @Test
        fun `02,-,test,init`() {
            report(test.initTest)
        }

        @Test
        fun `02,-,live,init`() {
            report(test.initLive)
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
        @Slow(600)
        fun `02,2,live,1`() {
            report(test.livePart2)
        }
    }
}
