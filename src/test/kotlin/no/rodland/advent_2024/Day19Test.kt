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
internal class Day19Test {
    private val data19 = "2024/input_19.txt".readFile()
    private val test19 = "2024/input_19_test.txt".readFile()

    private val resultTestOne = 2L
    private val resultTestTwo = 2L
    private val resultOne = 2L
    private val resultTwo = 2L

    val test = defaultTestSuiteParseOnInit(
        Day19(data19),
        Day19(test19),
        resultTestOne,
        resultOne,
        resultTestTwo,
        resultTwo,
        { Day19(data19) },
        { Day19(test19) },
    )

    @Nested
    inner class Init {
        @Test
        fun `19,-,example,1`() {
            report(AOCTest({ "123".toInt() }, Unit, 123, 5, "19".toInt(), Part.TWO, false, "example"))
        }

        @Test
        fun `19,-,example,2`() {
            report(test.initTest.copy())
        }

        @Test
        fun `19,-,test,init`() {
            report(test.initTest)
        }

        @Test
        fun `19,-,live,init`() {
            report(test.initLive)
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `19,1,test`() {
            report(test.testPart1)
        }

        @Test
        fun `19,1,live,1`() {
            report(test.livePart1)
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `19,2,test`() {
            report(test.testPart2)
        }

        @Test
        fun `19,2,live,1`() {
            report(test.livePart2)
        }
    }
}
