package no.rodland.advent_2017

import no.rodland.advent.DisableSlow
import no.rodland.advent.Slow
import no.rodland.advent.report
import no.rodland.advent_2017.Day21.concat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@Suppress("ClassName")
@DisableSlow
internal class Day21Test {
    val data21 = "2017/input_21.txt".readFile()
    val test21 = listOf(
            "../.# => ##./#../...",
            ".#./..#/### => #..#/..../..../#..#",
    )

    @Nested
    inner class Init {
        @Test
        fun `21,1,live,init`() {
            report {
                Day21.partOne(data21, 5) to 205
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `21,1,test`() {
            report {
                Day21.partOne(test21, 2) to 12
            }
        }

        @Test
        fun `21,1,test,split 8`() {
            report {
                val splitted = Day21.Grid("abcdefgh/ijklmnop/qrstuvwx/yzæøå123/4567890a/bcdefghi/jklmnopq/rstuvwxy").split()
                val test = listOf(
                        splitted[0][0] == Day21.Grid("ab/ij"),
                        splitted[2][3] == Day21.Grid("0a/hi"),
                        splitted[3][3] == Day21.Grid("pq/xy"),
                )
                test to listOf(true, true, true)
            }
        }

        @Test
        fun `21,1,test,split 9`() {
            report {
                val splitted = Day21.Grid("abcdefghi/jklmnopqr/stuvwxyzæ/123456789/0abcdefgh/ijklmnopq/rstuvwxyz/æøå123456/789abcdef").split()
                val test = listOf(
                        splitted[0][0],
                        splitted[1][1],
                        splitted[2][2],
                )
                val fasit = listOf(
                        Day21.Grid("abc/jkl/stu"),
                        Day21.Grid("456/cde/lmn"),
                        Day21.Grid("xyz/456/def"),
                )
                test.zip(fasit).all { it.first == it.second } to true
            }
        }

        @Test
        fun `21,1,test,split 6`() {
            report {
                val splitted = Day21.Grid("abcdef/ghijkl/mnopqr/stuvwx/123456/789abc").split()
                val test = listOf(
                        splitted[0][0] == Day21.Grid("ab/gh"),
                        splitted[1][1] == Day21.Grid("op/uv"),
                        splitted[1][2] == Day21.Grid("qr/wx"),
                        splitted[2][2] == Day21.Grid("56/bc"),
                )
                test to listOf(true, true, true, true)
            }
        }

        @Test
        fun `21,1,test,concat`() {
            report {
                val test = listOf(
                        listOf(Day21.Grid("ab/ef"), Day21.Grid("cd/gh")),
                        listOf(Day21.Grid("12/56"), Day21.Grid("34/78"))
                )
                val concat = test.concat()
                concat to Day21.Grid("abcd/efgh/1234/5678")
            }
        }

        @Test
        fun `21,1,test,split size 2`() {
            report {
                val grid = Day21.Grid("ab/cd")
                grid.split() to listOf(listOf(grid))
            }
        }

        @Test
        fun `21,1,test,split size 3`() {
            report {
                val grid = Day21.Grid("abc/def/ghi")
                grid.split() to listOf(listOf(grid))
            }
        }

        @Test
        fun `21,1,test,concat size 2`() {
            report {
                val grid = Day21.Grid("ab/cd")
                val test = listOf(listOf(grid))
                val concat = test.concat()
                concat to grid
            }
        }

        @Test
        fun `21,1,test,concat size 3`() {
            report {
                val grid = Day21.Grid("abc/def/ghi")
                val test = listOf(listOf(grid))
                val concat = test.concat()
                concat to grid
            }
        }


        @Test
        fun `21,1,test,split and concat`() {
            report {
                val grid = Day21.Grid("abcd/efgh/1234/5678")
                val splitted = grid.split().concat()
                splitted to grid
            }
        }

        @Test
        fun `21,1,live,1`() {
            report {
                Day21.partOne(data21, 5) to 205
            }
        }

        @Test
        fun `21,1,live,2`() {
            report {
                Day21.partOne(data21, 5) to 205
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        @Slow(3200)
        fun `21,2,live,1`() {
            report {
                Day21.partOne(data21, 18) to 3389823
            }
        }
    }
}
