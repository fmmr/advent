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
internal class Day16Test {
    private val data16 = "2024/input_16.txt".readFile()
    private val test16 = "2024/input_16_test.txt".readFile()

    private val resultTestOne = 2L
    private val resultTestTwo = 2L
    private val resultOne = 2L
    private val resultTwo = 2L

    val test = defaultTestSuiteParseOnInit(
        Day16(data16),
        Day16(test16),
        resultTestOne,
        resultOne,
        resultTestTwo,
        resultTwo,
        { Day16(data16) },
        { Day16(test16) },
    )

    @Nested
    inner class Init {
        @Test
        fun `16,-,example,1`() {
            report(AOCTest({ "123".toInt() }, Unit, 123, 5, "16".toInt(), Part.TWO, false, "example"))
        }

        @Test
        fun `16,-,example,2`() {
            report(test.initTest.copy())
        }

        @Test
        fun `16,-,test,init`() {
            report(test.initTest)
        }

        @Test
        fun `16,-,live,init`() {
            report(test.initLive)
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `16,1,test`() {
            report(test.testPart1)
        }

        @Test
        fun `16,1,live,1`() {
            report(test.livePart1)
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `16,2,test`() {
            report(test.testPart2)
        }

        @Test
        fun `16,2,live,1`() {
            report(test.livePart2)
        }
    }
}
