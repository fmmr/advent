package no.rodland.advent_2020

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@Suppress("ClassName")
@DisableSlow
internal class Day07Test {
    val data07 = "2020/input_07.txt".readFile()
    val test07 = listOf(
        "light red bags contain 1 bright white bag, 2 muted yellow bags.",
        "dark orange bags contain 3 bright white bags, 4 muted yellow bags.",
        "bright white bags contain 1 shiny gold bag.",
        "muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.",
        "shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.",
        "dark olive bags contain 3 faded blue bags, 4 dotted black bags.",
        "vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.",
        "faded blue bags contain no other bags.",
        "dotted black bags contain no other bags.",
    )
    val test07_2 = listOf(
        "shiny gold bags contain 2 dark red bags.",
        "dark red bags contain 2 dark orange bags.",
        "dark orange bags contain 2 dark yellow bags.",
        "dark yellow bags contain 2 dark green bags.",
        "dark green bags contain 2 dark blue bags.",
        "dark blue bags contain 2 dark violet bags.",
        "dark violet bags contain no other bags.",
    )

    @Nested
    inner class Init {
        @Test
        fun `07,1,live,init`() {
            report {
                Day07.partOne(data07) to 142
            }
        }

        @Test
        fun `07,2,live,init`() {
            report {
                Day07.partTwo(data07) to 2
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `07,1,test`() {
            report {
                Day07.partOne(test07) to 4
            }
        }

        @Test
        fun `07,1,live,1`() {
            report {
                Day07.partOne(data07) to 142
            }
        }

        @Test
        fun `07,1,live,2`() {
            report {
                Day07.partOne(data07) to 142
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `07,2,test`() {
            report {
                Day07.partTwo(test07_2) to 2
            }
        }

        @Test
        fun `07,2,live,1`() {
            report {
                Day07.partTwo(data07) to 2
            }
        }

        @Test
        fun `07,2,live,2`() {
            report {
                Day07.partTwo(data07) to 2
            }
        }
    }
}
