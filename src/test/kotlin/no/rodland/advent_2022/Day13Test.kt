package no.rodland.advent_2022

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import no.rodland.advent_2022.Day13.rightOrder
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@Suppress("ClassName")
@DisableSlow
internal class Day13Test {
    val data13 = "2022/input_13.txt".readFile()
    val test13 = listOf(
        "[1,1,3,1,1]",
        "[1,1,5,1,1]",
        "",
        "[[1],[2,3,4]]",
        "[[1],4]",
        "",
        "[9]",
        "[[8,7,6]]",
        "",
        "[[4,4],4,4]",
        "[[4,4],4,4,4]",
        "",
        "[7,7,7,7]",
        "[7,7,7]",
        "",
        "[]",
        "[3]",
        "",
        "[[[]]]",
        "[[]]",
        "",
        "[1,[2,[3,[4,[5,6,7]]]],8,9]",
        "[1,[2,[3,[4,[5,6,0]]]],8,9]",
    )

    val resultTestOne = 13
    val resultOne = 6235

    val resultTestTwo = 140
    val resultTwo = 22866

    @Nested
    inner class Init {
        @Test
        fun `13,1,live,init`() {
            report {
                Day13.partOne(data13) to resultOne
            }
        }

        @Test
        fun `13,2,live,init`() {
            report {
                Day13.partTwo(data13) to resultTwo
            }
        }
    }

    @Nested
    inner class Compare {
        @Test
        fun `13,1,compare,1`() {
            report {
                ("[1,1,3,1,1]" to "[1,1,5,1,1]").rightOrder() to true
            }
        }

        @Test
        fun `13,1,compare,1b`() {
            report {
                ("[1,1,3,1,9]" to "[1,1,5,1,1]").rightOrder() to true
            }
        }

        @Test
        fun `13,1,compare,1c`() {
            report {
                ("[1,1,3,1,9]" to "[1,1,5,1,1,4,3]").rightOrder() to true
            }
        }

        @Test
        fun `13,1,compare,2`() {
            report {
                ("[[1],[2,3,4,5,6]]" to "[[1],4]").rightOrder() to true
            }
        }

        @Test
        fun `13,1,compare,2a`() {
            report {
                ("[[1],[2,3]]" to "[[1],[2,3,4,5,6]]").rightOrder() to true
            }
        }

        @Test
        fun `13,1,compare,2b`() {
            report {
                ("[[1],[2,3,4]]" to "[[1],[2,3]]").rightOrder() to false
            }
        }

        @Test
        fun `13,1,compare,3`() {
            report {
                ("[9]" to "[[8,7,6]]").rightOrder() to false
            }
        }

        @Test
        fun `13,1,compare,4`() {
            report {
                ("[[4,4],4,4]" to "[[4,4],4,4,4]").rightOrder() to true
            }
        }

        @Test
        fun `13,1,compare,5`() {
            report {
                ("[7,7,7,7]" to "[7,7,7]").rightOrder() to false
            }
        }

        @Test
        fun `13,1,compare,8`() {
            report {
                ("[]" to "[3]").rightOrder() to true
            }
        }

        @Test
        fun `13,1,compare,6`() {
            report {
                ("[[[]]]" to "[[]]").rightOrder() to false
            }
        }

        @Test
        fun `13,1,compare,6b`() {
            report {
                ("[[]]" to "[[[]]]").rightOrder() to true
            }
        }

        @Test
        fun `13,1,compare,7`() {
            report {
                ("[1,[2,[3,[4,[5,6,7]]]],8,9]" to "[1,[2,[3,[4,[5,6,0]]]],8,9]").rightOrder() to false
            }
        }

    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `13,1,test`() {
            report {
                Day13.partOne(test13) to resultTestOne
            }
        }

        @Test
        fun `13,1,live,1`() {
            report {
                Day13.partOne(data13) to resultOne
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `13,2,test`() {
            report {
                Day13.partTwo(test13) to resultTestTwo
            }
        }

        @Test
        fun `13,2,live,1`() {
            report {
                Day13.partTwo(data13) to resultTwo
            }
        }
    }
}
