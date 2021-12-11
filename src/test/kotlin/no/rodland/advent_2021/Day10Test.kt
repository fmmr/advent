package no.rodland.advent_2021

import no.rodland.advent.AOCTest
import no.rodland.advent.DisableSlow
import no.rodland.advent.defaultTestSuite
import no.rodland.advent.report
import org.junit.jupiter.api.Test
import readFile
import kotlin.time.ExperimentalTime

@ExperimentalTime
@Suppress("ClassName")
@DisableSlow
internal class Day10Test {
    private val liveData = "2021/input_10.txt".readFile()
    private val testData = listOf(
        "[({(<(())[]>[[{[]{<()<>>",
        "[(()[<>])]({[<{<<[]>>(",
        "{([(<{}[<>[]}>{[]{[(<()>",
        "(((({<>}<{<{<>}{[]{[]{}",
        "[[<[([]))<([[{}[[()]]]",
        "[{[{({}]{}}([{[{{{}}([]",
        "{<[[]]>}<{[{[{[]{()[[[]",
        "[<(<(<(<{}))><([]([]()",
        "<{([([[(<>()){}]>(<<{{",
        "<{([{{}}[<[[[<>{}]]]>[]]",
    )
    val test = defaultTestSuite(
        10, Day10::partOne, Day10::partTwo, liveData, testData,
        testPart1 = 26397,
        livePart1 = 316851,
        testPart2 = 288957,
        livePart2 = 2182912364 // not 45691823, 63101831 That's not the right answer; your answer is too low.
    )

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
    // fun `10,1,demo_1`() {
    //     report { 2 to 2 }
    // }

    @Test
    fun `10,1,demo_2`() {
        report(AOCTest("10.points", Day10::toPointsPart1, ')', 3))
    }
}
