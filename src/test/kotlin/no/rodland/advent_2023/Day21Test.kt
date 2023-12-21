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
internal class Day21Test {
    private val data21 = "2023/input_21.txt".readFile()
    private val test21 = "2023/input_21_test.txt".readFile()

    private val resultTestOne = 2L
    private val resultTestTwo = 2L
    private val resultOne = 2L
    private val resultTwo = 2L

    val test = defaultTestSuiteParseOnInit(
        Day21(data21),
        Day21(test21),
        resultTestOne,
        resultOne,
        resultTestTwo,
        resultTwo,
        { Day21(data21) },
        { Day21(test21) },
    )

    @Nested
    inner class Init {
        @Test
        fun `21,-,example,1`() {
            report(AOCTest({ "123".toInt() }, Unit, 123, 5, "21".toInt(), Part.TWO, false, "example"))
        }

        @Test
        fun `21,-,example,2`() {
            report(test.initTest.copy())
        }

        @Test
        fun `21,-,test,init`() {
            report(test.initTest)
        }

        @Test
        fun `21,-,live,init`() {
            report(test.initLive)
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `21,1,test`() {
            report(test.testPart1)
        }

        @Test
        fun `21,1,live,1`() {
            report(test.livePart1)
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `21,2,test`() {
            report(test.testPart2)
        }

        @Test
        fun `21,2,live,1`() {
            report(test.livePart2)
        }
    }
}
