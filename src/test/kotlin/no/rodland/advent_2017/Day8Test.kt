package no.rodland.advent_2017

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@DisableSlow
internal class Day8Test {
    val data8 = "2017/input_8.txt".readFile()
    val test8 = listOf(
            "b inc 5 if a > 1",
            "a inc 1 if b < 5",
            "c dec -10 if a >= 1",
            "c inc -20 if c == 10"
    )

    @Nested
    inner class `Part 1` {
        @Test
        fun `8,1,test`() {
            report {
                Day8.run(test8).first to 1
            }
        }

        @Test
        fun `8,1,live`() {
            report {
                Day8.run(data8).first to 5946
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `8,2,test`() {
            report {
                Day8.run(test8).second to 10
            }
        }

        @Test
        fun `8,2,live`() {
            report {
                Day8.run(data8).second to 6026
            }
        }
    }
}


