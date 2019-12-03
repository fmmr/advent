package no.rodland.advent_2019

import no.rodland.advent.report
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class Day03PosTest {
    @Test
    fun `03,pos,distance,1`() {
        report {
            Pos(0, 0).distanceTo(Pos(3, 3)) to 6
        }
    }

    @Test
    fun `03,pos,distance,2`() {
        report {
            Pos(0, 0).distanceTo(Pos(0, 0)) to 0
        }
    }

    @Test
    fun `03,pos,distance,3`() {
        report {
            Pos(3, 2).distanceTo(Pos(0, 0)) to 5
        }
    }

    @Test
    fun `03,pos,newPos,1`() {
        report {
            Pos(3, 2).pos(Op("L50")) to Pos(-47, 2)
        }
    }

    @Test
    fun `03,pos,newPos,2`() {
        report {
            Pos(3, 2).pos(Op("L0")) to Pos(3, 2)
        }
    }

    @Test
    fun `03,pos,newPos,3`() {
        report {
            Pos(10, 10).pos(Op("L2")) to Pos(8, 10)
        }
    }

    @Test
    fun `03,pos,newPos,4`() {
        report {
            Pos(10, 10).pos(Op("U2")) to Pos(10, 12)
        }
    }

    @Test
    fun `03,pos,newPos,5`() {
        report {
            Pos(10, 10).pos(Op("D2")) to Pos(10, 8)
        }
    }

    @Test
    fun `03,pos,newPos,6`() {
        report {
            Pos(10, 10).pos(Op("R2")) to Pos(12, 10)
        }
    }

    @Test
    fun `03,pos,range,1`() {
        report {
            Pos(0, 0)..Pos(2, 0) to listOf(Pos(0, 0), Pos(1, 0), Pos(2, 0))
        }
    }

    @Test
    fun `03,pos,range,2`() {
        report {
            Pos(2, 0)..Pos(0, 0) to listOf(Pos(2, 0), Pos(1, 0), Pos(0, 0))
        }
    }

    @Test
    fun `03,pos,range,3`() {
        report {
            Pos(1, 1)..Pos(1, 3) to listOf(Pos(1, 1), Pos(1, 2), Pos(1, 3))
        }
    }

    @Test
    fun `03,pos,range,4`() {
        report {
            Pos(1, 5)..Pos(1, 2) to listOf(Pos(1, 5), Pos(1, 4), Pos(1, 3), Pos(1, 2))
        }
    }

    @Test
    fun `03,pos,range,5`() {
        Assertions.assertThrows(NotImplementedError::class.java) {
            Pos(1, 5)..Pos(3, 4)
        }
    }
}
