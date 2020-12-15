package no.rodland.advent_2020

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@Suppress("ClassName")
@DisableSlow
internal class Day15Test {
    val data15 = listOf(12, 1, 16, 3, 11, 0)

    @Nested
    inner class Init {
        @Test
        fun `15,1,live,init`() {
            report {
                Day15.partOne(data15) to 1696
            }
        }

        @Test
        fun `15,2,live,init`() {
            report {
                Day15.partTwo(data15) to 2
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `15,1,test`() {
            report {
                Day15.partOne(listOf(0, 3, 6)) to 436
            }
        }

        @Test
        fun `15,1,test,1`() {
            report {
                Day15.partOne(listOf(1, 3, 2)) to 1
            }
        }

        @Test
        fun `15,1,test,2`() {
            report {
                Day15.partOne(listOf(2, 1, 3)) to 10
            }
        }

        @Test
        fun `15,1,test,3`() {
            report {
                Day15.partOne(listOf(1, 2, 3)) to 27
            }
        }

        @Test
        fun `15,1,test,4`() {
            report {
                Day15.partOne(listOf(2, 3, 1)) to 78
            }
        }

        @Test
        fun `15,1,test,5`() {
            report {
                Day15.partOne(listOf(3, 2, 1)) to 438
            }
        }

        @Test
        fun `15,1,test,6`() {
            report {
                Day15.partOne(listOf(3, 1, 2)) to 1836
            }
        }

        @Test
        fun `15,1,live,1`() {
            report {
                Day15.partOne(data15) to 1696
            }
        }

        @Test
        fun `15,1,live,2`() {
            report {
                Day15.partOne(data15) to 1696
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `15,2,test`() {
            report {
                Day15.partTwo(listOf(0, 3, 6)) to 2// 175594
            }
        }

        @Test
        fun `15,2,live,1`() {
            report {
                Day15.partTwo(data15) to 2
            }
        }

        @Test
        fun `15,2,live,2`() {
            report {
                Day15.partTwo(data15) to 2
            }
        }
    }
}
