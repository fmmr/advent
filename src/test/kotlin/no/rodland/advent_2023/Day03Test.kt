package no.rodland.advent_2023

import no.rodland.advent.DisableSlow
import no.rodland.advent.defaultTestSuiteParseOnInit
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@Suppress("ClassName")
@DisableSlow
internal class Day03Test {
    private val data03 = "2023/input_03.txt".readFile()
    private val test03 = listOf("1", "2")

    private val resultTestOne = 1L
    private val resultTestTwo = 2L
    private val resultOne = 2L
    private val resultTwo = 2L

    val test = defaultTestSuiteParseOnInit(Day03(data03), Day03(test03), resultTestOne, resultOne, resultTestTwo, resultTwo)

    @Nested
    inner class Init {
        @Test
        fun `03,1,live,init`() {
            report(test.initPart1)
        }

        @Test
        fun `03,2,live,init`() {
            report(test.initPart2)
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
