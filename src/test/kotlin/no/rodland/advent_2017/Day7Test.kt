package no.rodland.advent_2017

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@DisableSlow
internal class Day7Test {
    val data7 = "2017/input_7.txt".readFile()
    val test7 = listOf("pbga (66)", "xhth (57)", "ebii (61)", "havc (66)", "ktlj (57)", "fwft (72) -> ktlj, cntj, xhth", "qoyq (66)", "padx (45) -> pbga, havc, qoyq", "tknk (41) -> ugml, padx, fwft", "jptl (61)", "ugml (68) -> gyxo, ebii, jptl", "gyxo (61)", "cntj (57)")

    @Nested
    inner class `Part 1` {
        @Test
        fun `7,1,test`() {
            report {
                Day7.partOne(test7) to "tknk"
            }
        }

        @Test
        fun `7,1,live`() {
            report {
                Day7.partOne(data7) to "wiapj"
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `7,2,test`() {
            report {
                Day7.partTwo(test7) to 2
            }
        }

        @Test
        fun `7,2,live`() {
            report {
                Day7.partTwo(data7) to 2
            }
        }
    }
}


