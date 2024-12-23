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
internal class Day23Test {
    private val data23 = "2024/input_23.txt".readFile()
    private val test23 = "2024/input_23_test.txt".readFile()

    private val resultTestOne = 7
    private val resultTestTwo = "co,de,ka,ta"
    private val resultOne = 1215
    private val resultTwo = "bm,by,dv,ep,ia,ja,jb,ks,lv,ol,oy,uz,yt"

    val test = defaultTestSuiteParseOnInit(
        Day23(data23),
        Day23(test23),
        resultTestOne,
        resultOne,
        resultTestTwo,
        resultTwo,
        { Day23(data23) },
        { Day23(test23) },
        numTestPart1 = 4,
        numTestPart2 = 100
    )

    @Nested
    inner class Init {
        @Test
        fun `23,-,example,1`() {
            report(AOCTest({ "123".toInt() }, Unit, 123, 5, "23".toInt(), Part.TWO, false, "example"))
        }

        @Test
        fun `23,-,example,2`() {
            report(test.initTest.copy())
        }

        @Test
        fun `23,-,test,init`() {
            report(test.initTest)
        }

        @Test
        fun `23,-,live,init`() {
            report(test.initLive)
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `23,1,test`() {
            report(test.testPart1)
        }

        @Test
        fun `23,1,live,1`() {
            report(test.livePart1)
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `23,2,test`() {
            report(test.testPart2)
        }

        @Test
        fun `23,2,live,1`() {
            report(test.livePart2)
        }
    }
}
