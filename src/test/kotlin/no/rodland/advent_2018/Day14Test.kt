package no.rodland.advent_2018

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@DisableSlow
internal class Day14Test {
    val data14 = "2018/input_14.txt".readFile()
    val test14 = listOf("1", "2")

    @Nested
    inner class newpos {
        @Test
        fun `14,1,newPos`() {
            report {
                Day14.newPos(3, 0, 4) to 0
            }
        }

        @Test
        fun `14,1,newPos,2`() {
            report {
                Day14.newPos(1, 5, 20) to 7
            }
        }


        @Test
        fun `14,1,newPos,3`() {
            report {
                Day14.newPos(3, 0, 6) to 4
            }
        }

        @Test
        fun `14,1,newPos,4`() {
            report {
                Day14.newPos(7, 1, 6) to 3
            }
        }

        @Test
        fun `14,1,newPos,5`() {
            report {
                Day14.newPos(7, 1, 13) to 9
            }
        }

    }

    @Nested
    inner class listDigits {
        @Test
        fun `14,1,listdigits`() {
            report {
                Day14.toListOfDigits(8) to listOf(8)
            }
        }

        @Test
        fun `14,1,listdigits,2`() {
            report {
                Day14.toListOfDigits(18) to listOf(1, 8)
            }
        }

        @Test
        fun `14,1,listdigits,3`() {
            report {
                Day14.toListOfDigits(0) to listOf(0)
            }
        }
    }

    @Nested
    inner class `Part 1` {

        @Test
        fun `14,1,test`() {
            report {
                Day14.partOne(9) to "5158916779"
            }
        }

        @Test
        fun `14,1,test,2`() {
            report {
                Day14.partOne(5) to "0124515891"
            }
        }

        @Test
        fun `14,1,test,3`() {
            report {
                Day14.partOne(18) to "9251071085"
            }
        }

        @Test
        fun `14,1,test,4`() {
            report {
                Day14.partOne(2018) to "5941429882"
            }
        }

        @Test
        fun `14,1,live`() {
            report {
                Day14.partOne(320851) to "7116398711"
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `14,2,test`() {
            report {
                Day14.partTwo(test14) to 2
            }
        }

        @Test
        fun `14,2,live`() {
            report {
                Day14.partTwo(data14) to 2
            }
        }
    }
}


