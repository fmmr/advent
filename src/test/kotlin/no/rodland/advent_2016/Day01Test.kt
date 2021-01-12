package no.rodland.advent_2016

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@Suppress("ClassName")
@DisableSlow
internal class Day01Test {
    val data01 = "2016/input_01.txt".readFirstLineStrings()
    val test01 = listOf("R2", "L3")
    val test01_3 = listOf("R2", "R2", "R2")
    val test01_2 = listOf("R5", "L5", "R5", "R3")
    val test02 = listOf("R8", "R4", "R4", "R8")

    @Nested
    inner class Init {
        @Test
        fun `01,1,live,init`() {
            report {
                Day01.partOne(data01) to 250
            }
        }

        @Test
        fun `01,2,live,init`() {
            report {
                Day01.partTwo(data01) to 151
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `01,1,test`() {
            report {
                Day01.partOne(test01) to 5
            }
        }

        @Test
        fun `01,1,test,2`() {
            report {
                Day01.partOne(test01_2) to 12
            }
        }

        @Test
        fun `01,1,test,3`() {
            report {
                Day01.partOne(test01_3) to 2
            }
        }

        @Test
        fun `01,1,live,1`() {
            report {
                Day01.partOne(data01) to 250
            }
        }

        @Test
        fun `01,1,live,2`() {
            report {
                Day01.partOne(data01) to 250
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `01,2,test`() {
            report {
                Day01.partTwo(test02) to 4
            }
        }

        @Test
        fun `01,2,live,1`() {
            report {
                Day01.partTwo(data01) to 151
            }
        }

        @Test
        fun `01,2,live,2`() {
            report {
                Day01.partTwo(data01) to 151
            }
        }
    }
}
