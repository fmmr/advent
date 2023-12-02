package no.rodland.advent_2021

import no.rodland.advent.DisableSlow
import no.rodland.advent.defaultTestSuiteParseOnCall
import no.rodland.advent.report
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import kotlin.time.ExperimentalTime

@ExperimentalTime
@DisableSlow
internal class Day17Test {
    private val liveData = "target area: x=179..201, y=-109..-63"
    private val testData = "target area: x=20..30, y=-10..-5"
    val test = defaultTestSuiteParseOnCall(
        17, Day17::partOne, Day17::partTwo, liveData, testData,
        testPart1 = 45,
        livePart1 = 5886,
        testPart2 = 112,
        livePart2 = 1806,
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
    fun `2_live`() {
        report(test.livePart2)
    }

    // @Test
    // fun `17,1,demo_1`() {
    //     report { 2 to 2 }
    // }

    // @Test
    // fun `17,1,demo_2`() {
    //     report(AOCTest("17.demo", { it }, 2, 2))
    // }
}
