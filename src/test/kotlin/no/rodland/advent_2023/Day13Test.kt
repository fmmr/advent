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
internal class Day13Test {
    private val data13 = "2023/input_13.txt".readFile()
    private val test13 = "2023/input_13_test.txt".readFile()

    private val resultTestOne = 405
    private val resultTestTwo = 400
    private val resultOne = 40006
    private val resultTwo = 28627

    val test = defaultTestSuiteParseOnInit(
        Day13(data13),
        Day13(test13),
        resultTestOne,
        resultOne,
        resultTestTwo,
        resultTwo,
        { Day13(data13) },
        { Day13(test13) },
        numTestPart1 = 300,
        numTestPart2 = 300
    )

    @Nested
    inner class Init {
        @Test
        fun `13,-,example,1`() {
            report(AOCTest({ "123".toInt() }, Unit, 123, 5, "13".toInt(), Part.TWO, false, "example"))
        }

        @Test
        fun `13,-,example,2`() {
            report(test.initTest.copy())
        }

        @Test
        fun `13,-,test,init`() {
            report(test.initTest)
        }

        @Test
        fun `13,-,live,init`() {
            report(test.initLive)
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `13,1,test`() {
            report(test.testPart1)
        }

        @Test
        fun `13,1,live,1`() {
            report(test.livePart1)
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `13,2,test`() {
            report(test.testPart2)
        }

        @Test
        fun `13,2,live,1`() {
            report(test.livePart2)
        }
    }
}
