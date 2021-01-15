package no.rodland.advent_2016

import no.rodland.advent.DisableSlow
import no.rodland.advent.Pos
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@Suppress("ClassName")
@DisableSlow
internal class Day13Test {


    @Nested
    inner class `Part 1` {
        @Test
        fun `13,1,test`() {
            report {
                Day13.partOne(10, Pos(7, 4)) to 11
            }
        }

        @Test
        fun `13,1,live,1`() {
            report {
                Day13.partOne(1358, Pos(31, 39)) to 96
            }
        }
    }

//    @Nested
//    inner class `Part 2` {
//        @Test
//        fun `13,2,test`() {
//            report {
//                Day13.partTwo(test13) to 2
//            }
//        }
//
//        @Test
//        fun `13,2,live,1`() {
//            report {
//                Day13.partTwo(data13) to 2
//            }
//        }
//
//        @Test
//        fun `13,2,live,2`() {
//            report {
//                Day13.partTwo(data13) to 2
//            }
//        }
//    }
}
