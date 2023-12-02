package no.rodland.advent_2021

import no.rodland.advent.DisableSlow
import no.rodland.advent.defaultTestSuiteParseOnCall
import no.rodland.advent.report
import org.junit.jupiter.api.Test
import readFile
import kotlin.time.ExperimentalTime

@ExperimentalTime
@DisableSlow
internal class Day23Test {
    private val liveData = "2021/input_23.txt".readFile()
    private val testData = listOf(
        "#############",
        "#...........#",
        "###B#C#B#D###",
        "  #A#D#C#A#",
        "  #########",
    )
    val test = defaultTestSuiteParseOnCall(
        16, Day23::partOne, Day23::partTwo, liveData, testData,
        testPart1 = 12521,
        livePart1 = 18300,
        testPart2 = 44169,
        livePart2 = 50190,  // too low: 329836099306
        numTestPart1 = 1,
        numTestPart2 = 1,
    )

    @Test
    fun `1_test`() {
        report(test.testPart1)
    }

    @Test
    fun `1_live`() {
        report(test.livePart1)
    }

    @Test
    fun `2_test`() {
        report(test.testPart2)
    }

    @Test
    fun `2_live`() {
        report(test.livePart2)
    }
}
