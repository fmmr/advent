package no.rodland.advent_2021

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
internal class Day20Test {
    private val liveData = "2021/input_20.txt".readFile()
    private val testData = listOf(
        "..#.#..#####.#.#.#.###.##.....###.##.#..###.####..#####..#....#..#..##..###..######.###...####..#..#####..##..#.#####...##.#.#..#.##..#.#......#.###.######.###.####...#.##.##..#..#..#####.....#.#....###..#.##......#.....#..#..#..##..#...##.######.####.####.#.#...#.......#..#.#.#...####.##.#......#..#...##.#.##..#...##.#.##..###.#......#.#.......#.#.#.####.###.##...#.....####.#..#..#.##.#....##..#.####....##...##..#...#......#.#.......#.......##..####..#...#.#.#...##..#.#..###..#####........#..####......#..#",
        "",
        "#..#.",
        "#....",
        "##..#",
        "..#..",
        "..###",
    )
    val test = defaultTestSuite(
        20, Day20::partOne, Day20::partTwo, liveData, testData,
        testPart1 = 35,
        livePart1 = 5425,  // 5570 too high
        testPart2 = 3351,
        livePart2 = 14052,
        numTestPart1 = 1,
        numTestPart2 = 1,
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
    @Slow(300)
    fun `2_test`() {
        report(test.testPart2)
    }

    @Test
    @Slow(1400)
    fun `2_live`() {
        report(test.livePart2)
    }
}
