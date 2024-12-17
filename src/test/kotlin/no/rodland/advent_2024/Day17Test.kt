package no.rodland.advent_2024

import no.rodland.advent.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

//
//  run: download_aoc_input.sh to download input
//

@Suppress("ClassName", "SpellCheckingInspection")
@DisableSlow
internal class Day17Test {
    private val data17 = "2024/input_17.txt".readFile()
    private val test17 = "2024/input_17_test.txt".readFile()
    private val test17Part2 = "2024/input_17_test_part2.txt".readFile()

    private val resultTestOne = "4,6,3,5,6,3,5,2,1,0"
    private val resultTestTwo = 117440L
    private val resultOne = "4,0,4,7,1,2,7,1,6"
    private val resultTwo = 202322348616234L

    val test = run {
        val liveDay = Day17(data17)
        val testDay = Day17(test17)
        val testDayPart2 = Day17(test17Part2)
        AOCTestSuite<Any?, Unit, Unit>(
            AOCTest({ liveDay.partOne() }, Unit, resultOne, 1000, liveDay.day, Part.ONE, true),
            AOCTest({ liveDay.partTwo() }, Unit, resultTwo, 1000, liveDay.day, Part.TWO, true),
            AOCTest({ testDay.partOne() }, Unit, resultTestOne, 1, liveDay.day, Part.ONE, false),
            AOCTest({ testDayPart2.partTwo() }, Unit, resultTestTwo, 1, liveDay.day, Part.TWO, false),
            AOCTest<Unit, Unit>({ Day17(data17) }, Unit, Unit, 100, liveDay.day, Part.INIT, live = true),
            AOCTest<Unit, Unit>({ Day17(test17Part2) }, Unit, Unit, 100, liveDay.day, Part.INIT, live = false),
        )
    }

    @Nested
    inner class Init {
        @Test
        fun `17,-,bst,1`() {
            val computer = Day17.Computer(0, 0, 9, listOf(2, 6))
            computer.run({ computer.b }, 1)
        }

        @Test
        fun `17,-,out,1`() {
            val computer = Day17.Computer(10, 0, 0, listOf(5, 0, 5, 1, 5, 4))
            computer.run({ computer.output() }, "0,1,2")
        }

        @Test
        fun `17,-,out2,1`() {
            val computer = Day17.Computer(2024, 0, 0, listOf(0, 1, 5, 4, 3, 0))
            computer.run({ computer.output() to computer.a }, "4,2,5,6,7,7,7,7,3,1,0" to 0L)
        }

        @Test
        fun `17,-,bxl,1`() {
            val computer = Day17.Computer(0, 29, 0, listOf(1, 7))
            computer.run({ computer.b }, 26)
        }

        @Test
        fun `17,-,bxc,1`() {
            val computer = Day17.Computer(0, 2024, 43690, listOf(4, 0))
            computer.run({ computer.b }, 44354)
        }

        @Test
        fun `17,-,original,1`() {
            val org = listOf(0, 3, 5, 4, 3, 0)
            val computer = Day17.Computer(117440, 0, 0, org)
            computer.run({ computer.output() }, org.joinToString(","))
        }

        private fun <T> Day17.Computer.run(function: (Unit) -> T, expected: T, part: Part = Part.ONE) {
            run()
            report(AOCTest(function, Unit, expected, 1, "17".toInt(), part, false, "2,6"))
        }

        @Test
        fun `17,-,example,1`() {
            report(AOCTest({ "123".toInt() }, Unit, 123, 5, "17".toInt(), Part.TWO, false, "example"))
        }

        @Test
        fun `17,-,example,2`() {
            report(test.initTest.copy())
        }

        @Test
        fun `17,-,test,init`() {
            report(test.initTest)
        }

        @Test
        fun `17,-,live,init`() {
            report(test.initLive)
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `17,1,test`() {
            report(test.testPart1)
        }

        @Test
        fun `17,1,live,1`() {
            report(test.livePart1)
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `17,2,test`() {
            report(test.testPart2)
        }

        @Test
        fun `17,2,live,1`() {
            report(test.livePart2)
        }
    }
}
