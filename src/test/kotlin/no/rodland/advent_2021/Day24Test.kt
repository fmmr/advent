package no.rodland.advent_2021

import no.rodland.advent.DisableSlow
import no.rodland.advent.Slow
import no.rodland.advent.defaultTestSuite
import no.rodland.advent.report
import org.junit.jupiter.api.Test
import readFile
import kotlin.time.ExperimentalTime

@ExperimentalTime
@Suppress("ClassName")
@DisableSlow
internal class Day24Test {
    private val liveData = "2021/input_24.txt".readFile()
    private val testData = listOf(
        "1",
        "2"
    )
    val test = defaultTestSuite(
        24, Day24::partOne, Day24::partTwo, liveData, testData,
        testPart1 = 2,
        livePart1 = 59996912981939L,
        testPart2 = 2,
        livePart2 = 17241911811915L,
        numTestPart1 = 1,
        numTestPart2 = 1,
    )

    @Test
    @Slow(2000)
    fun `1_live`() {
        report(test.livePart1)
    }

    @Test
    @Slow(2000)
    fun `2_live`() {
        report(test.livePart2)
    }
}
