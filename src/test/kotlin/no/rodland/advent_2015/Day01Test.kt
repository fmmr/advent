package no.rodland.advent_2015

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@Suppress("ClassName")
@DisableSlow
internal class Day01Test {
    val data01 = "2015/input_01.txt".readFileAsOneString()

    @Nested
    inner class `Part 1` {
        @Test
        fun `01,1,test,1`() {
            report {
                Day01.partOne("(())") to 0
            }
        }

        @Test
        fun `01,1,test,2`() {
            report {
                Day01.partOne("()()") to 0
            }
        }

        @Test
        fun `01,1,test,3`() {
            report {
                Day01.partOne("(()(()(") to 3
            }
        }

        @Test
        fun `01,1,test,4`() {
            report {
                Day01.partOne("(((") to 3
            }
        }

        @Test
        fun `01,1,test,5`() {
            report {
                Day01.partOne("))(((((") to 3
            }
        }

        @Test
        fun `01,1,test,6`() {
            report {
                Day01.partOne("())") to -1
            }
        }

        @Test
        fun `01,1,test,7`() {
            report {
                Day01.partOne("))(") to -1
            }
        }

        @Test
        fun `01,1,test,8`() {
            report {
                Day01.partOne(")))") to -3
            }
        }

        @Test
        fun `01,1,test,9`() {
            report {
                Day01.partOne(")())())") to -3
            }
        }

        @Test
        fun `01,1,live`() {
            report {
                Day01.partOne(data01) to 74
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `01,2,test,1`() {
            report {
                Day01.partTwo(")") to 1
            }
        }

        @Test
        fun `01,2,test,2`() {
            report {
                Day01.partTwo("()())") to 5
            }
        }

        @Test
        fun `01,2,live`() {
            report {
                Day01.partTwo(data01) to 1795
            }
        }
    }
}
