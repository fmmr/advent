package no.rodland.advent_2021

import no.rodland.advent.DisableSlow
import no.rodland.advent.defaultTestSuite
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile
import kotlin.time.ExperimentalTime

@ExperimentalTime
@Suppress("ClassName")
@DisableSlow
internal class Day13Test {
    private val liveData = "2021/input_13.txt".readFile()
    private val testData = listOf(
        "1",
        "2"
    )
    val test = defaultTestSuite(
        Day13::partOne,
        Day13::partTwo,
        liveData,
        testData,
        2, 2, 2, 2
    )

    @Nested
    inner class Init {
        @Test
        fun `13,1,live,init`() {
            report(test.livePart1.copy(numTests = 1))
        }

        @Test
        fun `13,1,simple,init`() {
            report { 2 to 2 }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `13,1,test`() {
            report(test.testPart1)
        }

        @Test
        fun `13,1,live`() {
            report(test.livePart1)
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `13,2,test`() {
            report(test.testPart2)
        }

        @Test
        fun `13,2,live`() {
            report(test.livePart2)
        }
    }
}
