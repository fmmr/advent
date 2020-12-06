package no.rodland.advent_2015

import no.rodland.advent.DisableSlow
import no.rodland.advent.Slow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@Suppress("ClassName")
@DisableSlow
internal class Day04Test {
    val data04 = "ckczppom"
    val test04 = "abcdef"

    @Nested
    inner class `Part 1` {
        @Test
        @Slow(5000)
        fun `04,1,test,1`() {
            report {
                Day04.partOne("abcdef") to 609043
            }
        }

        @Test
        @Slow(37000)
        fun `04,1,test,2`() {
            report {
                Day04.partOne("pqrstuv") to 1048970
            }
        }

        @Test
        @Slow(17000)
        fun `04,1,live,1`() {
            report {
                Day04.partOne(data04) to 117946
            }
        }

    }

    @Nested
    inner class `Part 2` {
        @Test
        @Slow(63000)
        fun `04,2,test`() {
            report {
                Day04.partTwo(test04) to 2
            }
        }

        @Test
        @Slow(30000)
        fun `04,2,live,2`() {
            report {
                Day04.partTwo(data04) to 2
            }
        }
    }
}
