@file:Suppress("SpellCheckingInspection")

package no.rodland.advent_2022

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@Suppress("ClassName")
@DisableSlow
internal class Day05Test {
    val data05Commands = "2022/input_05_commands.txt".readFile()
    val data05Crates = "2022/input_05_crates.txt".readFile()
    val test05Commands = listOf(
        "move 1 from 2 to 1",
        "move 3 from 1 to 3",
        "move 2 from 2 to 1",
        "move 1 from 1 to 2",
    )
    val test05Crates = listOf(
        "    [D]    ",
        "[N] [C]    ",
        "[Z] [M] [P]",
        " 1   2   3 ",
    )

    val resultTestOne = "CMZ"
    val resultOne = "QNNTGTPFN"

    val resultTestTwo = "MCD"
    val resultTwo = "GGNPJBTTR"

    @Nested
    inner class Init {
        @Test
        fun `05,1,live,init`() {
            report {
                Day05.partOne(data05Crates, data05Commands) to resultOne
            }
        }

        @Test
        fun `05,2,live,init`() {
            report {
                Day05.partTwo(data05Crates, data05Commands) to resultTwo
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `05,1,test`() {
            report {
                Day05.partOne(test05Crates, test05Commands) to resultTestOne
            }
        }

        @Test
        fun `05,1,live,1`() {
            report {
                Day05.partOne(data05Crates, data05Commands) to resultOne
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `05,2,test`() {
            report {
                Day05.partTwo(test05Crates, test05Commands) to resultTestTwo
            }
        }

        @Test
        fun `05,2,live,1`() {
            report {
                Day05.partTwo(data05Crates, data05Commands) to resultTwo
            }
        }
    }
}
