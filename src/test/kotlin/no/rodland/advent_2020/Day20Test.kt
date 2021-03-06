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
                Day20.partOne(data20) to 12519494280967
            }
        }

        @Test
        fun `20,2,live,init`() {
            report {
                Day20.partTwo(data20) to 2442
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `20,1,test`() {
            report {
                Day20.partOne(test20) to 20899048083289
            }
        }

        @Test
        fun `20,1,live,1`() {
            report {
                Day20.partOne(data20) to 12519494280967
            }
        }

        @Test
        fun `20,1,live,2`() {
            report {
                Day20.partOne(data20) to 12519494280967
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `20,2,test`() {
            report {
                Day20.partTwo(test20) to 273
            }
        }

        @Test
        fun `20,2,test,forrest_rotate_2`() {
            report {
                val l = listOf(listOf('a', 'b'), listOf('c', 'd'))
                val f = Day20.Forest(l)
                f.rotateR() to listOf(listOf('c', 'a'), listOf('d', 'b'))

            }
        }

        @Test
        fun `20,2,test,forrest_rotate_3`() {
            report {
                val l = listOf(listOf('a', 'b', 'c'), listOf('e', 'f', 'g'), listOf('i', 'j', 'k'))
                val f = Day20.Forest(l)
                f.rotateR() to listOf(listOf('i', 'e', 'a'), listOf('j', 'f', 'b'), listOf('k', 'g', 'c'))

            }
        }

        @Test
        fun `20,2,live,1`() {
            report {
                Day20.partTwo(data20) to 2442
            }
        }

        @Test
        fun `20,2,live,2`() {
            report {
                Day20.partTwo(data20) to 2442
            }
        }
    }
}
