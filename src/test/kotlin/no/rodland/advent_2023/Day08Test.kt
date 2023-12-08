package no.rodland.advent_2023

import no.rodland.advent.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile
import java.math.BigInteger

@Suppress("ClassName", "PrivatePropertyName")
@DisableSlow
internal class Day08Test {
    private val data08 = "2023/input_08.txt".readFile()
    private val test08_3 = listOf(
        "LR",
        "",
        "11A = (11B, XXX)",
        "11B = (XXX, 11Z)",
        "11Z = (11B, XXX)",
        "22A = (22B, XXX)",
        "22B = (22C, 22C)",
        "22C = (22Z, 22Z)",
        "22Z = (22B, 22B)",
        "XXX = (XXX, XXX)",
    )
    private val test08_2 = listOf(
        "LLR",
        "",
        "AAA = (BBB, BBB)",
        "BBB = (AAA, ZZZ)",
        "ZZZ = (ZZZ, ZZZ)",
    )
    private val test08 = listOf(
        "RL",
        "",
        "AAA = (BBB, CCC)",
        "BBB = (DDD, EEE)",
        "CCC = (ZZZ, GGG)",
        "DDD = (DDD, DDD)",
        "EEE = (EEE, EEE)",
        "GGG = (GGG, GGG)",
        "ZZZ = (ZZZ, ZZZ)",
    )

    private val resultTestOne = BigInteger.valueOf(2L)
    private val resultTestTwo = BigInteger.valueOf(2L)
    private val resultOne = BigInteger.valueOf(18827)
    private val resultTwo = BigInteger.valueOf(20220305520997L)

    val test = defaultTestSuiteParseOnInit(
        Day08(data08),
        Day08(test08),
        resultTestOne,
        resultOne,
        resultTestTwo,
        resultTwo,
        { Day08(data08) },
        { Day08(test08) },
        numTestPart1 = 200,
        numTestPart2 = 100,
    )

    @Nested
    inner class Init {
        @Test
        fun `08,-,test,init`() {
            report(test.initTest)
        }

        @Test
        fun `08,-,live,init`() {
            report(test.initLive)
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `08,1,test`() {
            report(test.testPart1)
        }

        @Test
        fun `08,1,test,2`() {
            val day = Day08(test08_2)
            report(test.testPart1.copy(function = { day.partOne() }, expected = BigInteger.valueOf(6L)))
        }

        @Test
        fun `08,1,live,1`() {
            report(test.livePart1)
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `08,2,test`() {
            val day08 = Day08(test08_3)
            report(AOCTest({ day08.partTwo() }, Unit, BigInteger.valueOf(6), 1, day08.day, Part.TWO, false))
        }

        @Test
        fun `08,2,live,1`() {
            report(test.livePart2)
        }
    }
}
