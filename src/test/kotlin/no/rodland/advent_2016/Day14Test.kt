package no.rodland.advent_2016

import no.rodland.advent.DisableSlow
import no.rodland.advent.Slow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@Suppress("ClassName")
@DisableSlow
internal class Day14Test {
    val data14 = "zpqevtbw"
    val test14 = "abc"

    @Nested
    inner class `Part 1` {
        @Test
        fun `14,1,regex`() {
            report {
                Day14.threeInARow.matches("dsjkdsd777tsdhjsd") to true
            }
        }

        @Test
        fun `14,1,group`() {
            report {
                val find = Day14.threeInARow.find("dsjkdsd777tsdhjsd")
                find!!.groupValues[1].first() to '7'
            }
        }

        @Test
        fun `14,1,next_false`() {
            report {
                Day14.nextThousandContainsFiveInARow("0034e0923cc38887a57bd7b1d4f953df", "abc", 40, Day14::hashPart1) to false
            }
        }

        @Test
        fun `14,1,next_true`() {
            report {
                Day14.nextThousandContainsFiveInARow("347dac6ee8eeea4652c7476d0f97bee5", "abc", 40, Day14::hashPart1) to true
            }
        }

        @Test
        @Slow(1700)
        fun `14,1,test`() {
            report {
                Day14.partOne(test14) to 22728
            }
        }

        @Test
        @Slow(1200)
        fun `14,1,live,1`() {
            report {
                Day14.partOne(data14) to 16106
            }
        }

    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `14,1,regex`() {
            report {
                Day14.hashPart2("abc", 0) to "a107ff634856bb300138cac6568c0f24"
            }
        }

        @Test
        @Slow(15800)
        fun `14,2,test`() {
            report {
                Day14.partTwo(test14) to 22551
            }
        }

        @Test
        @Slow(18000)
        fun `14,2,live,1`() {
            report {
                Day14.partTwo(data14) to 26454
            }
        }
    }
}
