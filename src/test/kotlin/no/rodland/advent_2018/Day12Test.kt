package no.rodland.advent_2018

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@DisableSlow
internal class Day12Test {
    val data12 = "2018/input_12.txt".readFile()
    val test12 = listOf("...## => #", "..#.. => #", ".#... => #", ".#.#. => #", ".#.## => #", ".##.. => #", ".#### => #", "#.#.# => #", "#.### => #", "##.#. => #", "##.## => #", "###.. => #", "###.# => #", "####. => #")

    @Nested
    inner class `Part 1` {
        @Test
        fun `12,1,test`() {
            report {
                Day12.partOne(test12, "#..#.#..##......###...###") to 325
            }
        }

        @Test
        fun `12,1,live`() {
            report {
                Day12.partOne(data12, "#.#.#....##...##...##...#.##.#.###...#.##...#....#.#...#.##.........#.#...#..##.#.....#..#.###") to 1733
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `12,2,test`() {
            report {
                Day12.partTwo(test12) to 2
            }
        }

        @Test
        fun `12,2,live`() {
            report {
                Day12.partTwo(data12) to 2
            }
        }
    }
}


