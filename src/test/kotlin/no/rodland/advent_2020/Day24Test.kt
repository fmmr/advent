package no.rodland.advent_2020

import no.rodland.advent.DisableSlow
import no.rodland.advent.Slow
import no.rodland.advent.report
import no.rodland.advent_2020.Day24.toDirections
import no.rodland.advent_2020.Day24.toTile
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@Suppress("ClassName")
@DisableSlow
internal class Day24Test {
    val data24 = "2020/input_24.txt".readFile()
    val test24 = listOf("esenee")
    val test24_2 = listOf(
        "sesenwnenenewseeswwswswwnenewsewsw",
        "neeenesenwnwwswnenewnwwsewnenwseswesw",
        "seswneswswsenwwnwse",
        "nwnwneseeswswnenewneswwnewseswneseene",
        "swweswneswnenwsewnwneneseenw",
        "eesenwseswswnenwswnwnwsewwnwsene",
        "sewnenenenesenwsewnenwwwse",
        "wenwwweseeeweswwwnwwe",
        "wsweesenenewnwwnwsenewsenwwsesesenwne",
        "neeswseenwwswnwswswnw",
        "nenwswwsewswnenenewsenwsenwnesesenew",
        "enewnwewneswsewnwswenweswnenwsenwsw",
        "sweneswneswneneenwnewenewwneswswnese",
        "swwesenesewenwneswnwwneseswwne",
        "enesenwswwswneneswsenwnewswseenwsese",
        "wnwnesenesenenwwnenwsewesewsesesew",
        "nenewswnwewswnenesenwnesewesw",
        "eneswnwswnwsenenwnwnwwseeswneewsenese",
        "neswnwewnwnwseenwseesewsenwsweewe",
        "wseweeenwnesenwwwswnew",
    )

    @Nested
    inner class Init {
        @Test
        fun `24,1,live,init`() {
            report {
                Day24.partOne(data24) to 230
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `24,1,test`() {
            report {
                Day24.partOne(test24) to 1
            }
        }

        @Test
        fun `24,1,test,2`() {
            report {
                Day24.partOne(test24_2) to 10
            }
        }

        @Test
        fun `can follow instructions`() {
            val instruction = "nwwswee"
            val instructionsList = instruction.toDirections()
            val tile = instructionsList.toTile()
            assertThat(tile).isEqualTo(Day24.Hex(0, 0, 0))
        }

        @Test
        fun `24,1,live,1`() {
            report {
                Day24.partOne(data24) to 230
            }
        }

        @Test
        fun `24,1,live,2`() {
            report {
                Day24.partOne(data24) to 230
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `24,2,test`() {
            report {
                Day24.partTwo(test24_2) to 2208
            }
        }

        @Test
        @Slow(600)
        fun `24,2,live,1`() {
            report {
                Day24.partTwo(data24) to 3565
            }
        }

        @Test
        @Slow(1000)
        fun `24,2,live,2`() {
            report {
                Day24.partTwo(data24) to 3565
            }
        }
    }
}
