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
internal class Day18Test {
    private val data18 = "2023/input_18.txt".readFile()
    private val test18 = "2023/input_18_test.txt".readFile()

    private val resultTestOne = 62L
    private val resultTestTwo = 952408144115L
    private val resultOne = 40714L
    private val resultTwo = 129849166997110L

    val test = defaultTestSuiteParseOnInit(
        Day18(data18),
        Day18(test18),
        resultTestOne,
        resultOne,
        resultTestTwo,
        resultTwo,
        { Day18(data18) },
        { Day18(test18) },
        numTestPart1 = 1000,
        numTestPart2 = 1000,
    )

    @Nested
    inner class Init {
        @Test
        fun `18,-,example,1`() {
            report(AOCTest({ "123".toInt() }, Unit, 123, 5, "18".toInt(), Part.TWO, false, "example"))
        }

        @Test
        fun `18,-,example,2`() {
            report(test.initTest.copy())
        }

        @Test
        fun `18,-,test,init`() {
            report(test.initTest)
        }

        @Test
        fun `18,-,live,init`() {
            report(test.initLive)
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `18,1,test`() {
            report(test.testPart1)
        }

        @Test
        fun `18,1,live,1`() {
            report(test.livePart1)
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `18,2,test`() {
            report(test.testPart2)
        }

        @Test
        fun `18,2,live,1`() {
            report(test.livePart2)
        }
    }
}
