package no.rodland.advent_2019

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisableSlow
internal class Day25Test {
    val data25 = "2019/input_25.txt".readFirstLineStrings()

    @Nested
    inner class Init {
        @Test
        fun `25,1,live,init`() {
            report {
                Day25.partOne(data25) to 4362
            }
        }
    }
}
