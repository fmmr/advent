package no.rodland.advent_2020

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@Suppress("ClassName")
@DisableSlow
internal class Day20Test {
    val data20 = "2020/input_20.txt".readFileAsOneString()
    val test20 = """
        Tile 2311:
        ..##.#..#.
        ##..#.....
        #...##..#.
        ####.#...#
        ##.##.###.
        ##...#.###
        .#.#.#..##
        ..#....#..
        ###...#.#.
        ..###..###
        
        Tile 1951:
        #.##...##.
        #.####...#
        .....#..##
        #...######
        .##.#....#
        .###.#####
        ###.##.##.
        .###....#.
        ..#.#..#.#
        #...##.#..
        
        Tile 1171:
        ####...##.
        #..##.#..#
        ##.#..#.#.
        .###.####.
        ..###.####
        .##....##.
        .#...####.
        #.##.####.
        ####..#...
        .....##...
        
        Tile 1427:
        ###.##.#..
        .#..#.##..
        .#.##.#..#
        #.#.#.##.#
        ....#...##
        ...##..##.
        ...#.#####
        .#.####.#.
        ..#..###.#
        ..##.#..#.
        
        Tile 1489:
        ##.#.#....
        ..##...#..
        .##..##...
        ..#...#...
        #####...#.
        #..#.#.#.#
        ...#.#.#..
        ##.#...##.
        ..##.##.##
        ###.##.#..
        
        Tile 2473:
        #....####.
        #..#.##...
        #.##..#...
        ######.#.#
        .#...#.#.#
        .#########
        .###.#..#.
        ########.#
        ##...##.#.
        ..###.#.#.
        
        Tile 2971:
        ..#.#....#
        #...###...
        #.#.###...
        ##.##..#..
        .#####..##
        .#..####.#
        #..#.#..#.
        ..####.###
        ..#.#.###.
        ...#.#.#.#
        
        Tile 2729:
        ...#.#.#.#
        ####.#....
        ..#.#.....
        ....#..#.#
        .##..##.#.
        .#.####...
        ####.#.#..
        ##.####...
        ##..#.##..
        #.##...##.
        
        Tile 3079:
        #.#.#####.
        .#..######
        ..#.......
        ######....
        ####.#..#.
        .#...#.##.
        #.#####.##
        ..#.###...
        ..#.......
        ..#.###...
        """.trimIndent()

    @Nested
    inner class Init {
        @Test
        fun `20,1,live,init`() {
            report {
                Day20.partOne(data20) to 2
            }
        }

        @Test
        fun `20,2,live,init`() {
            report {
                Day20.partTwo(data20) to 2
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `20,1,test`() {
            report {
                Day20.partOne(test20) to 2
            }
        }

        @Test
        fun `20,1,live,1`() {
            report {
                Day20.partOne(data20) to 2
            }
        }

        @Test
        fun `20,1,live,2`() {
            report {
                Day20.partOne(data20) to 2
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `20,2,test`() {
            report {
                Day20.partTwo(test20) to 2
            }
        }

        @Test
        fun `20,2,live,1`() {
            report {
                Day20.partTwo(data20) to 2
            }
        }

        @Test
        fun `20,2,live,2`() {
            report {
                Day20.partTwo(data20) to 2
            }
        }
    }
}
