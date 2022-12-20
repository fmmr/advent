package no.rodland.advent_2022

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import no.rodland.advent_2022.Day20.DECRYPTION_KEY
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@Suppress("ClassName")
@DisableSlow
internal class Day20Test {
    val data20 = "2022/input_20.txt".readFile()
    val test20_2 = listOf(
        "811589153",
        "1623178306",
        "-2434767459",
        "2434767459",
        "-1623178306",
        "0",
        "3246356612",
    )
    val test20 = listOf(
        "1",
        "2",
        "-3",
        "3",
        "-2",
        "0",
        "4",
    )

    val resultTestOne = 3L
    val resultOne = 10707L
    val resultTestTwo = 1623178306L * DECRYPTION_KEY
    val resultTwo = 2488332343098L

    @Nested
    inner class Init {
        @Test
        fun `20,1,live,init`() {
            report {
                Day20.partOne(data20) to resultOne
            }
        }

        @Test
        fun `20,2,live,init`() {
            report {
                Day20.partTwo(data20) to resultTwo
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `20,1,test`() {
            report {
                Day20.partOne(test20) to resultTestOne
            }
        }

        @Test
        fun `20,1,live,1`() {
            report {
                Day20.partOne(data20) to resultOne
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `20,2,test`() {
            report {
                Day20.partTwo(test20_2) to resultTestTwo
            }
        }

        @Test
        fun `20,2,live,1`() {
            report {
                Day20.partTwo(data20) to resultTwo
            }
        }
    }
}
