package no.rodland.advent_2022

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@Suppress("ClassName")
@DisableSlow
internal class Day07Test {
    val data07 = "2022/input_07.txt".readFile()
    val test07 = listOf(
        "$ cd /",
        "$ ls",
        "dir a",
        "14848514 b.txt",
        "8504156 c.dat",
        "dir d",
        "$ cd a",
        "$ ls",
        "dir e",
        "29116 f",
        "2557 g",
        "62596 h.lst",
        "$ cd e",
        "$ ls",
        "584 i",
        "$ cd ..",
        "$ cd ..",
        "$ cd d",
        "$ ls",
        "4060174 j",
        "8033020 d.log",
        "5626152 d.ext",
        "7214296 k",
    )
    val resultTestOne = 95437
    val resultOne = 1391690

    val resultTestTwo = 24933642
    val resultTwo = 5469168

    @Nested
    inner class Init {
        @Test
        fun `07,1,live,init`() {
            report {
                Day07.partOne(data07) to resultOne
            }
        }

        @Test
        fun `07,2,live,init`() {
            report {
                Day07.partTwo(data07) to resultTwo
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `07,1,test`() {
            report {
                Day07.partOne(test07) to resultTestOne
            }
        }

        @Test
        fun `07,1,live,1`() {
            report {
                Day07.partOne(data07) to resultOne
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `07,2,test`() {
            report {
                Day07.partTwo(test07) to resultTestTwo
            }
        }

        @Test
        fun `07,2,live,1`() {
            report {
                Day07.partTwo(data07) to resultTwo
            }
        }
    }
}
