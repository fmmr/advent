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
internal class Day12Test {
    private val data12 = "2023/input_12.txt".readFile()
    private val test12 = "2023/input_12_test.txt".readFile()

    private val resultTestOne = 21
    private val resultTestTwo = 2L
    private val resultOne = 7163
    private val resultTwo = 2L

    val test = defaultTestSuiteParseOnInit(
        Day12(data12),
        Day12(test12),
        resultTestOne,
        resultOne,
        resultTestTwo,
        resultTwo,
        { Day12(data12) },
        { Day12(test12) },
        numTestPart1 = 1,
        numTestPart2 = 1,
    )

    @Nested
    inner class Parsing {
        val day = Day12(emptyList())
        val test1 = AOCTest({ day.expand("") }, Unit, listOf(""), 1, "12".toInt(), Part.ONE, false, "parsing")

        @Test
        fun `12,-,example,expand_empty`() {
            report(test1)
        }

        @Test
        fun `12,-,example,expand_question`() {
            test("?", listOf(".", "#"))
        }

        @Test
        fun `12,-,example,expand_2_question`() {
            test("??", listOf("..", ".#", "#.", "##"))
        }

        @Test
        fun `12,-,example,expand_2_question_with_hash`() {
            test("?#?", listOf(".#.", ".##", "##.", "###"))
            test("#??", listOf("#..", "#.#", "##.", "###"))
            test("??#", listOf("..#", ".##", "#.#", "###"))
        }

        @Test
        fun `12,-,example,expand_3_question`() {
            test("???", listOf("...", "..#", ".#.", ".##", "#..", "#.#", "##.", "###"))
        }

        @Test
        fun `12,-,example,expand_dot`() {
            test(".", listOf("."))
        }

        @Test
        fun `12,-,example,expand_hash`() {
            test("#", listOf("#"))
        }

        private fun test(input: String, expected: List<String>) {
            report(test1.copy(function = { day.expand(input) }, expected = expected))
        }

    }

    @Nested
    inner class Init {

        @Test
        fun `12,-,example,2`() {
            report(test.initTest.copy())
        }

        @Test
        fun `12,-,test,init`() {
            report(test.initTest)
        }

        @Test
        fun `12,-,live,init`() {
            report(test.initLive)
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `12,1,test`() {
            report(test.testPart1)
        }

        @Test
        @Slow(10000)
        fun `12,1,live,1`() {
            report(test.livePart1)
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `12,2,test`() {
            report(test.testPart2)
        }

        @Test
        fun `12,2,live,1`() {
            report(test.livePart2)
        }
    }
}
