package no.rodland.advent_2021

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@Suppress("ClassName")
@DisableSlow
internal class Day08Test {
    val data08 = "2021/input_08.txt".readFile()
    val test08_simple = listOf(
        "acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab | cdfeb fcadb cdfeb cdbaf"
    )
    val test08 = listOf(
        "be cfbegad cbdgef fgaecd cgeb fdcge agebfd fecdb fabcd edb | fdgacbe cefdb cefbgd gcbe",
        "edbfga begcd cbg gc gcadebf fbgde acbgfd abcde gfcbed gfec | fcgedb cgb dgebacf gc",
        "fgaebd cg bdaec gdafb agbcfd gdcbef bgcad gfac gcb cdgabef | cg cg fdcagb cbg",
        "fbegcd cbd adcefb dageb afcb bc aefdc ecdab fgdeca fcdbega | efabcd cedba gadfec cb",
        "aecbfdg fbg gf bafeg dbefa fcge gcbea fcaegb dgceab fcbdga | gecf egdcabf bgf bfgea",
        "fgeab ca afcebg bdacfeg cfaedg gcfdb baec bfadeg bafgc acf | gebdcfa ecba ca fadegcb",
        "dbcfg fgd bdegcaf fgec aegbdf ecdfab fbedc dacgb gdcebf gf | cefg dcbef fcge gbcadfe",
        "bdfegc cbegaf gecbf dfcage bdacg ed bedf ced adcbefg gebcd | ed bcgafe cdgba cbgef",
        "egadfb cdbfeg cegd fecab cgb gbdefca cg fgcdab egfdb bfceg | gbdfcae bgc cg cgb",
        "gcafb gcf dcaebfg ecagb gf abcdeg gaef cafbge fdbac fegbdc | fgae cfgab fg bagce",
    )

    @Nested
    inner class Init {
        @Test
        fun `08,1,live,init`() {
            report {
                Day08.partOne(data08) to 554
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `08,1,test_simple`() {
            report {
                Day08.partOne(test08_simple) to 0
            }
        }

        @Test
        fun `08,1,test`() {
            report {
                Day08.partOne(test08) to 26
            }
        }

        @Test
        fun `08,1,live,1`() {
            report {
                Day08.partOne(data08) to 554
            }
        }

        @Test
        fun `08,1,live,2`() {
            report {
                Day08.partOne(data08) to 554
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `08,2,test`() {
            report {
                Day08.partTwo(test08) to 2
            }
        }

        @Test
        fun `08,2,live,1`() {
            report {
                Day08.partTwo(data08) to 2
            }
        }

        @Test
        fun `08,2,live,2`() {
            report {
                Day08.partTwo(data08) to 2
            }
        }
    }
}
