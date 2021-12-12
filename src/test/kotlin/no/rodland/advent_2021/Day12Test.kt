package no.rodland.advent_2021

import no.rodland.advent.AOCTest
import no.rodland.advent.DisableSlow
import no.rodland.advent.Slow
import no.rodland.advent.defaultTestSuite
import no.rodland.advent.report
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import readFile
import kotlin.time.ExperimentalTime

@ExperimentalTime
@Suppress("ClassName")
@DisableSlow
internal class Day12Test {
    private val liveData = "2021/input_12.txt".readFile()
    private val testData1 = listOf(
        "dc-end",
        "HN-start",
        "start-kj",
        "dc-start",
        "dc-HN",
        "LN-dc",
        "HN-end",
        "kj-sa",
        "kj-HN",
        "kj-dc",
    )
    private val testData0 = listOf(
        "start-A",
        "start-b",
        "A-c",
        "A-b",
        "b-d",
        "A-end",
        "b-end",
    )
    private val testData = listOf(
        "fs-end",
        "he-DX",
        "fs-he",
        "start-DX",
        "pj-DX",
        "end-zg",
        "zg-sl",
        "zg-pj",
        "pj-he",
        "RW-he",
        "fs-DX",
        "pj-RW",
        "zg-RW",
        "start-pj",
        "he-WI",
        "zg-he",
        "pj-fs",
        "start-RW",
    )
    val test = defaultTestSuite(
        12, Day12::partOne, Day12::partTwo, liveData, testData,
        testPart1 = 226,
        livePart1 = 3450,
        testPart2 = 3509,
        livePart2 = 96528,
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
    fun `1_test_0`() {
        report(AOCTest("test_0", Day12::partOne, testData0, 10))
    }

    @Test
    fun `2_test_0`() {
        report(AOCTest("test_0", Day12::partTwo, testData0, 36))
    }

    @Test
    fun `1_test_1`() {
        report(AOCTest("test_1", Day12::partOne, testData1, 19))
    }
    @Test
    fun `2_test_1`() {
        report(AOCTest("test_1", Day12::partTwo, testData1, 103))
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
    @Slow(300)
    fun `2_live`() {
        report(test.livePart2)
    }
}
