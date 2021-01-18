package no.rodland.advent_2016

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@Suppress("ClassName")
@DisableSlow
internal class Day17Test {
    val data17 = "dmypynyp"

    @Nested
    inner class Init {
        @Test
        fun `17,1,live,init`() {
            report {
                Day17.partOne(data17) to "RDRDUDLRDR"
            }
        }

        @Test
        fun `17,2,live,init`() {
            report {
                Day17.partTwo(data17) to 386
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `17,1,test,1`() {
            report {
                Day17.partOne("ihgpwlah") to "DDRRRD"
            }
        }

        @Test
        fun `17,1,test,2`() {
            report {
                Day17.partOne("kglvqrro") to "DDUDRLRRUDRD"
            }
        }

        @Test
        fun `17,1,test,3`() {
            report {
                Day17.partOne("ulqzkmiv") to "DRURDRUDDLLDLUURRDULRLDUUDDDRR"
            }
        }

        @Test
        fun `17,1,live,1`() {
            report {
                Day17.partOne(data17) to "RDRDUDLRDR"
            }
        }

        @Test
        fun `17,1,live,2`() {
            report {
                Day17.partOne(data17) to "RDRDUDLRDR"
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `17,1,test,1`() {
            report {
                Day17.partTwo("ihgpwlah") to 370
            }
        }

        @Test
        fun `17,1,test,2`() {
            report {
                Day17.partTwo("kglvqrro") to 492
            }
        }


        @Test
        fun `17,1,test,3`() {
            report {
                Day17.partTwo("ulqzkmiv") to 830
            }
        }


        @Test
        fun `17,2,live,1`() {
            report {
                Day17.partTwo(data17) to 386
            }
        }

        @Test
        fun `17,2,live,2`() {
            report {
                Day17.partTwo(data17) to 386
            }
        }
    }
}
