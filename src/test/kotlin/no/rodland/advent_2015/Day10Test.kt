package no.rodland.advent_2015

import no.rodland.advent.DisableSlow
import no.rodland.advent.Slow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@Suppress("ClassName")
@DisableSlow
internal class Day10Test {
    val data10 = "1113222113"

    @Nested
    inner class Init {
        @Test
        fun `10,1,live,init`() {
            report {
                Day10.partOne(data10, 40) to 252594
            }
        }

        @Test
        @Slow(700)
        fun `10,2,live,init`() {
            report {
                Day10.partOne(data10, 50) to 3579328
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `10,1,test,1`() {
            report {
                Day10.next("1") to "11"
            }
        }

        @Test
        fun `10,1,test,2`() {
            report {
                Day10.next("11") to "21"
            }
        }

        @Test
        fun `10,1,test,3`() {
            report {
                Day10.next("21") to "1211"
            }
        }

        @Test
        fun `10,1,test,4`() {
            report {
                Day10.next("1211") to "111221"
            }
        }

        @Test
        fun `10,1,test,5`() {
            report {
                Day10.next("111221") to "312211"
            }
        }

        @Test
        fun `10,1,live,1`() {
            report {
                Day10.partOne(data10, 40) to 252594
            }
        }

        @Test
        fun `10,1,live,2`() {
            report {
                Day10.partOne(data10, 40) to 252594
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        @Slow(700)
        fun `10,2,live,1`() {
            report {
                Day10.partOne(data10, 50) to 3579328
            }
        }
    }
}
