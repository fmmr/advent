package no.rodland.advent_2019

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisableSlow
@ExperimentalCoroutinesApi
@DelicateCoroutinesApi
internal class Day11Test {
    val data11 = "2019/input_11.txt".readFirstLineStrings()
    val test11 = listOf("1", "2")

    @Nested
    inner class `Part 1` {
        @Test
        fun `11,1,live`() {
            report {
                Day11.part1(data11) to 2172
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `11,2,live`() {
            report {
                Day11.partTwo(data11) to 2
            }
        }
    }
}


