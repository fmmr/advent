package no.rodland.advent_2018

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@DisableSlow
internal class Day17Test {
    val data17 = "2018/input_17.txt".readFile()
    val test17 = listOf("x=495, y=2..7", "y=7, x=495..501", "x=501, y=3..7", "x=498, y=2..4", "x=506, y=1..2", "x=498, y=10..13", "x=504, y=10..13", "y=13, x=498..504")

    @Nested
    inner class Parse {
        @Test
        fun `17,1,parse`() {
            report {
                Day17.parse(test17) to listOf(Pair(495..495, 2..7),
                        Pair(495..501, 7..7),
                        Pair(501..501, 3..7),
                        Pair(498..498, 2..4),
                        Pair(506..506, 1..2),
                        Pair(498..498, 10..13),
                        Pair(504..504, 10..13),
                        Pair(498..504, 13..13))
            }
        }

        @Test
        fun `17,1,minmax`() {
            report {
                val parse = Day17.parse(test17)
                Day17.getYMinMax(parse) to (1 to 13)
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `17,1,test`() {
            report {
                Day17.partOne(test17) to 2
            }
        }

        @Test
        fun `17,1,live`() {
            report {
                Day17.partOne(data17) to 2
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `17,2,test`() {
            report {
                Day17.partTwo(test17) to 2
            }
        }

        @Test
        fun `17,2,live`() {
            report {
                Day17.partTwo(data17) to 2
            }
        }
    }
}


