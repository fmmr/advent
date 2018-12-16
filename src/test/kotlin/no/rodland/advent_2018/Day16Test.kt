package no.rodland.advent_2018

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@DisableSlow
internal class Day16Test {
    val samples16 = "2018/input_16_samples.txt".readFile()
    val program16 = "2018/input_16_program.txt".readFile()
    val test16 = listOf("1", "2")

    @Nested
    inner class `Part 1` {
        @Test
        fun `16,1,test`() {
            report {
                Day16.partOne(samples16) to 2
            }
        }

        @Test
        fun `16,1,live`() {
            report {
                Day16.partOne(samples16) to 2
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `16,2,test`() {
            report {
                Day16.partTwo(program16) to 2
            }
        }

        @Test
        fun `16,2,live`() {
            report {
                Day16.partTwo(program16) to 2
            }
        }
    }
}


