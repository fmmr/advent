package no.rodland.advent_2018

import no.rodland.advent.DisableSlow
import no.rodland.advent.Pos
import no.rodland.advent.Slow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisableSlow
internal class Day22Test {
    val test22 = listOf("1", "2")

    @Nested
    inner class `Part 1` {
        @Test
        fun `22,1,test`() {
            report {
                Day22.partOne(510, Pos(10, 10)) to 114
            }
        }

        @Test
        @Slow(2900)
        fun `22,1,live`() {
            report {
                Day22.partOne(8112, Pos(13, 743)) to 10395
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `22,2,test`() {
            report {
                Day22.partTwo(510, Pos(10, 10)) to 45
            }
        }

        //        depth: 8112
//        target: 13,743
        @Test
        @Slow(9000)
        fun `22,1,live`() {
            report {
                // ca 750 => ca 2000
                // 900 => 1082
                // 1000 => 1027
                // 1300 => 1027
                // 2500 => 1027
                Day22.partTwo(8112, Pos(13, 743)) to 1006
            }
        }
    }
}


