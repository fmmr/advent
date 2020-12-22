package no.rodland.advent_2020

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@Suppress("ClassName")
@DisableSlow
internal class Day22Test {
    val data22 = "2020/input_22.txt".readFileAsOneString()
    val test22 = """
        Player 1:
        9
        2
        6
        3
        1

        Player 2:
        5
        8
        4
        7
        10
    """.trimIndent()
    val test_loop = """
    Player 1:
    43
    19
    
    Player 2:
    2
    29
    14
    """.trimIndent()

    @Nested
    inner class Init {
        @Test
        fun `22,1,live,init`() {
            report {
                Day22.partOne(data22) to 32401
            }
        }

        @Test
        fun `22,2,live,init`() {
            report {
                Day22.partTwo(data22) to 31436
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `22,1,test`() {
            report {
                Day22.partOne(test22) to 306
            }
        }

        @Test
        fun `22,1,live,1`() {
            report {
                Day22.partOne(data22) to 32401
            }
        }

        @Test
        fun `22,1,live,2`() {
            report {
                Day22.partOne(data22) to 32401
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `22,2,test`() {
            report {
                Day22.partTwo(test22) to 291
            }
        }

        @Test
        fun `22,2,test,loop`() {
            report {
                Day22.partTwo(test_loop) to 105
            }
        }

        @Test
        fun `22,2,live,1`() {
            report {
                Day22.partTwo(data22) to 31436
            }
        }

        @Test
        fun `22,2,live,2`() {
            report {
                Day22.partTwo(data22) to 31436
            }
        }
    }
}
