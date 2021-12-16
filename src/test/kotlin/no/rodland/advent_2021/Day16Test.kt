package no.rodland.advent_2021

import no.rodland.advent.AOCTest
import no.rodland.advent.DisableSlow
import no.rodland.advent.defaultTestSuite
import no.rodland.advent.report
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile
import kotlin.time.ExperimentalTime

@ExperimentalTime
@Suppress("ClassName")
@DisableSlow
internal class Day16Test {
    private val liveData = "2021/input_16.txt".readFile().first()
    private val testData = "D2FE28"

    val test = defaultTestSuite(
        16, Day16::partOne, Day16::partTwo, liveData, testData,
        testPart1 = 6,
        livePart1 = 847,
        testPart2 = 2021,
        livePart2 = 333794664059L,  // too low: 329836099306
    )

    @BeforeAll
    fun `0_init`() {
        test.livePart1.run { function(data) }
    }

    @Nested
    inner class Eval {
        @Test
        fun `2_eval_D2FE28`() {
            report(AOCTest("test_0", Day16::eval, "D2FE28", 2021L))
        }

        @Test
        fun `2_eval_C200B40A82`() {
            report(AOCTest("test_0", Day16::eval, "C200B40A82", 3L))
        }

        @Test
        fun `2_eval_04005AC33890`() {
            report(AOCTest("test_0", Day16::eval, "04005AC33890", 54L))
        }

        @Test
        fun `2_eval_880086C3E88112`() {
            report(AOCTest("test_0", Day16::eval, "880086C3E88112", 7L))
        }

        @Test
        fun `2_eval_D8005AC2A8F0`() {
            report(AOCTest("test_0", Day16::eval, "D8005AC2A8F0", 1L))
        }

        @Test
        fun `2_eval_F600BC2D8F`() {
            report(AOCTest("test_0", Day16::eval, "F600BC2D8F", 0L))
        }

        @Test
        fun `2_eval_9C005AC2F8F0`() {
            report(AOCTest("test_0", Day16::eval, "9C005AC2F8F0", 0L))
        }

        @Test
        fun `2_eval_9C0141080250320F1802104A08`() {
            report(AOCTest("test_0", Day16::eval, "9C0141080250320F1802104A08", 1L))
        }

    }

    @Nested
    inner class Parsing {
        @Test
        fun `1_parse_D2FE28`() {
            report(AOCTest("test_0", Day16::allLiterals, "D2FE28", listOf(2021L)))
        }

        @Test
        fun `1_parse_38006F45291200`() {
            report(AOCTest("test_0", Day16::allLiterals, "38006F45291200", listOf(10L, 20L)))
        }

        @Test
        fun `1_parse_EE00D40C823060`() {
            report(AOCTest("test_0", Day16::allLiterals, "EE00D40C823060", listOf(1L, 2L, 3L)))
        }

        @Test
        fun `1_parse_8A004A801A8002F478`() {
            report(AOCTest("test_0", Day16::partOne, "8A004A801A8002F478", 16))
        }

        @Test
        fun `1_parse_620080001611562C8802118E34`() {
            report(AOCTest("test_0", Day16::partOne, "620080001611562C8802118E34", 12))
        }

        @Test
        fun `1_parse_C0015000016115A2E0802F182340`() {
            report(AOCTest("test_0", Day16::partOne, "C0015000016115A2E0802F182340", 23))
        }

        @Test
        fun `1_parse_A0016C880162017C3686B18A3D4780`() {
            report(AOCTest("test_0", Day16::partOne, "A0016C880162017C3686B18A3D4780", 31))
        }
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
}
