package no.rodland.advent_2019

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisableSlow
internal class Day05Test {
    val data05 = "2019/input_05.txt".readFirstLineInts()

    @Nested
    inner class `Part 1` {
        @Test
        fun `05,1,test,1`() {
            report {
                Day05.partOne(listOf(1002, 4, 3, 4, 33)) to listOf(1002, 4, 3, 4, 99)
            }
        }

        @Test
        fun `05,1,test,2`() {
            report {
                Day05.partOne(listOf(1101, 100, -1, 4, 0)) to listOf(1101, 100, -1, 4, 99)
            }
        }

        @Test
        fun `05,1,live`() {
            report {
                Day05.partOne(data05) to 2
            }
        }
    }

    @Nested
    inner class `Op` {
        @Test
        fun `05,op,steps,1`() {
            report {
                Operation(2).operation to 2
            }
        }

        @Test
        fun `05,op,steps,2`() {
            report {
                Operation(102).operation to 2
            }
        }

        @Test
        fun `05,op,steps,3`() {
            report {
                Operation(1002).operation to 2
            }
        }

        @Test
        fun `05,op,steps,4`() {
            report {
                Operation(1002).steps to 4
            }
        }

        @Test
        fun `05,op,test,5`() {
            report {
                Operation(1).steps to 4
            }
        }

        @Test
        fun `05,op,steps,6`() {
            report {
                Operation(103).steps to 2
            }
        }

        @Test
        fun `05,op,mode,1`() {
            report {
                val code = 2
                listOf(Operation(code).mode(1), Operation(code).mode(1), Operation(code).mode(1)) to
                        listOf(0, 0, 0)
            }
        }

        @Test
        fun `05,op,mode,2`() {
            report {
                val code = 1002
                listOf(Operation(code).mode(1), Operation(code).mode(2), Operation(code).mode(3)) to
                        listOf(0, 1, 0)
            }
        }

        @Test
        fun `05,op,mode,3`() {
            report {
                val code = 11002
                listOf(Operation(code).mode(1), Operation(code).mode(2), Operation(code).mode(3)) to
                        listOf(0, 1, 1)
            }
        }

        @Test
        fun `05,op,mode,4`() {
            report {
                val code = 11101
                listOf(Operation(code).mode(1), Operation(code).mode(2), Operation(code).mode(3)) to
                        listOf(1, 1, 1)
            }
        }

    }

//    @Nested
//    inner class `Part 2` {
//        @Test
//        fun `05,2,test`() {
//            report {
//                Day05.partTwo(test05) to 2
//            }
//        }
//
//        @Test
//        fun `05,2,live`() {
//            report {
//                Day05.partTwo(data05) to 2
//            }
//        }
//    }
}


