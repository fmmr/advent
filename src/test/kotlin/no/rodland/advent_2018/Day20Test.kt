package no.rodland.advent_2018

import no.rodland.advent.DisableSlow
import no.rodland.advent.Pos
import no.rodland.advent.report
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile
import kotlin.test.assertEquals

@DisableSlow
internal class Day20Test {
    val data20 = "2018/input_20.txt".readFile()[0]

    @Nested
    inner class Simple {
        @Test
        fun `20,1,test,fmr_simplest_w`() {
            report {
                val map = mutableMapOf<Pos, Char>(Pos(0, 0) to 'X')
                val partOne = Day20.traverse("^W$", map, Pos(0, 0))
                assertThat(map).containsKeys(Pos(0, 0), Pos(x = -1, y = -1), Pos(x = -2, y = 0), Pos(x = -1, y = 0), Pos(x = -1, y = 1))
                assertThat(map).containsEntry(Pos(x = -1, y = 0), '|')
                assertThat(map).containsEntry(Pos(x = -1, y = -1), '#')
                assertThat(map).containsEntry(Pos(x = -2, y = 0), '.')
                assertThat(map).hasSize(5)
                partOne to Pos(-2, 0)
            }
        }


        @Test
        fun `20,1,test,fmr_simplest_ww`() {
            report {
                val map = mutableMapOf<Pos, Char>(Pos(0, 0) to 'X')
                val partOne = Day20.traverse("^WW$", map, Pos(0, 0))
                assertThat(map).containsKeys(Pos(0, 0), Pos(x = -1, y = -1), Pos(x = -2, y = 0), Pos(x = -1, y = 0), Pos(x = -1, y = 1))
                assertThat(map).containsEntry(Pos(x = -1, y = 0), '|')
                assertThat(map).containsEntry(Pos(x = -1, y = -1), '#')
                assertThat(map).containsEntry(Pos(x = -2, y = 0), '.')
                assertThat(map).containsEntry(Pos(x = -3, y = 0), '|')
                assertThat(map).containsEntry(Pos(x = -4, y = 0), '.')
                assertThat(map).hasSize(9)
                partOne to Pos(-4, 0)
            }
        }

        @Test
        fun `20,1,test,fmr_simplest_wn`() {
            report {
                val map = mutableMapOf<Pos, Char>(Pos(0, 0) to 'X')
                val partOne = Day20.traverse("^WN$", map, Pos(0, 0))
                isMap(map, listOf(
                        "?.??",
                        "#-#?",
                        "?.|X",
                        "??#?"
                ))
                assertThat(map).hasSize(8)
                partOne to Pos(x = -2, y = -2)
            }
        }

        @Test
        fun `20,1,test,fmr_simplest_wne`() {
            report {
                val map = mutableMapOf<Pos, Char>(Pos(0, 0) to 'X')
                val partOne = Day20.traverse("^WNE$", map, Pos(0, 0))
                isMap(map, listOf(
                        "??#?",
                        "?.|.",
                        "#-#?",
                        "?.|X",
                        "??#?"
                ))
                assertThat(map).hasSize(11)
                partOne to Pos(x = 0, y = -2)
            }
        }
    }


    @Nested
    inner class `Part 1` {
        @Test
        fun `20,1,test`() {
            report {
                Day20.partOne("^WNE$") to 3
            }
        }


//        @Test
//        fun `20,1,test,fmr_1`() {
//            report {
//                Day20.partOne("^(WNE)$") to 3
//            }
//        }
//
//        @Test
//        fun `20,1,test,fmr_2`() {
//            report {
//                Day20.partOne("^(WNE|NE)$") to 5
//            }
//        }
//
//        @Test
//        fun `20,1,test,2`() {
//            report {
//                Day20.partOne("^ENWWW(NEEE|SSE(EE|N))$") to 10
//            }
//        }
//
//        @Test
//        fun `20,1,test,3`() {
//            report {
//                Day20.partOne("^ENNWSWW(NEWS|)SSSEEN(WNSE|)EE(SWEN|)NNN$") to 18
//            }
//        }
//
//        @Test
//        fun `20,1,live`() {
//            report {
//                Day20.partOne(data20) to 2
//            }
//        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `20,2,test`() {
            report {
                Day20.partTwo("^WNE$") to 2
            }
        }

        @Test
        fun `20,2,live`() {
            report {
                Day20.partTwo(data20) to 2
            }
        }
    }

    private fun isMap(map: MutableMap<Pos, Char>, actual: List<String>) {
//        map.toMap().map { it.joinToString("") }.forEach { println(it) }
        assertEquals(map.toMap().map { it.joinToString("") }, actual)
    }
}


