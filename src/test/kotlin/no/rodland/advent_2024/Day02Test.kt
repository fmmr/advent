package no.rodland.advent_2024

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
    private val data02 = "2024/input_02.txt".readFile()
    private val test02 = "2024/input_02_test.txt".readFile()

    private val resultTestOne = 2L
    private val resultTestTwo = 4L
    private val resultOne = 680L
    private val resultTwo = 710L

    val test = defaultTestSuiteParseOnInit(
        Day02(data02),
        Day02(test02),
        resultTestOne,
        resultOne,
        resultTestTwo,
        resultTwo,
        { Day02(data02) },
        { Day02(test02) },
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
        fun `02,2,live,1`() {
            report(test.livePart2)
        }
    }
}
