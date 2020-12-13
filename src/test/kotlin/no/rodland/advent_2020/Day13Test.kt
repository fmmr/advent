package no.rodland.advent_2020

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@Suppress("ClassName")
@DisableSlow
internal class Day13Test {
    val data13 = "2020/input_13.txt".readFile()
    val test13 = listOf("939", "7,13,x,x,59,x,31,19")

    @Nested
    inner class Init {
        @Test
        fun `13,1,live,init`() {
            report {
                Day13.partOne(data13) to 2947
            }
        }

        @Test
        fun `13,2,live,init`() {
            report {
                Day13.partTwo(data13) to 526090562196173
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `13,1,test`() {
            report {
                Day13.partOne(test13) to 295
            }
        }

        @Test
        fun `13,1,live,1`() {
            report {
                Day13.partOne(data13) to 2947
            }
        }

        @Test
        fun `13,1,live,2`() {
            report {
                Day13.partOne(data13) to 2947
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `13,2,test,1`() {
            report {
                Day13.partTwo(test13) to 1068781
            }
        }

        @Test
        fun `13,2,test,2`() {
            report {
                Day13.partTwo(listOf("", "17,x,13,19")) to 3417
            }
        }

        @Test
        fun `13,2,test,3`() {
            report {
                Day13.partTwo(listOf("", "67,7,59,61")) to 754018
            }
        }

        @Test
        fun `13,2,test,4`() {
            report {
                Day13.partTwo(listOf("", "67,x,7,59,61")) to 779210
            }
        }

        @Test
        fun `13,2,test,5`() {
            report {
                Day13.partTwo(listOf("", "67,7,x,59,61")) to 1261476
            }
        }

        @Test
        fun `13,2,test,6`() {
            report {
                Day13.partTwo(listOf("", "1789,37,47,1889")) to 1202161486
            }
        }

        @Test
        fun `13,2,live,1`() {
            report {
                Day13.partTwo(data13) to 526090562196173
            }
        }

        @Test
        fun `13,2,live,2`() {
            report {
                Day13.partTwo(data13) to 526090562196173
            }
        }
    }
}
