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
internal class Day06Test {
    private val data06 = "2025/input_06.txt".readFile()
    private val test06 = "2025/input_06_test.txt".readFile()

    private val resultTestOne = 4277556L
    private val resultTestTwo = 2L
    private val resultOne = 5381996914800L
    private val resultTwo = 2L

    val test = defaultTestSuiteParseOnInit(
        Day06(data06),
        Day06(test06),
        resultTestOne,
        resultOne,
        resultTestTwo,
        resultTwo,
        { Day06(data06) },
        { Day06(test06) },
    )

    @Nested
    inner class Init {
        @Test
        fun `06,-,example,1`() {
            report(AOCTest({ "123".toInt() }, Unit, 123, 5, "06".toInt(), Part.TWO, false, "example"))
        }

        @Test
        fun `06,-,example,2`() {
            report(test.initTest.copy())
        }

        @Test
        fun `06,-,test,init`() {
            report(test.initTest)
        }

        @Test
        fun `06,-,live,init`() {
            report(test.initLive)
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `06,1,test`() {
            report(test.testPart1)
        }

        @Test
        fun `06,1,live,1`() {
            report(test.livePart1)
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `06,2,test`() {
            report(test.testPart2)
        }

        @Test
        fun `06,2,live,1`() {
            report(test.livePart2)
        }
    }
}
