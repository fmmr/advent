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
internal class Day01Test {
    private val data01 = "2024/input_01.txt".readFile()
    private val test01 = "2024/input_01_test.txt".readFile()

    private val resultTestOne = 2L
    private val resultTestTwo = 2L
    private val resultOne = 2L
    private val resultTwo = 2L

    val test = defaultTestSuiteParseOnInit(
        Day01(data01),
        Day01(test01),
        resultTestOne,
        resultOne,
        resultTestTwo,
        resultTwo,
        { Day01(data01) },
        { Day01(test01) },
    )

    @Nested
    inner class Init {
        @Test
        fun `01,-,example,1`() {
            report(AOCTest({ "123".toInt() }, Unit, 123, 5, "01".toInt(), Part.TWO, false, "example"))
        }

        @Test
        fun `01,-,example,2`() {
            report(test.initTest.copy())
        }

        @Test
        fun `01,-,test,init`() {
            report(test.initTest)
        }

        @Test
        fun `01,-,live,init`() {
            report(test.initLive)
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `01,1,test`() {
            report(test.testPart1)
        }

        @Test
        fun `01,1,live,1`() {
            report(test.livePart1)
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `01,2,test`() {
            report(test.testPart2)
        }

        @Test
        fun `01,2,live,1`() {
            report(test.livePart2)
        }
    }
}
