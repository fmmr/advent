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
internal class Day14Test {
    private val data14 = "2023/input_14.txt".readFile()
    private val test14 = "2023/input_14_test.txt".readFile()

    private val resultTestOne = 136
    private val resultTestTwo = 2L
    private val resultOne = 108144
    private val resultTwo = 2L

    val test = defaultTestSuiteParseOnInit(
        Day14(data14),
        Day14(test14),
        resultTestOne,
        resultOne,
        resultTestTwo,
        resultTwo,
        { Day14(data14) },
        { Day14(test14) },
    )

    @Nested
    inner class Init {
        @Test
        fun `14,-,example,1`() {
            report(AOCTest({ "123".toInt() }, Unit, 123, 5, "14".toInt(), Part.TWO, false, "example"))
        }

        @Test
        fun `14,-,example,2`() {
            report(test.initTest.copy())
        }

        @Test
        fun `14,-,test,init`() {
            report(test.initTest)
        }

        @Test
        fun `14,-,live,init`() {
            report(test.initLive)
        }
    }


    @Nested
    inner class Parsing {
        private val day = Day14(listOf(""))
        private val test1 = AOCTest({ day.collapsed("") }, Unit, listOf(""), 1, "12".toInt(), Part.ONE, false, "collapsing")

        @Test
        fun `12,-,example,collapse_1`() {
            test("OO.OO", "OOOO.")
        }

        @Test
        fun `12,-,example,collapse_2`() {
            test(".O", "O.")
        }

        private fun test(input: String, expected: String) {
            report(test1.copy(function = { day.collapsed(input) }, expected = expected))
        }

    }


    @Nested
    inner class `Part 1` {
        @Test
        fun `14,1,test`() {
            report(test.testPart1)
        }

        @Test
        fun `14,1,live,1`() {
            report(test.livePart1)
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `14,2,test`() {
            report(test.testPart2)
        }

        @Test
        fun `14,2,live,1`() {
            report(test.livePart2)
        }
    }
}
