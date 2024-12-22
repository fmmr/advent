package no.rodland.advent_2024

import no.rodland.advent.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

//
//  run: download_aoc_input.sh to download input
//

@Suppress("ClassName")
@DisableSlow
internal class Day22Test {
    private val data22 = "2024/input_22.txt".readFile()
    private val test22 = "2024/input_22_test.txt".readFile()
    private val test22Part2 = "2024/input_22_test_part2.txt".readFile()

    private val resultTestOne = 37327623L
    private val resultTestTwo = 23L
    private val resultOne = 14082561342L
    private val resultTwo = 1568L

    val test = run {
        val liveDay = Day22(data22)
        val testDay = Day22(test22)
        val testDayPart2 = Day22(test22Part2)
        AOCTestSuite<Any?, Unit, Unit>(
            AOCTest({ liveDay.partOne() }, Unit, resultOne, numTests = 3, day = liveDay.day, part = Part.ONE, live = true),
            AOCTest({ liveDay.partTwo() }, Unit, resultTwo, 1, liveDay.day, Part.TWO, true),
            AOCTest({ testDay.partOne() }, Unit, resultTestOne, 1, liveDay.day, Part.ONE, false),
            AOCTest({ testDayPart2.partTwo() }, Unit, resultTestTwo, 1, liveDay.day, Part.TWO, false),
            AOCTest<Unit, Unit>({ Day22(data22) }, Unit, Unit, 100, liveDay.day, Part.INIT, live = true),
            AOCTest<Unit, Unit>({ Day22(test22) }, Unit, Unit, 100, liveDay.day, Part.INIT, live = false),
        )
    }

    @Nested
    inner class Init {
        @Test
        fun `22,-,example,1`() {
            report(AOCTest({ "123".toInt() }, Unit, 123, 5, "22".toInt(), Part.TWO, false, "example"))
        }

        @Test
        fun `22,-,example,2`() {
            report(test.initTest.copy())
        }

        @Test
        fun `22,-,test,init`() {
            report(test.initTest)
        }

        @Test
        fun `22,-,live,init`() {
            report(test.initLive)
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `22,1,test`() {
            report(test.testPart1)
        }

        @Test
        fun `22,1,live,1`() {
            report(test.livePart1)
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `22,2,test`() {
            report(test.testPart2)
        }

        @Test
//        @Slow(2000)
        fun `22,2,live,1`() {
            report(test.livePart2)
        }
    }
}
