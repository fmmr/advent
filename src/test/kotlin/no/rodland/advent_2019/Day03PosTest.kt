package no.rodland.advent_2019

import no.rodland.advent.report
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class Day03PosTest {
    @Test
    fun `03,pos,distance,1`() {
        report {
            Pos2(0, 0).distanceTo(Pos2(3, 3)) to 6
        }
    }

    @Test
    fun `03,pos,distance,2`() {
        report {
            Pos2(0, 0).distanceTo(Pos2(0, 0)) to 0
        }
    }

    @Test
    fun `03,pos,distance,3`() {
        report {
            Pos2(3, 2).distanceTo(Pos2(0, 0)) to 5
        }
    }

    @Test
    fun `03,pos,newPos,1`() {
        report {
            Pos2(3, 2).pos(Op("L50")) to Pos2(-47, 2)
        }
    }

    @Test
    fun `03,pos,newPos,2`() {
        report {
            Pos2(3, 2).pos(Op("L0")) to Pos2(3, 2)
        }
    }

    @Test
    fun `03,pos,newPos,3`() {
        report {
            Pos2(10, 10).pos(Op("L2")) to Pos2(8, 10)
        }
    }

    @Test
    fun `03,pos,newPos,4`() {
        report {
            Pos2(10, 10).pos(Op("U2")) to Pos2(10, 12)
        }
    }

    @Test
    fun `03,pos,newPos,5`() {
        report {
            Pos2(10, 10).pos(Op("D2")) to Pos2(10, 8)
        }
    }

    @Test
    fun `03,pos,newPos,6`() {
        report {
            Pos2(10, 10).pos(Op("R2")) to Pos2(12, 10)
        }
    }

    @Test
    fun `03,pos,range,1`() {
        report {
            Pos2(0, 0)..Pos2(2, 0) to listOf(Pos2(0, 0), Pos2(1, 0), Pos2(2, 0))
        }
    }

    @Test
    fun `03,pos,range,2`() {
        report {
            Pos2(2, 0)..Pos2(0, 0) to listOf(Pos2(2, 0), Pos2(1, 0), Pos2(0, 0))
        }
    }

    @Test
    fun `03,pos,range,3`() {
        report {
            Pos2(1, 1)..Pos2(1, 3) to listOf(Pos2(1, 1), Pos2(1, 2), Pos2(1, 3))
        }
    }

    @Test
    fun `03,pos,range,4`() {
        report {
            Pos2(1, 5)..Pos2(1, 2) to listOf(Pos2(1, 5), Pos2(1, 4), Pos2(1, 3), Pos2(1, 2))
        }
    }

    @Test
    fun `03,pos,range,5`() {
        Assertions.assertThrows(NotImplementedError::class.java) {
            Pos2(1, 5)..Pos2(3, 4)
        }
    }
}
