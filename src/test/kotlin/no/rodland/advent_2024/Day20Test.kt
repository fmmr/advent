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
internal class Day20Test {
    private val data20 = "2024/input_20.txt".readFile()
    private val test20 = "2024/input_20_test.txt".readFile()

    private val resultTestOne = 10   // example: 44  (>0)
    private val resultTestTwo = 2268 // example: 285 (>50)
    private val resultOne = 1502
    private val resultTwo = 1028136

    val test = defaultTestSuiteParseOnInit(
        Day20(data20),
        Day20(test20),
        resultTestOne,
        resultOne,
        resultTestTwo,
        resultTwo,
        { Day20(data20) },
        { Day20(test20) },
        numTestPart1 = 1,
        numTestPart2 = 1,
    )

    @Nested
    inner class Init {
        @Test
        fun `20,-,example,1`() {
            report(AOCTest({ "123".toInt() }, Unit, 123, 5, "20".toInt(), Part.TWO, false, "example"))
        }

        @Test
        fun `20,-,example,2`() {
            report(test.initTest.copy())
        }

        @Test
        fun `20,-,test,init`() {
            report(test.initTest)
        }

        @Test
        fun `20,-,live,init`() {
            report(test.initLive)
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `20,1,test`() {
            report(test.testPart1)
        }

        @Test
        fun `20,1,live,1`() {
            report(test.livePart1)
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `20,2,test`() {
            report(test.testPart2)
        }

        @Test
        fun `20,2,live,1`() {
            report(test.livePart2)
        }
    }
}
