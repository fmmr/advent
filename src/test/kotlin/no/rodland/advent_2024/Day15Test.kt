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
internal class Day15Test {
    private val data15 = "2024/input_15.txt".readFile()
    private val test15 = "2024/input_15_test.txt".readFile()

    private val resultTestOne = 10092
    private val resultTestTwo = 9021
    private val resultOne = 1552879
    private val resultTwo = 1561175

    val test = defaultTestSuiteParseOnInit(
        Day15(data15),
        Day15(test15),
        resultTestOne,
        resultOne,
        resultTestTwo,
        resultTwo,
        { Day15(data15) },
        { Day15(test15) },
        numTestPart1 = 10,
        numTestPart2 = 10
    )

    @Nested
    inner class Init {
        @Test
        fun `15,-,example,1`() {
            report(AOCTest({ "123".toInt() }, Unit, 123, 5, "15".toInt(), Part.TWO, false, "example"))
        }

        @Test
        fun `15,-,example,2`() {
            report(test.initTest.copy())
        }

        @Test
        fun `15,-,test,init`() {
            report(test.initTest)
        }

        @Test
        fun `15,-,live,init`() {
            report(test.initLive)
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `15,1,test`() {
            report(test.testPart1)
        }

        @Test
        @Slow(600)
        fun `15,1,live,1`() {
            report(test.livePart1)
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `15,2,test`() {
            report(test.testPart2)
        }

        @Test
        fun `15,2,live,1`() {
            report(test.livePart2)
        }
    }
}
