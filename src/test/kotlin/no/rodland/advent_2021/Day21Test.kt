package no.rodland.advent_2021

import no.rodland.advent.DisableSlow
import no.rodland.advent.Slow
import no.rodland.advent.defaultTestSuiteParseOnCall
import no.rodland.advent.report
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import kotlin.time.ExperimentalTime

@ExperimentalTime
@DisableSlow
internal class Day21Test {
    private val liveData = 8 to 5
    private val testData = 4 to 8
    val test = defaultTestSuiteParseOnCall(
        21, Day21::partOne, Day21::partTwo, liveData, testData,
        testPart1 = 739785,
        livePart1 = 597600,
        testPart2 = 444356092776315L,
        livePart2 = 634769613696613L,
        numTestPart2 = 2
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
    @Slow(250)
    fun `2_live`() {
        report(test.livePart2)
    }
}
