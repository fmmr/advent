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
internal class Day22Test {
    private val data22 = "2023/input_22.txt".readFile()
    private val test22 = "2023/input_22_test.txt".readFile()

    private val resultTestOne = 5
    private val resultTestTwo = 7
    private val resultOne = 497
    private val resultTwo = 67468

    val test = defaultTestSuiteParseOnInit(
        Day22(data22),
        Day22(test22),
        resultTestOne,
        resultOne,
        resultTestTwo,
        resultTwo,
        { Day22(data22) },
        { Day22(test22) },
        numInitLive = 2,
        numTestPart2 = 1,
    )

    @Nested
    inner class Init {
        @Test
        fun `22,-,example,1`() {
            report(AOCTest({ "123".toInt() }, Unit, 123, 5, "22".toInt(), Part.TWO, false, "example"))
        }

        @Test
        fun `22,-,example,2`() {
            report(test.initTest.copy())
        }

        @Test
        fun `22,-,test,init`() {
            report(test.initTest)
        }

        @Test
        fun `22,-,live,init`() {
            report(test.initLive)
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `22,1,test`() {
            report(test.testPart1)
        }

        @Test
        fun `22,1,live,1`() {
            report(test.livePart1)
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `22,2,test`() {
            report(test.testPart2)
        }

        @Test
        @Slow(660)
        fun `22,2,live,1`() {
            report(test.livePart2)
        }
    }
}
