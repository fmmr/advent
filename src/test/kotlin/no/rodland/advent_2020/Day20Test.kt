package no.rodland.advent_2020

import no.rodland.advent.AOCTest
import no.rodland.advent.DisableSlow
import no.rodland.advent.defaultTestSuiteParseOnCall
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.time.ExperimentalTime

@Suppress("ClassName")
@ExperimentalTime
@DisableSlow
internal class Day20Test {
    private val data20 = "2020/input_20.txt".readFileAsOneString()
    private val test20 = """
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


    val test = defaultTestSuiteParseOnCall(
        20,
        Day20::partOne,
        Day20::partTwo,
        data20,
        test20,
        20899048083289L, 12519494280967L, 273, 2442,
        1, 1
    )


    @Nested
    inner class Init {
        @Test
        fun `20,1,live,init`() {
            test.livePart1.run { function(data) }
        }

        @Test
        fun `20,2,live,init`() {
            report(test.livePart2.copy(numTests = 1))
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `20,1,test`() {
            report(test.testPart1)
        }

        @Test
        fun `20,1,live`() {
            report(test.livePart1)
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `20,2,test`() {
            report(test.testPart2)
        }

        @Test
        fun `20,2,live`() {
            report(test.livePart2)
        }
    }

    @Nested
    inner class Misc {
        @Test
        fun `20,2,test,forrest_rotate_2`() {
            report(
                AOCTest(
                    name = "20.Rotate_2x2",
                    Day20.Forest::rotateR,
                    Day20.Forest(listOf(listOf('a', 'b'), listOf('c', 'd'))),
                    listOf(listOf('c', 'a'), listOf('d', 'b'))
                )
            )
        }

        @Test
        fun `20,2,test,forrest_rotate_3`() {
            report(
                AOCTest(
                    "20.Rotate_3x3",
                    Day20.Forest::rotateR,
                    Day20.Forest(listOf(listOf('a', 'b', 'c'), listOf('e', 'f', 'g'), listOf('i', 'j', 'k'))),
                    listOf(listOf('i', 'e', 'a'), listOf('j', 'f', 'b'), listOf('k', 'g', 'c'))
                )
            )
        }
    }
}
