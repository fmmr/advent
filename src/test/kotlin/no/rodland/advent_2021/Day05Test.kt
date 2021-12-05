package no.rodland.advent_2021

import no.rodland.advent.DisableSlow
import no.rodland.advent.Pos
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@Suppress("ClassName")
@DisableSlow
internal class Day05Test {
    val data05 = "2021/input_05.txt".readFile()
    val test05 = listOf(
        "0,9 -> 5,9",
        "8,0 -> 0,8",
        "9,4 -> 3,4",
        "2,2 -> 2,1",
        "7,0 -> 7,4",
        "6,4 -> 2,0",
        "0,9 -> 2,9",
        "3,4 -> 1,4",
        "0,0 -> 8,8",
        "5,5 -> 8,2",
    )

    @Nested
    inner class Init {
        @Test
        fun `05,1,live,init`() {
            report {
                Day05.partOne(data05) to 5092
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `05,1,test`() {
            report {
                Day05.partOne(test05) to 5
            }
        }

        @Test
        fun `05,1,live,1`() {
            report {
                Day05.partOne(data05) to 5092
            }
        }

        @Test
        fun `05,1,live,2`() {
            report {
                Day05.partOne(data05) to 5092
            }
        }
    }

    @Nested
    inner class `All Pos` {
        @Test
        fun `05,2,allpos,straight`() {
            report {
                Day05.Vent(Pos(1, 1), Pos(3, 1)).allPos() to listOf(Pos(1, 1), Pos(2, 1), Pos(3, 1))
            }
        }

        @Test
        fun `05,2,allpos,down`() {
            report {
                Day05.Vent(Pos(1, 1), Pos(1, 3)).allPos() to listOf(Pos(1, 1), Pos(1, 2), Pos(1, 3))
            }
        }

        @Test
        fun `05,2,allpos,down left`() {
            report {
                Day05.Vent(Pos(7, 9), Pos(5, 7)).allPos() to listOf(Pos(7, 9), Pos(6, 8), Pos(5, 7))
            }
        }

        @Test
        fun `05,2,allpos,up right`() {
            report {
                Day05.Vent(Pos(1, 1), Pos(3, 3)).allPos() to listOf(Pos(1, 1), Pos(2, 2), Pos(3, 3))
            }
        }

        @Test
        fun `05,2,allpos,down right`() {
            report {
                Day05.Vent(Pos(3, 3), Pos(1, 1)).allPos() to listOf(Pos(3, 3), Pos(2, 2), Pos(1, 1))
            }
        }

        @Test
        fun `05,2,allpos,up left`() {
            report {
                Day05.Vent(Pos(9, 7), Pos(7, 9)).allPos() to listOf(Pos(9, 7), Pos(8, 8), Pos(7, 9))
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `05,2,test`() {
            report {
                Day05.partTwo(test05) to 12
            }
        }


        @Test
        fun `05,2,live,1`() {
            report {
                Day05.partTwo(data05) to 20484
            }
        }

        @Test
        fun `05,2,live,2`() {
            report {
                Day05.partTwo(data05) to 20484
            }
        }
    }
}
