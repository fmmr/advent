package no.rodland.advent_2018

import Day8
import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@DisableSlow
internal class Day8Test {

    val data8 = "2018/input_8.txt".readFile()[0].split(" ").toList().map { it.toInt() }
    val test8 = listOf(2, 3, 0, 3, 10, 11, 12, 1, 1, 0, 1, 99, 2, 1, 1, 2)


    @Nested
    inner class `Part 1` {
        @Test
        fun `8,1,test`() {
            report {
                Day8.partOne(test8) to 138
            }
        }

        @Test
        fun `7,1,live`() {
            report {
                Day8.partOne(data8) to 45618
            }
        }
    }

    @Nested
    inner class `Part 2` {
    }
}


