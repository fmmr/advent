package no.rodland.advent_2020

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@Suppress("ClassName")
@DisableSlow
internal class Day08Test {
    val data08 = "2020/input_08.txt".readFile()
    val test08 = listOf(
        "nop +0",
        "acc +1",
        "jmp +4",
        "acc +3",
        "jmp -3",
        "acc -99",
        "acc +1",
        "jmp -4",
        "acc +6",
    )

    @Nested
    inner class Init {
        @Test
        fun `08,1,live,init`() {
            report {
                Day08.partOne(data08) to 1217
            }
        }

        @Test
        fun `08,2,live,init`() {
            report {
                Day08.partTwo(data08) to 501
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `08,1,test`() {
            report {
                Day08.partOne(test08) to 5
            }
        }

        @Test
        fun `08,1,live,1`() {
            report {
                Day08.partOne(data08) to 1217
            }
        }

        @Test
        fun `08,1,live,2`() {
            report {
                Day08.partOne(data08) to 1217
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `08,2,test`() {
            report {
                Day08.partTwo(test08) to 8
            }
        }

        @Test
        fun `08,2,live,1`() {
            report {
                Day08.partTwo(data08) to 501
            }
        }

        @Test
        fun `08,2,live,2`() {
            report {
                Day08.partTwo(data08) to 501
            }
        }
    }
}
