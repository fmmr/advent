package no.rodland.advent_2018

import Day11
import Day11.getHundred
import no.rodland.advent.DisableSlow
import no.rodland.advent.Slow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisableSlow
internal class Day11Test {

    @Nested
    inner class `Part 1` {
        @Test
        fun `11,1,test`() {
            report {
                Day11.partOne(42) to (21 to 61)
            }
        }

        @Test
        fun `11,1,test,2`() {
            report {
                Day11.partOne(18) to (33 to 45)
            }
        }

        @Test
        fun `11,1,live`() {
            report {
                Day11.partOne(9798) to (44 to 37)
            }
        }

        @Test
        fun `11,1,getHundred`() {
            report {
                Day11.getHundred(42) to 0
            }
        }

        @Test
        fun `11,1,getHundred,2`() {
            report {
                Day11.getHundred(12342) to 3
            }
        }

        @Test
        fun `11,1,getHundred,3`() {
            report {
                Day11.getHundred(100) to 1
            }
        }

        @Test
        fun `11,1,getHundred,4`() {
            report {
                Day11.getHundred(100000) to 0
            }
        }

        @Test
        fun `11,1,getLevel`() {
            report {
                getHundred(((3 + 10) * 5 + 8) * (3 + 10)) - 5 to 4
            }
        }

        @Test
        fun `11,1,getLevel,2`() {
            report {
                getHundred(((217 + 10) * 196 + 39) * (217 + 10)) - 5 to 0
            }
        }

        @Test
        fun `11,1,getLevel,3`() {
            report {
                getHundred(((101 + 10) * 153 + 71) * (101 + 10)) - 5 to 4
            }
        }

        @Test
        fun `11,1,getLevel,4`() {
            report {
                getHundred(((122 + 10) * 79 + 57) * (122 + 10)) - 5 to -5
            }
        }

    }

    @Nested
    inner class `Part 2` {
        @Test
        @Slow(3100)
        fun `11,2,test`() {
            report {
                Day11.partTwo(18, 15, 17) to ((90 to 269) to 16)
            }
        }

        @Test
        @Slow(10000)
        fun `11,2,test,2`() {
            report {
                Day11.partTwo(18, 1, 20) to ((90 to 269) to 16)
            }
        }

        @Test
        @Slow(999999)
        fun `11,2,test,3`() {
            report {
                Day11.partTwo(9798, 1, 300) to ((90 to 269) to 16)
            }
        }

        @Test
        @Slow(2400)
        fun `11,2,live`() {
            report {
                Day11.partTwo(9798, 12, 14) to ((235 to 87) to 13)
            }
        }

        @Test
        @Slow(27800)
        fun `11,2,live,2`() {
            report {
                Day11.partTwo(9798, 1, 30) to ((235 to 87) to 13)
            }
        }
    }
}


