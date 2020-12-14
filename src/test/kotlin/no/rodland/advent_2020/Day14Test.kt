package no.rodland.advent_2020

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@Suppress("ClassName")
@DisableSlow
internal class Day14Test {
    val data14 = "2020/input_14.txt".readFile()
    val test14 = listOf(
        "mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X",
        "mem[8] = 11",
        "mem[7] = 101",
        "mem[8] = 0",
    )
    val test14_2 = listOf(
        "mask = 000000000000000000000000000000X1001X",
        "mem[42] = 100",
        "mask = 00000000000000000000000000000000X0XX",
        "mem[26] = 1",
    )

    @Nested
    inner class Init {
        @Test
        fun `14,1,live,init`() {
            report {
                Day14.partOne(data14) to 10050490168421
            }
        }

        @Test
        fun `14,2,live,init`() {
            report {
                Day14.partTwo(data14) to 2173858456958
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `14,1,test`() {
            report {
                Day14.partOne(test14) to 165
            }
        }

        @Test
        fun `14,1,live,1`() {
            report {
                Day14.partOne(data14) to 10050490168421
            }
        }

        @Test
        fun `14,1,live,2`() {
            report {
                Day14.partOne(data14) to 10050490168421
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `14,2,test`() {
            report {
                Day14.partTwo(test14_2) to 208
            }
        }

        @Test
        fun `14,2,live,1`() {
            report {
                Day14.partTwo(data14) to 2173858456958
            }
        }

        @Test
        fun `14,2,live,2`() {
            report {
                Day14.partTwo(data14) to 2173858456958
            }
        }
    }
}
