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
internal class Day14Test {
    private val data14 = "2024/input_14.txt".readFile()
    private val test14 = "2024/input_14_test.txt".readFile()

    private val resultTestOne = 12L
    private val resultTestTwo = 1
    private val resultOne = 221142636L
    private val resultTwo = 7916

    val test = defaultTestSuiteParseOnInit(
        Day14(data14, 101, 103),
        Day14(test14, 11, 7),
        resultTestOne,
        resultOne,
        resultTestTwo,
        resultTwo,
        { Day14(data14, 101, 103) },
        { Day14(test14, 11, 7) },
        numTestPart2 = 1
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
