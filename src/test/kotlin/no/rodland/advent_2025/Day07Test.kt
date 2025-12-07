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
internal class Day07Test {
    private val data07 = "2025/input_07.txt".readFile()
    private val test07 = "2025/input_07_test.txt".readFile()

    private val resultTestOne = 21L
    private val resultTestTwo = 40L
    private val resultOne = 1573L
    private val resultTwo = 15093663987272L

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
        numTestPart2 = 100
    )

    @Nested
    inner class Init {
        @Test
        fun `07,-,example,1`() {
            report(AOCTest({ "123".toInt() }, Unit, 123, 5, "07".toInt(), Part.TWO, false, "example"))
        }

        @Test
        fun `07,-,example,2`() {
            report(test.initTest.copy())
        }

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
}
