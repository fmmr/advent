package no.rodland.advent_2021

import no.rodland.advent.DisableSlow
import no.rodland.advent.Slow
import no.rodland.advent.defaultTestSuiteParseOnCall
import no.rodland.advent.report
import org.junit.jupiter.api.Test
import readFile
import kotlin.time.ExperimentalTime

@ExperimentalTime
@DisableSlow
internal class Day25Test {
    private val liveData = "2021/input_25.txt".readFile()
    private val testData = listOf(
        "v...>>.vv>",
        ".vv>>.vv..",
        ">>.>v>...v",
        ">>v>>.>.v.",
        "v>v.vv.v..",
        ">.>>..v...",
        ".vv..>.>v.",
        "v.v..>>v.v",
        "....v..v.>",
    )
    val test = defaultTestSuiteParseOnCall(
        25, Day25::partOne, Day25::partTwo, liveData, testData,
        testPart1 = 58,
        livePart1 = 2,
        testPart2 = 2,
        livePart2 = 2,
        numTestPart1 = 1,
    )

//    @BeforeAll
//    fun `0_init`() {
//        test.livePart1.run { function(data) }
//    }

    @Test
    fun `1_test`() {
        report(test.testPart1)
    }

    @Test
    @Slow(2700)
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
    // fun `25,1,demo_1`() {
    //     report { 2 to 2 }
    // }

    // @Test
    // fun `25,1,demo_2`() {
    //     report(AOCTest("25.demo", { it }, 2, 2))
    // }
}
