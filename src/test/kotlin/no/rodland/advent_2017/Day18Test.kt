package no.rodland.advent_2017

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@Suppress("ClassName")
@DisableSlow
internal class Day18Test {
    val data18 = "2017/input_18.txt".readFile()
    val test18 = listOf(
            "set a 1",
            "add a 2",
            "mul a a",
            "mod a 5",
            "snd a",
            "set a 0",
            "rcv a",
            "jgz a -1",
            "set a 1",
            "jgz a -2",
    )

    @Nested
    inner class Init {
        @Test
        fun `18,1,live,init`() {
            report {
                Day18.partOne(data18) to 3188
            }
        }

        @Test
        fun `18,2,live,init`() {
            report {
                Day18.partTwo(data18) to 2
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `18,1,test`() {
            report {
                Day18.partOne(test18) to 4
            }
        }

        @Test
        fun `18,1,live,1`() {
            report {
                Day18.partOne(data18) to 3188
            }
        }

        @Test
        fun `18,1,live,2`() {
            report {
                Day18.partOne(data18) to 3188
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `18,2,test`() {
            report {
                Day18.partTwo(test18) to 2
            }
        }

        @Test
        fun `18,2,live,1`() {
            report {
                Day18.partTwo(data18) to 2
            }
        }

        @Test
        fun `18,2,live,2`() {
            report {
                Day18.partTwo(data18) to 2
            }
        }
    }
}
