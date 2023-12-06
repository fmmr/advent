package no.rodland.advent_2023

import no.rodland.advent.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@Suppress("ClassName")
@DisableSlow
internal class Day06Test {
    private val data06 = listOf(
        48 to 261,
        93 to 1192,
        84 to 1019,
        66 to 1063,
    )
    private val test06 = listOf(
        7 to 9,
        15 to 40,
        30 to 200,
    )

    private val resultTestOne = 288L
    private val resultTestTwo = 71503L
    private val resultOne = 1312850L
    private val resultTwo = 36749103L

    val test = defaultTestSuiteParseOnInit(
        Day06(data06),
        Day06(test06),
        resultTestOne,
        resultOne,
        resultTestTwo,
        resultTwo,
        { Day06(data06) },
        { Day06(test06) },
        numTestPart2 = 1000
    )

    @Nested
    inner class Init {
        @Test
        fun `06,-,test,init`() {
            report(test.initTest)
        }

        @Test
        fun `06,-,live,init`() {
            report(test.initLive)
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `06,1,test`() {
            report(test.testPart1)
        }

        @Test
        fun `06,1,live,1`() {
            report(test.livePart1)
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `06,2,test`() {
            report(test.testPart2)
        }

        @Test
        fun `06,2,live,1`() {
            report(test.livePart2)
        }
    }

    @Nested
    inner class `Part 2 implementations` {
        @Test
        fun `06,2,midPoint`() {
            report(AOCTest({  Day06(data06).impl_2_half_range_and_stop(48938466L, 261119210191063L) }, Unit, 36749103L, 10, 1, Part.TWO, true, "test half range and stop"))
        }

        @Test
        fun `06,2,all`() {
            report(AOCTest({  Day06(data06).impl_1_try_whole_range(48938466L, 261119210191063L) }, Unit, 36749103L, 10, 1, Part.TWO, true, "test all range"))
        }

        @Test
        fun `06,2,root`() {
            report(AOCTest({ Day06(data06).impl_3_solve_quadratic(48938466L, 261119210191063L) }, Unit, 36749103L, 10000, 1, Part.TWO, true, "solve for root"))
        }

    }
}
