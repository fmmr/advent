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
internal class Day14Test {
    private val liveData = "2021/input_14.txt".readFile()
    private val testData = listOf(
        "NNCB",
        "",
        "CH -> B",
        "HH -> N",
        "CB -> H",
        "NH -> C",
        "HB -> C",
        "HC -> B",
        "HN -> C",
        "NN -> C",
        "BH -> H",
        "NC -> B",
        "NB -> B",
        "BN -> B",
        "BB -> N",
        "BC -> B",
        "CC -> N",
        "CN -> C",
    )
    val test = defaultTestSuite(
        14, Day14::partOne, Day14::partTwo, liveData, testData,
        testPart1 = 1588,
        livePart1 = 2657,
        testPart2 = 2188189693529L,
        livePart2 = 2911561572630L
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
    fun `1_test_as_part2`() {
        report(test.testPart1.copy(function = Day14::partOneAsPartTwo))
    }

    @Test
    fun `1_live`() {
        report(test.livePart1)
    }

    @Test
    fun `1_live_as_part2`() {
        report(test.livePart1.copy(function = Day14::partOneAsPartTwo))
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
    // fun `14,1,demo_1`() {
    //     report { 2 to 2 }
    // }

    // @Test
    // fun `14,1,demo_2`() {
    //     report(AOCTest("14.demo", { it }, 2, 2))
    // }
}
