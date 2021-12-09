package no.rodland.advent_2021

import no.rodland.advent.DisableSlow
import no.rodland.advent.defaultTestSuite
import no.rodland.advent.report
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import readFile
import kotlin.time.ExperimentalTime

@ExperimentalTime
@Suppress("ClassName")
@DisableSlow
internal class Day16Test {
    private val liveData = "2021/input_16.txt".readFile()
    private val testData = listOf(
        "1",
        "2"
    )
    val test = defaultTestSuite(
        16, Day16::partOne, Day16::partTwo, liveData, testData,
        testPart1 = 2,
        livePart1 = 2,
        testPart2 = 2,
        livePart2 = 2
    )

    @BeforeAll
    @Test
    fun `0_init`() {
        report(test.livePart1.copy(numTests = 1))
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
    fun `2_live`() {
        report(test.livePart2)
    }

    // @Test
    // fun `16,1,demo_1`() {
    //     report { 2 to 2 }
    // }

    // @Test
    // fun `16,1,demo_2`() {
    //     report(AOCTest("16.demo", { it }, 2, 2))
    // }
}
