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
internal class Day12Test {
    private val data12 = "2024/input_12.txt".readFile()
    private val test12 = "2024/input_12_test.txt".readFile()

    private val resultTestOne = 1930
    private val resultTestTwo = 2
    private val resultOne = 1424472
    private val resultTwo = 2

    val test = defaultTestSuiteParseOnInit(
        Day12(data12),
        Day12(test12),
        resultTestOne,
        resultOne,
        resultTestTwo,
        resultTwo,
        { Day12(data12) },
        { Day12(test12) },
        numTestPart1 = 5
    )

    @Nested
    inner class Init {
        @Test
        fun `12,-,example,1`() {
            report(AOCTest({ "123".toInt() }, Unit, 123, 5, "12".toInt(), Part.TWO, false, "example"))
        }

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
