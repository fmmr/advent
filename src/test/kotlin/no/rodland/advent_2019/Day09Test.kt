package no.rodland.advent_2019

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisableSlow
internal class Day09Test {
    val data09 = "2019/input_09.txt".readFirstLineStrings()

    @Nested
    inner class `Part 1` {
        @Test
        fun `09,1,test,1`() {
            report {
                val program = listOf("109", "1", "204", "-1", "1001", "100", "1", "100", "1008", "100", "16", "101", "1006", "101", "0", "99")
                Day09.partOne(program, 1L) to program.map { it.toLong() }
            }
        }

        @Test
        fun `09,1,test,2`() {
            report {
                Day09.partOne(listOf("1102", "34915192", "34915192", "7", "4", "7", "99", "0"), 1L) to listOf(1219070632396864L)
            }
        }

        @Test
        fun `09,1,test,3`() {
            report {
                Day09.partOne(listOf("104", "1125899906842624", "99"), 1L) to listOf(1125899906842624L)
            }
        }

        @Test
        fun `09,1,live`() {
            report {
                Day09.partOne(data09, 1L) to listOf(2890527621L)
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `09,2,live`() {
            report {
                Day09.partOne(data09, 2L) to listOf(66772L)
            }
        }
    }
}


