package no.rodland.advent_2023

import no.rodland.advent.DisableSlow
import no.rodland.advent.defaultTestSuiteParseOnInit
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@Suppress("ClassName")
@DisableSlow
internal class Day05Test {
    private val data05 = "2023/input_05.txt".readFile()
    private val test05 = listOf(
        "seeds: 79 14 55 13",
        "",
        "seed-to-soil map:",
        "50 98 2",
        "52 50 48",
        "",
        "soil-to-fertilizer map:",
        "0 15 37",
        "37 52 2",
        "39 0 15",
        "",
        "fertilizer-to-water map:",
        "49 53 8",
        "0 11 42",
        "42 0 7",
        "57 7 4",
        "",
        "water-to-light map:",
        "88 18 7",
        "18 25 70",
        "",
        "light-to-temperature map:",
        "45 77 23",
        "81 45 19",
        "68 64 13",
        "",
        "temperature-to-humidity map:",
        "0 69 1",
        "1 0 69",
        "",
        "humidity-to-location map:",
        "60 56 37",
        "56 93 4",
    )

    private val resultTestOne = 35L
    private val resultTestTwo = 2L
    private val resultOne = 836040384L
    private val resultTwo = 2L

    val test = defaultTestSuiteParseOnInit(
        Day05(data05),
        Day05(test05),
        resultTestOne,
        resultOne,
        resultTestTwo,
        resultTwo,
        { Day05(data05) },
        { Day05(test05) },
    )

    @Nested
    inner class Init {
        @Test
        fun `05,-,test,init`() {
            report(test.initTest)
        }

        @Test
        fun `05,-,live,init`() {
            report(test.initLive)
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `05,1,test`() {
            report(test.testPart1)
        }

        @Test
        fun `05,1,live,1`() {
            report(test.livePart1)
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `05,2,test`() {
            report(test.testPart2)
        }

        @Test
        fun `05,2,live,1`() {
            report(test.livePart2)
        }
    }
}
