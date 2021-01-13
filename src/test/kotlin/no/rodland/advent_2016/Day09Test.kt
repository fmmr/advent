package no.rodland.advent_2016

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@Suppress("ClassName")
@DisableSlow
internal class Day09Test {
    val data09 = "2016/input_09.txt".readFileAsOneString()

    @Nested
    inner class Init {
        @Test
        fun `09,1,live,init`() {
            report {
                Day09.partOne(data09) to 123908
            }
        }

        @Test
        fun `09,2,live,init`() {
            report {
                Day09.partTwo(data09) to 10755693147
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `09,1,test`() {
            report {
                Day09.partOne("A(1x5)BC") to 7
            }
        }

        @Test
        fun `09,1,test,1`() {
            report {
                Day09.decompressPart1("ADVENT") to "ADVENT"
            }
        }

        @Test
        fun `09,1,test,3`() {
            report {
                Day09.decompressPart1("A(1x5)BC") to "ABBBBBC"
            }
        }

        @Test
        fun `09,1,test,4`() {
            report {
                Day09.decompressPart1("(3x3)XYZ") to "XYZXYZXYZ"
            }
        }

        @Test
        fun `09,1,test,5`() {
            report {
                Day09.decompressPart1("A(2x2)BCD(2x2)EFG") to "ABCBCDEFEFG"
            }
        }

        @Test
        fun `09,1,test,6`() {
            report {
                Day09.decompressPart1("(6x1)(1x3)A") to "(1x3)A"
            }
        }

        @Test
        fun `09,1,test,7`() {
            report {
                Day09.decompressPart1("X(8x2)(3x3)ABCY") to "X(3x3)ABC(3x3)ABCY"
            }
        }

        @Test
        fun `09,1,live,1`() {
            report {
                Day09.partOne(data09) to 123908
            }
        }

        @Test
        fun `09,1,live,2`() {
            report {
                Day09.partOne(data09) to 123908
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `09,2,test,1`() {
            report {
                Day09.partTwo("(3x3)XYZ") to "XYZXYZXYZ".length.toLong()
            }
        }

        @Test
        fun `09,2,test,2`() {
            report {
                Day09.partTwo("X(8x2)(3x3)ABCY") to "XABCABCABCABCABCABCY".length.toLong()
            }
        }

        @Test
        fun `09,2,test,3`() {
            report {
                Day09.partTwo("(27x12)(20x12)(13x14)(7x10)(1x12)A") to 241920
            }
        }

        @Test
        fun `09,2,test,4`() {
            report {
                Day09.partTwo("(25x3)(3x3)ABC(2x3)XY(5x2)PQRSTX(18x9)(3x2)TWO(5x7)SEVEN") to 445
            }
        }

        @Test
        fun `09,2,live,1`() {
            report {
                Day09.partTwo(data09) to 10755693147
            }
        }

        @Test
        fun `09,2,live,2`() {
            report {
                Day09.partTwo(data09) to 10755693147
            }
        }
    }
}
