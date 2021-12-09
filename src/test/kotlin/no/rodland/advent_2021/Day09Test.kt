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
internal class Day09Test {
    private val liveData = "2021/input_09.txt".readFile()
    private val testData = listOf(
        "2199943210",
        "3987894921",
        "9856789892",
        "8767896789",
        "9899965678",
    )
    val test = defaultTestSuite(
        9,
        Day09::partOne,
        Day09::partTwo,
        liveData,
        testData,
        15, 518, 1134, 949905
    )


    @Nested
    inner class Init {
        @Test
        fun `09,1,live,init`() {
            report(test.livePart1.copy(numTests = 1))
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `09,1,test`() {
            report(test.testPart1)
        }

        @Test
        fun `09,1,live`() {
            report(test.livePart1)
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `09,2,test`() {
            report(test.testPart2)
        }

        @Test
        fun `09,2,live`() {
            report(test.livePart2)
        }
    }
}
