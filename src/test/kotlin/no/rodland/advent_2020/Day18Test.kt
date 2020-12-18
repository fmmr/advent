package no.rodland.advent_2020

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@Suppress("ClassName")
@DisableSlow
internal class Day18Test {
    val data18 = "2020/input_18.txt".readFile()
    val test18 = listOf(
        "1 + (2 * 3) + (4 * (5 + 6))",
        "1 + 2 * 3 + 4 * 5 + 6"
    )

    @Nested
    inner class Init {
        @Test
        fun `18,1,live,init`() {
            report {
                Day18.partOne(data18) to 21347713555555
            }
        }

        @Test
        fun `18,2,live,init`() {
            report {
                Day18.partTwo(data18) to 275011754427339
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `18,1,test`() {
            report {
                Day18.partOne(test18) to 122
            }
        }

        @Test
        fun `18,1,test,2_a`() {
            report {
                Day18.shuntingYard("1 + (2 * 3) + (4 * (5 + 6))") to "123*+456+*+"
            }
        }

        @Test
        fun `18,1,test,2_b`() {
            report {
                Day18.rpn("123*+456+*+") to 51
            }
        }


        @Test
        fun `18,1,live,1`() {
            report {
                Day18.partOne(data18) to 21347713555555
            }
        }

        @Test
        fun `18,1,live,2`() {
            report {
                Day18.partOne(data18) to 21347713555555
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `18,2,test`() {
            report {
                Day18.partTwo(test18) to 282
            }
        }

        @Test
        fun `18,2,live,1`() {
            report {
                Day18.partTwo(data18) to 275011754427339
            }
        }

        @Test
        fun `18,2,live,2`() {
            report {
                Day18.partTwo(data18) to 275011754427339
            }
        }
    }
}
