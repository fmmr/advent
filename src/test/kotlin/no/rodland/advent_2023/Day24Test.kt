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
internal class Day24Test {
    private val data24 = "2023/input_24.txt".readFile()
    private val test24 = "2023/input_24_test.txt".readFile()

    private val resultTestOne = 2
    private val resultTestTwo = 47L
    private val resultOne = 23760
    private val resultTwo = 888708704663413L

    val test = defaultTestSuiteParseOnInit(
        Day24(data24),
        Day24(test24),
        resultTestOne,
        resultOne,
        resultTestTwo,
        resultTwo,
        { Day24(data24) },
        { Day24(test24) },
        numTestPart1 = 10,
        numTestPart2 = 1,
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
        @Slow(500)
        fun `24,2,live,1`() {
            report(test.livePart2)
        }
    }
}
