package no.rodland.advent_2022

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@Suppress("ClassName")
@DisableSlow
internal class Day21Test {
    val data21 = "2022/input_21.txt".readFile()
    val test21 = listOf(
        "root: pppw + sjmn",
        "dbpl: 5",
        "cczh: sllz + lgvd",
        "zczc: 2",
        "ptdq: humn - dvpt",
        "dvpt: 3",
        "lfqf: 4",
        "humn: 5",
        "ljgn: 2",
        "sjmn: drzm * dbpl",
        "sllz: 4",
        "pppw: cczh / lfqf",
        "lgvd: ljgn * ptdq",
        "drzm: hmdt - zczc",
        "hmdt: 32",
    )

    val resultTestOne = 152L
    val resultOne = 169525884255464L

    val resultTestTwo = 2L
    val resultTwo = 2L

    @Nested
    inner class Init {
        @Test
        fun `21,1,live,init`() {
            report {
                Day21.partOne(data21) to resultOne
            }
        }

        @Test
        fun `21,2,live,init`() {
            report {
                Day21.partTwo(data21) to resultTwo
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `21,1,test`() {
            report {
                Day21.partOne(test21) to resultTestOne
            }
        }

        @Test
        fun `21,1,live,1`() {
            report {
                Day21.partOne(data21) to resultOne
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `21,2,test`() {
            report {
                Day21.partTwo(test21) to resultTestTwo
            }
        }

        @Test
        fun `21,2,live,1`() {
            report {
                Day21.partTwo(data21) to resultTwo
            }
        }
    }
}
