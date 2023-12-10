package no.rodland.advent_2023

import no.rodland.advent.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@Suppress("ClassName", "PrivatePropertyName", "PropertyName", "MemberVisibilityCanBePrivate")
@DisableSlow
internal class Day10Test {
    private val data10 = "2023/input_10.txt".readFile()
    private val test10 = listOf(
        ".....",
        ".S-7.",
        ".|.|.",
        ".L-J.",
        ".....",
    )
    private val test10_2 = listOf(
        "-L|F7",
        "7S-7|",
        "L|7||",
        "-L-J|",
        "L|-JF",
    )
    private val test10_3 = listOf(
        "..F7.",
        ".FJ|.",
        "SJ.L7",
        "|F--J",
        "LJ...",
    )
    private val test10_4 = listOf(
        "7-F7-",
        ".FJ|7",
        "SJLL7",
        "|F--J",
        "LJ.LJ",
    )
    private val test10_5 = listOf(
        "F-7..",
        "L-J..",
        ".S-7.",
        ".|.|.",
        ".L-J.",
    )

    private val resultTestOne = 4
    private val resultTestTwo = 2
    private val resultOne = 6599
    private val resultTwo = 2

    val test = defaultTestSuiteParseOnInit(
        Day10(data10),
        Day10(test10),
        resultTestOne,
        resultOne,
        resultTestTwo,
        resultTwo,
        { Day10(data10) },
        { Day10(test10) },
        numTestPart1 = 20
    )
    val testPart1_2 = test.testPart1.copy(function = { Day10(test10_2).partOne() })
    val testPart1_3 = testPart1_2.copy(function = { Day10(test10_3).partOne() }, expected = 8)
    val testPart1_4 = testPart1_3.copy(function = { Day10(test10_4).partOne() })
    val testPart1_5 = testPart1_2.copy(function = { Day10(test10_5).partOne() })

    @Nested
    inner class Init {
        @Test
        fun `10,-,example,1`() {
            report(AOCTest({ "123".toInt() }, Unit, 123, 5, "10".toInt(), Part.TWO, false, "example"))
        }

        @Test
        fun `10,-,example,2`() {
            report(test.initTest.copy())
        }

        @Test
        fun `10,-,test,init`() {
            report(test.initTest)
        }

        @Test
        fun `10,-,live,init`() {
            report(test.initLive)
        }
    }

    @Nested
    inner class `Part 1 Misc Test data` {
        @Test
        fun `10,1,test,1`() {
            report(test.testPart1)
        }

        @Test
        fun `10,1,test,2`() {
            report(testPart1_2)
        }

        @Test
        fun `10,1,test,3`() {
            report(testPart1_3)
        }

        @Test
        fun `10,1,test,4`() {
            report(testPart1_4)
        }

        @Test
        fun `10,1,test,5`() {
            report(testPart1_5)
        }

    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `10,1,test,1`() {
            report(test.testPart1)
        }


        @Test
        fun `10,1,live,1`() {
            report(test.livePart1)
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `10,2,test`() {
            report(test.testPart2)
        }

        @Test
        fun `10,2,live,1`() {
            report(test.livePart2)
        }
    }
}
