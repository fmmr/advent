package no.rodland.advent_2017

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@DisableSlow
internal class Day11Test {
    val data11 = "2017/input_11.txt".readFile().first()

    @Nested
    inner class `Part 1` {
        @Test
        fun `11,1,test,1`() {
            report {
                Day11.partOne("ne,ne,ne") to 3
            }
        }

        @Test
        fun `11,1,test,2`() {
            report {
                Day11.partOne("ne,ne,sw,sw") to 0
            }
        }

        @Test
        fun `11,1,test,3`() {
            report {
                Day11.partOne("ne,ne,s,s") to 2
            }
        }

        @Test
        fun `11,1,test,4`() {
            report {
                Day11.partOne("se,sw,se,sw,sw") to 3
            }
        }

        @Test
        fun `11,1,live`() {
            report {
                Day11.partOne(data11) to 808
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `11,2,test`() {
            report {
                Day11.partTwo("ne,ne,ne") to 3
            }
        }

        @Test
        fun `11,2,live`() {
            report {
                Day11.partTwo(data11) to 1556
            }
        }
    }
}


