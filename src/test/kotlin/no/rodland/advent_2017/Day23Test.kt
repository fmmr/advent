package no.rodland.advent_2017

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@Suppress("ClassName")
@DisableSlow
internal class Day23Test {
    val data23 = "2017/input_23.txt".readFile()
    @Nested
    inner class `Part 1` {
        @Test
        fun `23,1,live,1`() {
            report {
                Day23.partOne(data23) to 6241
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `23,2,test`() {
            report {
                Day23.partTwo(data23) to 909
            }
        }
    }
}
