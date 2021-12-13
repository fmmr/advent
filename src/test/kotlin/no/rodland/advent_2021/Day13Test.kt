package no.rodland.advent_2021

import no.rodland.advent.DisableSlow
import no.rodland.advent.defaultTestSuite
import no.rodland.advent.report
import org.junit.jupiter.api.Test
import readFile
import kotlin.time.ExperimentalTime

@ExperimentalTime
@Suppress("ClassName")
@DisableSlow
internal class Day13Test {
    private val liveData = "2021/input_13.txt".readFile()
    private val testData = listOf(
        "6,10",
        "0,14",
        "9,10",
        "0,3",
        "10,4",
        "4,11",
        "6,0",
        "6,12",
        "4,1",
        "0,13",
        "10,12",
        "3,4",
        "3,0",
        "8,4",
        "1,10",
        "2,14",
        "8,10",
        "9,0",
        "",
        "fold along y=7",
        "fold along x=5",
    )
    val test = defaultTestSuite(
        13, Day13::partOne, Day13::partTwo, liveData, testData,
        testPart1 = 17,
        livePart1 = 675,
        testPart2 = 2,
        livePart2 = 2
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
    // fun `13,1,demo_1`() {
    //     report { 2 to 2 }
    // }

    // @Test
    // fun `13,1,demo_2`() {
    //     report(AOCTest("13.demo", { it }, 2, 2))
    // }
}
