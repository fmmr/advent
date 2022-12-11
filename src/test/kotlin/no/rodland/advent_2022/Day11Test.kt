package no.rodland.advent_2022

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@Suppress("ClassName")
@DisableSlow
internal class Day11Test {
    val data11 = "2022/input_11.txt".readFile()
    val test11 = listOf(
        "Monkey 0:",
        "  Starting items: 79, 98",
        "  Operation: new = old * 19",
        "  Test: divisible by 23",
        "    If true: throw to monkey 2",
        "    If false: throw to monkey 3",
        "",
        "Monkey 1:",
        "  Starting items: 54, 65, 75, 74",
        "  Operation: new = old + 6",
        "  Test: divisible by 19",
        "    If true: throw to monkey 2",
        "    If false: throw to monkey 0",
        "",
        "Monkey 2:",
        "  Starting items: 79, 60, 97",
        "  Operation: new = old * old",
        "  Test: divisible by 13",
        "    If true: throw to monkey 1",
        "    If false: throw to monkey 3",
        "",
        "Monkey 3:",
        "  Starting items: 74",
        "  Operation: new = old + 3",
        "  Test: divisible by 17",
        "    If true: throw to monkey 0",
        "    If false: throw to monkey 1",
    )

    val resultTestOne = 10605
    val resultOne = 58056

    val resultTestTwo = 2713310158L
    val resultTwo = 15048718170L

    @Nested
    inner class Init {
        @Test
        fun `11,1,live,init`() {
            report {
                Day11.partOne(data11) to resultOne
            }
        }

        @Test
        fun `11,2,live,init`() {
            report {
                Day11.partTwo(data11) to resultTwo
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `11,1,test`() {
            report {
                Day11.partOne(test11) to resultTestOne
            }
        }

        @Test
        fun `11,1,live,1`() {
            report {
                Day11.partOne(data11) to resultOne
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `11,2,test`() {
            report {
                Day11.partTwo(test11) to resultTestTwo
            }
        }

        @Test
        fun `11,2,live,1`() {
            report {
                Day11.partTwo(data11) to resultTwo
            }
        }
    }
}
