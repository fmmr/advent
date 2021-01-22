package no.rodland.advent_2016

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@Suppress("ClassName")
@DisableSlow
internal class Day24Test {
    val data24 = "2016/input_24.txt".readFile()
    val test24 = listOf(
            "###########",
            "#0.1.....2#",
            "#.#######.#",
            "#4.......3#",
            "###########",
    )

    @Nested
    inner class Init {
        @Test
        fun `24,1,live,init`() {
            report {
                Day24.partOne(data24) to 518
            }
        }

        @Test
        fun `24,2,live,init`() {
            report {
                Day24.partTwo(data24) to 716
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `24,1,test`() {
            report {
                Day24.partOne(test24) to 14
            }
        }

        @Test
        fun `24,1,live,1`() {
            report {
                Day24.partOne(data24) to 518
            }
        }

        @Test
        fun `24,1,live,2`() {
            report {
                Day24.partOne(data24) to 518
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `24,2,test`() {
            report {
                Day24.partTwo(test24) to 20
            }
        }

        @Test
        fun `24,2,live,1`() {
            report {
                Day24.partTwo(data24) to 716
            }
        }

        @Test
        fun `24,2,live,2`() {
            report {
                Day24.partTwo(data24) to 716
            }
        }
    }
}
