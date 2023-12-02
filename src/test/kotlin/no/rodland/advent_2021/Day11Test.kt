package no.rodland.advent_2021

import no.rodland.advent.AOCTest
import no.rodland.advent.DisableSlow
import no.rodland.advent.IntGrid
import no.rodland.advent.defaultTestSuiteParseOnCall
import no.rodland.advent.report
import org.junit.jupiter.api.Test
import readFile
import kotlin.time.ExperimentalTime

@ExperimentalTime
@DisableSlow
internal class Day11Test {
    private val liveData = "2021/input_11.txt".readFile()
    private val testData = listOf(
        "5483143223",
        "2745854711",
        "5264556173",
        "6141336146",
        "6357385478",
        "4167524645",
        "2176841721",
        "6882881134",
        "4846848554",
        "5283751526",
    )
    private val testDataSimple = listOf(
        "11111",
        "19991",
        "19191",
        "19991",
        "11111",
    )
    val test = defaultTestSuiteParseOnCall(
        11, { Day11.partOne(it) }, Day11::partTwo, liveData, testData,
        testPart1 = 1656,
        livePart1 = 1562,
        testPart2 = 195,
        livePart2 = 268
    )

//    @BeforeAll
//    fun `0_init`() {
//        test.livePart1.run { function(data) }
//    }

    @Test
    fun `1_test_increase`() {
        report(AOCTest("test_increase", { it.increase(1).toString() }, IntGrid.fromInput(testDataSimple),
            listOf(
                "22222",
                "21010102",
                "2102102",
                "21010102",
                "22222",
            ).joinToString("\n")
        ))
    }

    @Test
    fun `1_test_simple`() {
        report(AOCTest("test_simple", { Day11.partOne(it, 2) }, testDataSimple, 9))
    }

    @Test
    fun `1_test_1`() {
        report(AOCTest("test_1", { Day11.partOne(it, 1) }, testData, 0))
    }


    @Test
    fun `1_test_2`() {
        report(AOCTest("test_1", { Day11.partOne(it, 2) }, testData, 35))
    }

    @Test
    fun `1_test_4`() {
        report(AOCTest("test_1", { Day11.partOne(it, 4) }, testData, 96))
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
}
