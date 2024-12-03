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
internal class Day03Test {
    private val data03 = "2024/input_03.txt".readFile()
    private val test03 = "2024/input_03_test.txt".readFile()

    private val resultTestOne = 161
    private val resultTestTwo = 48
    private val resultOne = 187825547
    private val resultTwo = 85508223

    val test = defaultTestSuiteParseOnInit(
        Day03(data03),
        Day03(test03),
        resultTestOne,
        resultOne,
        resultTestTwo,
        resultTwo,
        { Day03(data03) },
        { Day03(test03) },
    )

    @Nested
    inner class Init {
        @Test
        fun `03,-,example,1`() {
            report(AOCTest({ "123".toInt() }, Unit, 123, 5, "03".toInt(), Part.TWO, false, "example"))
        }

        @Test
        fun `03,-,example,2`() {
            report(test.initTest.copy())
        }

        @Test
        fun `03,-,test,init`() {
            report(test.initTest)
        }

        @Test
        fun `03,-,live,init`() {
            report(test.initLive)
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `03,1,test`() {
            report(test.testPart1)
        }

        @Test
        fun `03,1,live,1`() {
            report(test.livePart1)
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `03,2,test`() {
            report(test.testPart2)
        }

        @Test
        fun `03,2,live,1`() {
            report(test.livePart2)
        }
    }
}
