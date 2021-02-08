package no.rodland.advent_2019

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@DisableSlow
internal class Day22Test {
    val data22 = "2019/input_22.txt".readFile()
    val test22 = listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
    val testInput = listOf(
            "deal into new stack",
            "cut -2",
            "deal with increment 7",
            "cut 8",
            "cut -4",
            "deal with increment 7",
            "cut 3",
            "deal with increment 9",
            "deal with increment 3",
            "cut -1"
    )

    @Nested
    inner class `Test Functions` {
        @Test
        fun `22,1,deal into new stack`() {
            report {
                test22.deal_new() to listOf(9, 8, 7, 6, 5, 4, 3, 2, 1, 0)
            }
        }

        @Test
        fun `22,1,cut`() {
            report {
                test22.cut(3) to listOf(3, 4, 5, 6, 7, 8, 9, 0, 1, 2)
            }
        }

        @Test
        fun `22,1,cutNegative`() {
            report {
                test22.cutNegative(4) to listOf(6, 7, 8, 9, 0, 1, 2, 3, 4, 5)
            }
        }

        @Test
        fun `22,1,deal_increment`() {
            report {
                test22.deal_increment(3) to listOf(0, 7, 4, 1, 8, 5, 2, 9, 6, 3)
            }
        }

        @Test
        fun `22,1,routine,1`() {
            report {
                test22.deal_increment(7).deal_new().deal_new() to listOf(0, 3, 6, 9, 2, 5, 8, 1, 4, 7)
            }
        }

        @Test
        fun `22,1,routine,2`() {
            report {
                test22.cut(6).deal_increment(7).deal_new() to listOf(3, 0, 7, 4, 1, 8, 5, 2, 9, 6)
            }
        }

        @Test
        fun `22,1,routine,3`() {
            report {
                test22.deal_increment(7).deal_increment(9).cutNegative(2) to listOf(6, 3, 0, 7, 4, 1, 8, 5, 2, 9)
            }
        }

        @Test
        fun `22,1,routine,4`() {
            report {
                test22.deal_new()
                        .cutNegative(2)
                        .deal_increment(7)
                        .cut(8)
                        .cutNegative(4)
                        .deal_increment(7)
                        .cut(3)
                        .deal_increment(9)
                        .deal_increment(3)
                        .cutNegative(1) to listOf(9, 2, 5, 8, 1, 4, 7, 0, 3, 6)
            }
        }

    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `22,1,test`() {
            report {
                Day22.partOne(testInput, test22) to listOf(9, 2, 5, 8, 1, 4, 7, 0, 3, 6)
            }
        }

        @Test
        fun `22,1,live`() {
            report {
                Day22.partOne(data22, (0..10006).toList()).indexOf(2019) to 2514
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `22,2,live`() {
            report {
                Day22.partTwo(data22) to 88843646341519.toBigInteger()
            }
        }
    }
}


