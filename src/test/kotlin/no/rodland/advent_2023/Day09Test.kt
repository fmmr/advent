package no.rodland.advent_2023

import no.rodland.advent.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@Suppress("ClassName")
@DisableSlow
internal class Day09Test {
    private val data09 = "2023/input_09.txt".readFile()
    private val test09 = listOf(
        "0 3 6 9 12 15",
        "1 3 6 10 15 21",
        "10 13 16 21 30 45",
    )

    private val resultTestOne = 114
    private val resultTestTwo = 2
    private val resultOne = 1972648895
    private val resultTwo = 919

    val test = defaultTestSuiteParseOnInit(
        Day09(data09),
        Day09(test09),
        resultTestOne,
        resultOne,
        resultTestTwo,
        resultTwo,
        { Day09(data09) },
        { Day09(test09) },
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
