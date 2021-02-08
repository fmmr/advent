package no.rodland.advent_2019

import no.rodland.advent.DisableSlow
import no.rodland.advent.Slow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@DisableSlow
internal class Day18Test {
    val data18 = "2019/input_18.txt".readFile()
    val data18_2 = "2019/input_18_2.txt".readFile()
    val test18_1 = listOf(
            "#########",
            "#b.A.@.a#",
            "#########"
    )
    val test18_2 = listOf(
            "########################",
            "#f.D.E.e.C.b.A.@.a.B.c.#",
            "######################.#",
            "#d.....................#",
            "########################"
    )
    val test18_3 = listOf(
            "########################",
            "#...............b.C.D.f#",
            "#.######################",
            "#.....@.a.B.c.d.A.e.F.g#",
            "########################"
    )
    val test18_4 = listOf(
            "#################",
            "#i.G..c...e..H.p#",
            "########.########",
            "#j.A..b...f..D.o#",
            "########@########",
            "#k.E..a...g..B.n#",
            "########.########",
            "#l.F..d...h..C.m#",
            "#################"
    )

    val test18_part2 = listOf(
            "#############",
            "#g#f.D#..h#l#",
            "#F###e#E###.#",
            "#dCba@#@BcIJ#",
            "#############",
            "#nK.L@#@G...#",
            "#M###N#H###.#",
            "#o#m..#i#jk.#",
            "#############",
    )

    val test18_5 = listOf(
            "########################",
            "#@..............ac.GI.b#",
            "###d#e#f################",
            "###A#B#C################",
            "###g#h#i################",
            "########################"
    )

    @Nested
    inner class `Part 1` {
        @Test
        fun `18,1,test,1`() {
            report {
                Day18.partOne(test18_1) to 8
            }
        }

        @Test
        fun `18,1,test,2`() {
            report {
                Day18.partOne(test18_2) to 86
            }
        }

        @Test
        fun `18,1,test,3`() {
            report {
                Day18.partOne(test18_3) to 132
            }
        }

        @Test
        fun `18,1,test,4`() {
            report {
                Day18.partOne(test18_4) to 136
            }
        }

        @Test
        fun `18,1,test,5`() {
            report {
                Day18.partOne(test18_5) to 81
            }
        }

        @Test
        @Slow(2600)
        fun `18,1,live`() {
            report {
                Day18.partOne(data18) to 2
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `18,2,test`() {
            report {
                Day18.partOne(test18_part2) to 72
            }
        }

        @Test
        @Slow(3100)
        fun `18,2,live`() {
            report {
                Day18.partOne(data18_2) to 2144
            }
        }
    }
}


