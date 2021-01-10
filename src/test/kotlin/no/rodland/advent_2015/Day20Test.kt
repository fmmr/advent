package no.rodland.advent_2015

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@Suppress("ClassName")
@DisableSlow
internal class Day20Test {
    val data20 = 29000000L

    @Nested
    inner class Init {
        @Test
        fun `20,1,live,init`() {
            report {
                Day20.partOne(data20) to 665280
            }
        }

        @Test
        fun `20,2,live,init`() {
            report {
                Day20.partTwo(data20) to 705600
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `20,1,test,10`() {
            report {
                Day20.presents(10).second to 180
            }
        }

        @Test
        fun `20,1,test,12`() {
            report {
                Day20.presents(12).second to 280
            }
        }

        @Test
        fun `20,1,test,8`() {
            report {
                Day20.presents(8).second to 150
            }
        }

        @Test
        fun `20,1,test,1`() {
            report {
                Day20.presents(1).second to 10
            }
        }

        @Test
        fun `20,1,test,2`() {
            report {
                Day20.presents(2).second to 30
            }
        }

        @Test
        fun `20,1,test,3`() {
            report {
                Day20.presents(3).second to 40
            }
        }

        @Test
        fun `20,1,test,9`() {
            report {
                Day20.presents(9).second to 130
            }
        }

        @Test
        fun `20,1,live,1`() {
            report {
                Day20.partOne(data20) to 665280
            }
        }

        @Test
        fun `20,1,live,2`() {
            report {
                Day20.partOne(data20) to 665280
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `20,2,live,1`() {
            report {
                Day20.partTwo(data20) to 705600
            }
        }

        @Test
        fun `20,2,live,2`() {
            report {
                Day20.partTwo(data20) to 705600
            }
        }
    }
}
