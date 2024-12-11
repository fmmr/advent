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
internal class Day11Test {
    private val data11 = "2024/input_11.txt".readFile()
    private val test11 = "2024/input_11_test.txt".readFile()

    private val resultTestOne = 55312L
    private val resultTestTwo = 65601038650482L
    private val resultOne = 172484L
    private val resultTwo = 205913561055242L

    val test = defaultTestSuiteParseOnInit(
        Day11(data11),
        Day11(test11),
        resultTestOne,
        resultOne,
        resultTestTwo,
        resultTwo,
        { Day11(data11) },
        { Day11(test11) },
        numTestPart1 = 100,
        numTestPart2 = 3
    )

    @Nested
    inner class Init {
        @Test
        fun `11,-,example,1`() {
            report(AOCTest({ "123".toInt() }, Unit, 123, 5, "11".toInt(), Part.TWO, false, "example"))
        }

        @Test
        fun `11,-,example,2`() {
            report(test.initTest.copy())
        }

        @Test
        fun `11,-,test,init`() {
            report(test.initTest)
        }

        @Test
        fun `11,-,live,init`() {
            report(test.initLive)
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `11,1,test`() {
            report(test.testPart1)
        }

        @Test
        fun `11,1,live,1`() {
            report(test.livePart1)
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `11,2,test`() {
            report(test.testPart2)
        }

        @Test
        fun `11,2,live,1`() {
            report(test.livePart2)
        }
    }
}
