package no.rodland.advent_2016

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import no.rodland.advent_2016.Day16.toCheckSum
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@Suppress("ClassName")
@DisableSlow
internal class Day16Test {
    val data16 = "00101000101111010"
    val test16 = "10000"

    @Nested
    inner class Init {
        @Test
        fun `16,1,live,init`() {
            report {
                Day16.partOne(data16, 272) to "10010100110011100"
            }
        }

        @Test
        fun `16,2,live,init`() {
            report {
                Day16.partTwo(data16) to 2
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `16,1,onestep_1`() {
            report {
                Day16.calcOneStep("1") to "100"
            }
        }

        @Test
        fun `16,1,onestep_0`() {
            report {
                Day16.calcOneStep("0") to "001"
            }
        }

        @Test
        fun `16,1,onestep_11111`() {
            report {
                Day16.calcOneStep("11111") to "11111000000"
            }
        }

        @Test
        fun `16,1,onestep_111100001010`() {
            report {
                Day16.calcOneStep("111100001010") to "1111000010100101011110000"
            }
        }

        @Test
        fun `16,1,checksum_110010110100`() {
            report {
                "110010110100".toCheckSum() to "100"
            }
        }

        @Test
        fun `16,1,test`() {
            report {
                Day16.partOne(test16, 20) to "01100"
            }
        }

        @Test
        fun `16,1,live,1`() {
            report {
                Day16.partOne(data16, 272) to "10010100110011100"
            }
        }

        @Test
        fun `16,1,live,2`() {
            report {
                Day16.partOne(data16, 272) to "10010100110011100"
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `16,2,test`() {
            report {
                Day16.partTwo(test16) to 2
            }
        }

        @Test
        fun `16,2,live,1`() {
            report {
                Day16.partTwo(data16) to 2
            }
        }

        @Test
        fun `16,2,live,2`() {
            report {
                Day16.partTwo(data16) to 2
            }
        }
    }
}
