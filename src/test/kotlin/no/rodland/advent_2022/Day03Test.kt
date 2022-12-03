package no.rodland.advent_2022

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import no.rodland.advent_2022.Day03.priority
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@Suppress("ClassName")
@DisableSlow
internal class Day03Test {
    val data03 = "2022/input_03.txt".readFile()
    val test03 = listOf(
        "vJrwpWtwJgWrhcsFMMfFFhFp",
        "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL",
        "PmmdzqPrVvPwwTWBwg",
        "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn",
        "ttgJtRGJQctTZtZT",
        "CrZsJsPPZsGzwwsLwLmpwMDw",
    )

    val resultTestOne = 157
    val resultOne = 7795

    val resultTestTwo = 2L
    val resultTwo = 2L

    @Nested
    inner class Init {
        @Test
        fun `03,1,live,init`() {
            report {
                Day03.partOne(data03) to resultOne
            }
        }

        @Test
        fun `03,2,live,init`() {
            report {
                Day03.partTwo(data03) to resultTwo
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `03,1,test`() {
            report {
                Day03.partOne(test03) to resultTestOne
            }
        }

        @Test
        fun `03,1,live,1`() {
            report {
                Day03.partOne(data03) to resultOne
            }
        }
    }

    @Nested
    inner class Misc {
        @Test
        fun `03,1,bag`() {
            report {
                val (sub1, sub2) = Day03.Bag("ttHH")
                (sub1 to sub2) to (listOf('t', 't') to listOf('H', 'H'))
            }
        }

        @Test
        fun `03,1,common`() {
            report {
                val bag = Day03.Bag("ttyTswHHyMMl")
                (bag.common()) to ('y')
            }
        }

        @Test
        fun `03,1,priority,1`() {
            report { ('a'.priority()) to (1) }
        }

        @Test
        fun `03,1,priority,2`() {
            report { ('z'.priority()) to (26) }
        }

        @Test
        fun `03,1,priority,3`() {
            report { ('A'.priority()) to (27) }
        }

        @Test
        fun `03,1,priority,4`() {
            report { ('Z'.priority()) to (52) }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `03,2,test`() {
            report {
                Day03.partTwo(test03) to resultTestTwo
            }
        }

        @Test
        fun `03,2,live,1`() {
            report {
                Day03.partTwo(data03) to resultTwo
            }
        }
    }
}
