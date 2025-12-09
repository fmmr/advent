package no.rodland.advent_2025

import no.rodland.advent.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

//
//  run: download_aoc_input.sh to download input
//

@Suppress("ClassName")
@DisableSlow
internal class Day08Test {
    private val data08 = "2025/input_08.txt".readFile()
    private val test08 = "2025/input_08_test.txt".readFile()

    private val resultTestOne = 40L
    private val resultTestTwo = 2L
    private val resultOne = 122636L
    private val resultTwo = 2L

    val test = defaultTestSuiteParseOnInit(
        Day08(data08),
        Day08(test08),
        resultTestOne,
        resultOne,
        resultTestTwo,
        resultTwo,
        { Day08(data08) },
        { Day08(test08) },
        numTestPart1 = 10,
        numTestPart2 = 1,
        numInitLive = 1
    )

    @Nested
    inner class Init {
        @Test
        fun `08,-,example,1`() {
            report(AOCTest({ "123".toInt() }, Unit, 123, 5, "08".toInt(), Part.TWO, false, "example"))
        }

        @Test
        fun `08,-,example,2`() {
            report(test.initTest.copy())
        }

        @Test
        fun `08,-,test,init`() {
            report(test.initTest)
        }

        @Test
        fun `08,-,live,init`() {
            report(test.initLive)
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `08,1,test`() {
            report(test.testPart1)
        }

        @Test
        @Slow(300)
        fun `08,1,live,1`() {
            report(test.livePart1)
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `08,2,test`() {
            report(test.testPart2)
        }

        @Test
        fun `08,2,live,1`() {
            report(test.livePart2)
        }
    }
}
