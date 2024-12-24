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
internal class Day24Test {
    private val data24 = "2024/input_24.txt".readFile()
    private val test24 = "2024/input_24_test.txt".readFile()

    private val resultTestOne = 2024L
    private val resultTestTwo = 2L
    private val resultOne = 57344080719736L  // 8510417647883 too low
    private val resultTwo = 2L

    val test = defaultTestSuiteParseOnInit(
        Day24(data24),
        Day24(test24),
        resultTestOne,
        resultOne,
        resultTestTwo,
        resultTwo,
        { Day24(data24) },
        { Day24(test24) },
        numTestPart1 = 1
    )

    @Nested
    inner class Init {
        @Test
        fun `24,-,example,1`() {
            report(AOCTest({ "123".toInt() }, Unit, 123, 5, "24".toInt(), Part.TWO, false, "example"))
        }

        @Test
        fun `24,-,example,2`() {
            report(test.initTest.copy())
        }

        @Test
        fun `24,-,test,init`() {
            report(test.initTest)
        }

        @Test
        fun `24,-,live,init`() {
            report(test.initLive)
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `24,1,test`() {
            report(test.testPart1)
        }

        @Test
        fun `24,1,live,1`() {
            report(test.livePart1)
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `24,2,test`() {
            report(test.testPart2)
        }

        @Test
        fun `24,2,live,1`() {
            report(test.livePart2)
        }
    }
}
