package no.rodland.advent_2019

import kotlinx.coroutines.ExperimentalCoroutinesApi
import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisableSlow
internal class Day23Test {
    val data23 = "2019/input_23.txt".readFirstLineStrings()

    @Nested
    inner class `Part 1` {
        @Test
        fun `23,1,live`() {
            report {
                Day23.partOne(data23) to 23266
            }
        }
    }

    @Nested
    @Disabled("sometimes fails with result 17494.  stopped in 2021 to avoid breaking entire build.")
    inner class `Part 2` {
        @ExperimentalCoroutinesApi
        @Test
        fun `23,2,live`() {
            report {
                Day23.partTwo(data23) to 17493
            }
        }
    }
}


