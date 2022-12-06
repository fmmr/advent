package no.rodland.advent_2022

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFileAsString

@Suppress("ClassName", "SpellCheckingInspection")
@DisableSlow
internal class Day06Test {
    val data06 = "2022/input_06.txt".readFileAsString()
    val test06A = "mjqjpqmgbljsphdztnvjfqwrcgsmlb"
    val test06B = "bvwbjplbgvbhsrlpgdmjqwftvncz"
    val test06C = "nppdvjthqldpwncqszvftbrmjlhg"
    val test06D = "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg"
    val test06E = "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw"

    val resultTestOneA = 7
    val resultTestOneB = 5
    val resultTestOneC = 6
    val resultTestOneD = 10
    val resultTestOneE = 11


    val resultOne = 1702

    val resultTestTwoA = 19
    val resultTestTwoB = 23
    val resultTestTwoC = 23
    val resultTestTwoD = 29
    val resultTestTwoE = 26
    val resultTwo = 3559

    @Nested
    inner class Init {
        @Test
        fun `06,1,live,init`() {
            report {
                Day06.partOne(data06) to resultOne
            }
        }

        @Test
        fun `06,2,live,init`() {
            report {
                Day06.partTwo(data06) to resultTwo
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `06,1,test,A`() {
            report {
                Day06.partOne(test06A) to resultTestOneA
            }
        }
        @Test
        fun `06,1,test,B`() {
            report {
                Day06.partOne(test06B) to resultTestOneB
            }
        }
        @Test
        fun `06,1,test,C`() {
            report {
                Day06.partOne(test06C) to resultTestOneC
            }
        }
        @Test
        fun `06,1,test,D`() {
            report {
                Day06.partOne(test06D) to resultTestOneD
            }
        }
        @Test
        fun `06,1,test,E`() {
            report {
                Day06.partOne(test06E) to resultTestOneE
            }
        }

        @Test
        fun `06,1,live,1`() {
            report {
                Day06.partOne(data06) to resultOne
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `06,2,testA`() {
            report {
                Day06.partTwo(test06A) to resultTestTwoA
            }
        }
        @Test
        fun `06,2,testB`() {
            report {
                Day06.partTwo(test06B) to resultTestTwoB
            }
        }
        @Test
        fun `06,2,testC`() {
            report {
                Day06.partTwo(test06C) to resultTestTwoC
            }
        }
        @Test
        fun `06,2,testD`() {
            report {
                Day06.partTwo(test06D) to resultTestTwoD
            }
        }
        @Test
        fun `06,2,testE`() {
            report {
                Day06.partTwo(test06E) to resultTestTwoE
            }
        }

        @Test
        fun `06,2,live,1`() {
            report {
                Day06.partTwo(data06) to resultTwo
            }
        }
    }
}
