package no.rodland.advent_2021

import no.rodland.advent.AOCTest
import no.rodland.advent.DisableSlow
import no.rodland.advent.defaultTestSuite
import no.rodland.advent.report
import no.rodland.advent_2021.Day18.magnitude
import no.rodland.advent_2021.Day18.split
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import readFile
import kotlin.time.ExperimentalTime

@ExperimentalTime
@Suppress("ClassName")
@DisableSlow
internal class Day18Test {
    private val liveData = "2021/input_18.txt".readFile()
    private val testData = listOf(
        "[1,2]",
        "[[1,2],3]",
        "[9,[8,7]]",
        "[[1,9],[8,5]]",
        "[[[[1,2],[3,4]],[[5,6],[7,8]]],9]",
        "[[[9,[3,8]],[[0,9],6]],[[[3,7],[4,9]],3]]",
        "[[[[1,3],[5,3]],[[1,3],[8,7]]],[[[4,9],[6,9]],[[8,2],[7,3]]]]",
    )
    val test = defaultTestSuite(
        18, Day18::partOne, Day18::partTwo, liveData, testData,
        testPart1 = 2,
        livePart1 = 2,
        testPart2 = 2,
        livePart2 = 2
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
    // fun `18,1,demo_1`() {
    //     report { 2 to 2 }
    // }

    @Test
    fun `18,1,split_10`() {
        report(AOCTest("18.demo", { it.split() }, 10, 5 to 5))
    }

    @Test
    fun `18,1,split_11`() {
        report(AOCTest("18.demo", { it.split() }, 11, 5 to 6))
    }

    @Test
    fun `18,1,magnitude_9,1`() {
        report(AOCTest("18.demo", { it.magnitude() }, 9 to 1, 29))
    }

    @Test
    fun `18,1,magnitude_1,9`() {
        report(AOCTest("18.demo", { it.magnitude() }, 1 to 9, 21))
    }
}

