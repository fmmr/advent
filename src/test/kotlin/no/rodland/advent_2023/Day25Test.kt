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
internal class Day25Test {
    private val data25 = "2023/input_25.txt".readFile()
    private val test25 = "2023/input_25_test.txt".readFile()

    private val resultTestOne = 54L
    private val resultTestTwo = 2L
    private val resultOne = 602151L
    private val resultTwo = 2L

    val test = defaultTestSuiteParseOnInit(
        Day25(data25),
        Day25(test25),
        resultTestOne,
        resultOne,
        resultTestTwo,
        resultTwo,
        { Day25(data25) },
        { Day25(test25) },
        numTestPart1 = 10,
    )

    @Nested
    inner class Init {
        @Test
        fun `25,-,example,1`() {
            report(AOCTest({ "123".toInt() }, Unit, 123, 5, "25".toInt(), Part.TWO, false, "example"))
        }

        @Test
        fun `25,-,example,2`() {
            report(test.initTest.copy())
        }

        @Test
        fun `25,-,test,init`() {
            report(test.initTest)
        }

        @Test
        fun `25,-,live,init`() {
            report(test.initLive)
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `25,1,test`() {
            report(test.testPart1)
        }

        @Test
        fun `25,1,live,1`() {
            report(test.livePart1)
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `25,2,test`() {
            report(test.testPart2)
        }

        @Test
        fun `25,2,live,1`() {
            report(test.livePart2)
        }
    }
}
