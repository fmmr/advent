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
                Day05.partOne(listOf(1002, 4, 3, 4, 33)) to 7157989
            }
        }

        @Test
        fun `05,1,test,2`() {
            report {
                Day05.partOne(listOf(1101, 100, -1, 4, 0)) to 7157989
            }
        }

        @Test
        fun `05,1,live`() {
            report {
                Day05.partOne(data05) to 7157989
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `05,2,test,1`() {
            report {
                Day05.partOne(listOf(3, 9, 8, 9, 10, 9, 4, 9, 99, -1, 8), 8) to 1
            }
        }

        @Test
        fun `05,2,test,2`() {
            report {
                Day05.partOne(listOf(3, 9, 8, 9, 10, 9, 4, 9, 99, -1, 8), 7) to 0
            }
        }

        @Test
        fun `05,2,test,3`() {
            report {
                Day05.partOne(listOf(3, 9, 8, 9, 10, 9, 4, 9, 99, -1, 8), 9) to 0
            }
        }

        @Test
        fun `05,2,test,4`() {
            report {
                Day05.partOne(listOf(3, 9, 7, 9, 10, 9, 4, 9, 99, -1, 8), 7) to 1
            }
        }

        @Test
        fun `05,2,test,5`() {
            report {
                Day05.partOne(listOf(3, 9, 7, 9, 10, 9, 4, 9, 99, -1, 8), 8) to 0
            }
        }

        @Test
        fun `05,2,test,6`() {
            report {
                Day05.partOne(listOf(3, 9, 7, 9, 10, 9, 4, 9, 99, -1, 8), 9) to 0
            }
        }

        @Test
        fun `05,2,test,7`() {
            report {
                Day05.partOne(listOf(3, 3, 1108, -1, 8, 3, 4, 3, 99), 7) to 0
            }
        }

        @Test
        fun `05,2,test,8`() {
            report {
                Day05.partOne(listOf(3, 3, 1108, -1, 8, 3, 4, 3, 99), 8) to 1
            }
        }

        @Test
        fun `05,2,test,9`() {
            report {
                Day05.partOne(listOf(3, 3, 1108, -1, 8, 3, 4, 3, 99), 9) to 0
            }
        }

        @Test
        fun `05,2,test,10`() {
            report {
                Day05.partOne(listOf(3, 3, 1107, -1, 8, 3, 4, 3, 99), 7) to 1
            }
        }

        @Test
        fun `05,2,test,11`() {
            report {
                Day05.partOne(listOf(3, 3, 1107, -1, 8, 3, 4, 3, 99), 8) to 0
            }
        }

        @Test
        fun `05,2,test,12`() {
            report {
                Day05.partOne(listOf(3, 3, 1107, -1, 8, 3, 4, 3, 99), 9) to 0
            }
        }

        @Test
        fun `05,2,test,20`() {
            report {
                Day05.partOne(listOf(3, 12, 6, 12, 15, 1, 13, 14, 13, 4, 13, 99, -1, 0, 1, 9), 9) to 1
            }
        }

        @Test
        fun `05,2,test,21`() {
            report {
                Day05.partOne(listOf(3, 12, 6, 12, 15, 1, 13, 14, 13, 4, 13, 99, -1, 0, 1, 9), 0) to 0
            }
        }

        @Test
        fun `05,2,test,22`() {
            report {
                Day05.partOne(listOf(3, 3, 1105, -1, 9, 1101, 0, 0, 12, 4, 12, 99, 1), 9) to 1
            }
        }

        @Test
        fun `05,2,test,23`() {
            report {
                Day05.partOne(listOf(3, 3, 1105, -1, 9, 1101, 0, 0, 12, 4, 12, 99, 1), 0) to 0
            }
        }

        @Test
        fun `05,2,live`() {
            report {
                Day05.partOne(data05, start = 5) to 7873292
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


}


