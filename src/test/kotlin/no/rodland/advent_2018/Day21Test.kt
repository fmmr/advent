package no.rodland.advent_2018

import no.rodland.advent.DisableSlow
import no.rodland.advent.Slow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@DisableSlow
internal class Day21Test {
    val data21 = "2018/input_21.txt".readFile()

    @Nested
    inner class `Part 1` {
        @Test
        fun `21,1,live`() {
            report {
                Day21.partOne(data21) to 13522479
            }
        }
    }
    @Nested
    inner class `Part 2` {
        @Test
        @Slow(70000)
        fun `21,2,live`() {
            report {
                Day21.partTwo(data21) to 14626276
            }
        }
    }

}
