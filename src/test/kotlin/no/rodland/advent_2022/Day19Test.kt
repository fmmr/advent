package no.rodland.advent_2022

import no.rodland.advent.DisableSlow
import no.rodland.advent.Slow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@Suppress("ClassName")
@DisableSlow
internal class Day19Test {
    val data19 = "2022/input_19.txt".readFile()
    val test19 = listOf(
        "Blueprint 1: Each ore robot costs 4 ore. Each clay robot costs 2 ore. Each obsidian robot costs 3 ore and 14 clay. Each geode robot costs 2 ore and 7 obsidian.",
        "Blueprint 2: Each ore robot costs 2 ore. Each clay robot costs 3 ore. Each obsidian robot costs 3 ore and 8 clay. Each geode robot costs 3 ore and 12 obsidian.",
    )

    val resultTestOne = 33
    val resultOne = 1659
    val resultTwo = 6804

    @Nested
    inner class Init {
        @Test
        fun `19,1,live,init`() {
            report {
                Day19.partOne(data19) to resultOne
            }
        }

        @Test
        @Slow(1100)
        fun `19,2,live,init`() {
            report {
                Day19.partTwo(data19) to resultTwo
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `19,1,test`() {
            report {
                Day19.partOne(test19) to resultTestOne
            }
        }

        @Test
        fun `19,1,live,1`() {
            report {
                Day19.partOne(data19) to resultOne
            }
        }
    }

    @Nested
    inner class `Part 2` {

        @Test
        @Slow(1100)
        fun `19,2,live,1`() {
            report {
                Day19.partTwo(data19) to resultTwo
            }
        }
    }
}
