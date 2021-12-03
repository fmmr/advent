package no.rodland.advent_2018

import no.rodland.advent.DisableSlow
import no.rodland.advent.Pos
import no.rodland.advent.report
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

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
                map.toMap().map { it.joinToString("") }.forEach { println(it) }
                assertEquals(listOf(
                        "?.??",
                        "#-#?",
                        "?.|X",
                        "??#?"
                ), map.toMap().map { it.joinToString("") })
                assertThat(map).hasSize(8)
                partOne to Pos(x = -2, y = -2)
            }
        }

        @Test
        fun `20,1,test,fmr_simplest_wne`() {
            report {
                val map = mutableMapOf<Pos, Char>(Pos(0, 0) to 'X')
                val partOne = Day20.traverse("^WNE$", map, Pos(0, 0))
                map.toMap().map { it.joinToString("") }.forEach { println(it) }
                assertEquals(listOf(
                        "??#?",
                        "?.|.",
                        "#-#?",
                        "?.|X",
                        "??#?"
                ), map.toMap().map { it.joinToString("") })
                assertThat(map).hasSize(11)
                partOne to Pos(x = 0, y = -2)
            }
        }

        @Test
        fun `20,1,test,fmr_simplest_wne-ne`() {
            report {
                val map = mutableMapOf<Pos, Char>(Pos(0, 0) to 'X')
                val partOne = Day20.traverse("^WNE|NE$", map, Pos(0, 0))
                map.toMap().map { it.joinToString("") }.forEach { println(it) }
                assertEquals(listOf(
                        "??#?#?",
                        "?.|.|.",
                        "#-#-#?",
                        "?.|X??",
                        "??#???"
                ), map.toMap().map { it.joinToString("") })
                assertThat(map).hasSize(16)
                partOne to Pos(x = 0, y = 0)
            }
        }

        @Test
        fun `20,1,test,fmr_simplest_(wne-ne)`() {
            report {
                val map = mutableMapOf<Pos, Char>(Pos(0, 0) to 'X')
                val partOne = Day20.traverse("^(WNE|NE)$", map, Pos(0, 0))
                map.toMap().map { it.joinToString("") }.forEach { println(it) }
                assertEquals(listOf(
                        "??#?#?",
                        "?.|.|.",
                        "#-#-#?",
                        "?.|X??",
                        "??#???"
                ), map.toMap().map { it.joinToString("") })
                assertThat(map).hasSize(16)
                partOne to Pos(x = 0, y = 0)
            }
        }

        @Test
        fun `20,1,test,fmr_simplest_(wne-)`() {
            report {
                val map = mutableMapOf<Pos, Char>(Pos(0, 0) to 'X')
                val partOne = Day20.traverse("^(WNE|)$", map, Pos(0, 0))
                map.toMap().map { it.joinToString("") }.forEach { println(it) }
                assertEquals(listOf(
                        "??#?",
                        "?.|.",
                        "#-#?",
                        "?.|X",
                        "??#?"
                ), map.toMap().map { it.joinToString("") })
                assertThat(map).hasSize(11)
                partOne to Pos(x = 0, y = 0)
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `20,1,test,WNE`() {
            report {
                val map = Day20.parse("^WNE$")
                assertThat(map).hasSize(4)
                map to mapOf(Pos(x = -1, y = -1) to 2, Pos(x = 0, y = -1) to 3, Pos(x = -1, y = 0) to 1, Pos(x = 0, y = 0) to 0)
            }
        }


        @Test
        fun `20,1,test,fmr_^(WNE-NE)$`() {
            report {
                Day20.parse("^(WNE|NE)$").size to 5
            }
        }

        @Test
        fun `20,1,part1,fmr_^(WNE-NE)$`() {
            report {
                Day20.partOne("^(WNE|NE)$") to 2
            }
        }

        @Test
        fun `20,1,test,^ENWWW(NEEE-SSE(EE-N))$`() {
            report {
                Day20.parse("^ENWWW(NEEE|SSE(EE|N))$").size to 16
            }
        }

        @Test
        fun `20,1,part1,^ENWWW(NEEE-SSE(EE-N))$`() {
            report {
                Day20.partOne("^ENWWW(NEEE|SSE(EE|N))$") to 10
            }
        }

        @Test
        fun `20,1,test,^ENNWSWW(NEWS-)SSSEEN(WNSE-)EE(SWEN-)NNN$`() {
            report {
                Day20.parse("^ENNWSWW(NEWS|)SSSEEN(WNSE|)EE(SWEN|)NNN$").size to 25
            }
        }

        @Test
        fun `20,1,part1,^ENNWSWW(NEWS-)SSSEEN(WNSE-)EE(SWEN-)NNN$`() {
            report {
                Day20.partOne("^ENNWSWW(NEWS|)SSSEEN(WNSE|)EE(SWEN|)NNN$") to 18
            }
        }

        @Test
        fun `20,1,test,live`() {
            report {
                Day20.parse(data20).size to 10000
            }
        }

        @Test
        fun `20,1,live`() {
            report {
                Day20.partOne(data20) to 3046
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `20,2,test`() {
            report {
                Day20.partTwo("^WNE$") to 0
            }
        }

        @Test
        fun `20,2,live`() {
            report {
                Day20.partTwo(data20) to 8545
            }
        }
    }

    private fun isMap(map: MutableMap<Pos, Char>, expected: List<String>) {
        map.toMap().map { it.joinToString("") }.forEach { println(it) }
        assertEquals(expected, map.toMap().map { it.joinToString("") })
    }
}


