package no.rodland.advent_2016

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@Suppress("ClassName")
@DisableSlow
internal class Day07Test {
    val data07 = "2016/input_07.txt".readFile()
    val test07 = listOf(
            "abba[mnop]qrst",
            "abcd[bddb]xyyx",
            "aaaa[qwer]tyui",
            "ioxxoj[asdfgh]zxcvbn",
    )

    val test07_2 = listOf(
            "aba[bab]xyz",
            "xyx[xyx]xyx",
            "aaa[kek]eke",
            "zazbz[bzb]cdb",
    )

    @Nested
    inner class Init {
        @Test
        fun `07,1,live,init`() {
            report {
                Day07.partOne(data07) to 110
            }
        }

        @Test
        fun `07,2,live,init`() {
            report {
                Day07.partTwo(data07) to 242
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `07,1,test`() {
            report {
                Day07.partOne(test07) to 2
            }
        }

        @Test
        fun `07,1,live,1`() {
            report {
                Day07.partOne(data07) to 110
            }
        }

        @Test
        fun `07,1,live,2`() {
            report {
                Day07.partOne(data07) to 110
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `07,2,test`() {
            report {
                Day07.partTwo(test07_2) to 3
            }
        }

        @Test
        fun `07,2,live,1`() {
            report {
                Day07.partTwo(data07) to 242
            }
        }

        @Test
        fun `07,2,live,2`() {
            report {
                Day07.partTwo(data07) to 242
            }
        }
    }
}
