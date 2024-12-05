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
internal class Day05Test {
    private val data05 = "2024/input_05.txt".readFile()
    private val test05 = "2024/input_05_test.txt".readFile()

    private val resultTestOne = 143
    private val resultTestTwo = 2L
    private val resultOne = 5762
    private val resultTwo = 2L

    val test = defaultTestSuiteParseOnInit(
        Day05(data05),
        Day05(test05),
        resultTestOne,
        resultOne,
        resultTestTwo,
        resultTwo,
        { Day05(data05) },
        { Day05(test05) },
        numTestPart1 = 10,
        numTestPart2 = 10,
    )

    @Nested
    inner class Init {
        @Test
        fun `05,-,example,1`() {
            report(AOCTest({ "123".toInt() }, Unit, 123, 5, "05".toInt(), Part.TWO, false, "example"))
        }

        @Test
        fun `05,-,example,2`() {
            report(test.initTest.copy())
        }

        @Test
        fun `05,-,test,init`() {
            report(test.initTest)
        }

        @Test
        fun `05,-,live,init`() {
            report(test.initLive)
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `05,1,test`() {
            report(test.testPart1)
        }

        @Test
        fun `05,1,live,1`() {
            report(test.livePart1)
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `05,2,test`() {
            report(test.testPart2)
        }

        @Test
        fun `05,2,live,1`() {
            report(test.livePart2)
        }
    }
}
