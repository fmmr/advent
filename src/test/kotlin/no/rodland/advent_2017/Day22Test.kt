package no.rodland.advent_2017

import no.rodland.advent.DisableSlow
import no.rodland.advent.Slow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@Suppress("ClassName")
@DisableSlow
internal class Day22Test {
    val data22 = "2017/input_22.txt".readFile()
    val test22 = listOf(
            "..#",
            "#..",
            "...",
    )

    @Nested
    inner class Init {
        @Test
        fun `22,1,live,init`() {
            report {
                Day22.partOne(data22, 10000) to 5322
            }
        }

//        @Test
//        fun `22,2,live,init`() {
//            report {
//                Day22.partTwo(data22, 10000000) to 2
//            }
//        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `22,1,test`() {
            report {
                Day22.partOne(test22, 70) to 41
            }
        }

        @Test
        fun `22,1,test,2`() {
            report {
                Day22.partOne(test22, 10000) to 5587
            }
        }

        @Test
        fun `22,1,live,1`() {
            report {
                Day22.partOne(data22, 10000) to 5322
            }
        }

        @Test
        fun `22,1,live,2`() {
            report {
                Day22.partOne(data22, 10000) to 5322
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `22,2,test`() {
            report {
                Day22.partTwo(test22, 100) to 26
            }
        }

        @Test
        @Slow(1200)
        fun `22,2,test,1`() {
            report {
                Day22.partTwo(test22, 10_000_000) to 2511944
            }
        }

        @Test
        @Slow(1800)
        fun `22,2,live,2`() {
            report {
                Day22.partTwo(data22, 10_000_000) to 2512079
            }
        }
    }
}
