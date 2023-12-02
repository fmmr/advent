package no.rodland.advent_2021

import no.rodland.advent.DisableSlow
import no.rodland.advent.Slow
import no.rodland.advent.defaultTestSuiteParseOnCall
import no.rodland.advent.report
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import readFile
import kotlin.time.ExperimentalTime

@ExperimentalStdlibApi
@ExperimentalTime
@DisableSlow
internal class Day15Test {
    private val liveData = "2021/input_15.txt".readFile()
    private val testData = listOf(
        "1163751742",
        "1381373672",
        "2136511328",
        "3694931569",
        "7463417111",
        "1319128137",
        "1359912421",
        "3125421639",
        "1293138521",
        "2311944581",
    )
    val test = defaultTestSuiteParseOnCall(
        15, Day15::partOne, Day15::partTwo, liveData, testData,
        testPart1 = 40,
        livePart1 = 390,
        testPart2 = 315,
        livePart2 = 2814,
        numTestPart2 = 1
    )
    val testChrisWk = defaultTestSuiteParseOnCall(
        15, Day15ChrisWK::part1, Day15ChrisWK::part2, liveData, testData,
        testPart1 = 40,
        livePart1 = 390,
        testPart2 = 315,
        livePart2 = 2814,
        numTestPart2 = 1
    )

    @BeforeAll
    fun `0_init`() {
        test.livePart1.run { function(data) }
    }

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
    @Slow(700)
    fun `2_live`() {
        report(test.livePart2)
    }

    @Test
    fun `1_test_chriswk`() {
        report(testChrisWk.testPart1)
    }

    @Test
    fun `1_live_chriswk`() {
        report(testChrisWk.livePart1)
    }

    @Test
    fun `2_test_chriswk`() {
        report(testChrisWk.testPart2)
    }

    @Test
    @Slow(1000)
    fun `2_live_chriswk`() {
        report(testChrisWk.livePart2)
    }
}
