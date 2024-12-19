package no.rodland.advent_2024

import no.rodland.advent.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

//
//  run: download_aoc_input.sh to download input
//

@Suppress("ClassName")
@DisableSlow
internal class Day19Test {
    private val data19 = "2024/input_19.txt".readFile()
    private val test19 = "2024/input_19_test.txt".readFile()

    private val resultTestOne = 6
    private val resultTestTwo = 16L
    private val resultOne = 296
    private val resultTwo = 619970556776002L

    val test = defaultTestSuiteParseOnInit(
        Day19(data19),
        Day19(test19),
        resultTestOne,
        resultOne,
        resultTestTwo,
        resultTwo,
        { Day19(data19) },
        { Day19(test19) },
        numTestPart1 = 2,
        numTestPart2 = 2,
    )

    @Nested
    inner class Init {
        @Test
        fun `19,-,example,2`() {
            report(test.initTest.copy())
        }

        @Test
        fun `19,-,test,init`() {
            report(test.initTest)
        }

        @Test
        fun `19,-,test,substringAfter`() {
            val str = "abcghiabcjkl"
            assertEquals("NO_MATCH", str.substringAfter("sdjskdjks", "NO_MATCH"))
            assertEquals("ghiabcjkl", str.substringAfter("abc", "NO_MATCH"))
            assertEquals("", str.substringAfter("jkl", "NO_MATCH"))
        }

        @Test
        fun `19,-,live,init`() {
            report(test.initLive)
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `19,1,test`() {
            report(test.testPart1)
        }

        @Test
        fun `19,1,live,1`() {
            report(test.livePart1)
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `19,2,test`() {
            report(test.testPart2)
        }

        @Test
        fun `19,2,live,1`() {
            report(test.livePart2)
        }
    }
}
