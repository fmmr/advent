package no.rodland.advent_2017

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@Suppress("ClassName")
@DisableSlow
internal class Day16Test {
    val data16 = "2017/input_16.txt".readFileAsOneString()
    val test16 = "s1,x3/4,pe/b"
    val data = "abcdefghijklmnop"
    val test = "abcde"

    @Nested
    inner class `Part 1` {
        @Test
        fun `16,1,test`() {
            report {
                Day16.partOne(test, test16) to "baedc"
            }
        }

        @Test
        fun `16,1,live,1`() {
            report {
                Day16.partOne(data, data16) to "hmefajngplkidocb"
            }
        }

        @Test
        fun `16,1,live,2`() {
            report {
                Day16.partOne(data, data16) to "hmefajngplkidocb"
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `16,2,test`() {
            report {
                Day16.partTwo(test, test16) to "2"
            }
        }

        @Test
        fun `16,2,live,1`() {
            report {
                Day16.partTwo(data, data16) to "2"
            }
        }

        @Test
        fun `16,2,live,2`() {
            report {
                Day16.partTwo(data, data16) to "2"
            }
        }
    }
}
