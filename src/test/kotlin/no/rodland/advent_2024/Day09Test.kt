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
internal class Day09Test {
    private val data09 = "2024/input_09.txt".readFile()
    private val test09 = "2024/input_09_test.txt".readFile()

    private val resultTestOne = 1928L
    private val resultTestTwo = 2L
    private val resultOne = 6421128769094L
    private val resultTwo = 2L

    val test = defaultTestSuiteParseOnInit(
        Day09(data09),
        Day09(test09),
        resultTestOne,
        resultOne,
        resultTestTwo,
        resultTwo,
        { Day09(data09) },
        { Day09(test09) },
        numTestPart1 = 200
    )

    @Nested
    inner class Init {
        @Test
        fun `09,-,example,1`() {
            report(AOCTest({ "123".toInt() }, Unit, 123, 5, "09".toInt(), Part.TWO, false, "example"))
        }

        @Test
        fun `09,-,example,2`() {
            report(test.initTest.copy())
        }

        @Test
        fun `09,-,test,init`() {
            report(test.initTest)
        }

        @Test
        fun `09,-,live,init`() {
            report(test.initLive)
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `09,1,test`() {
            report(test.testPart1)
        }

        @Test
        fun `09,1,live,1`() {
            report(test.livePart1)
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `09,2,test`() {
            report(test.testPart2)
        }

        @Test
        fun `09,2,live,1`() {
            report(test.livePart2)
        }
    }
}
