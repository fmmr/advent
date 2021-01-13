package no.rodland.advent_2016

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@Suppress("ClassName")
@DisableSlow
internal class Day08Test {
    val data08 = "2016/input_08.txt".readFile()
    val test08 = listOf(
            "rect 3x2",
            "rotate column x=1 by 1",
            "rotate row y=0 by 4",
            "rotate column x=1 by 1",
    )

    @Nested
    inner class `Part 1` {
        @Test
        fun `08,1,test,cmd`() {
            report {
                Day08.Cmd("rotate row y=1 by 13").toString() to "Cmd(op=ROT_R, arg1=1, arg2=13)"
            }
        }

        @Test
        fun `08,1,test,cmd,2`() {
            report {
                Day08.Cmd("rotate column y=21 by 23").toString() to "Cmd(op=ROT_C, arg1=21, arg2=23)"
            }
        }

        @Test
        fun `08,1,test`() {
            report {
                Day08.partOne(test08, 7, 3) to 6
            }
        }

        @Test
        fun `08,1,live,2`() {
            report {
                Day08.partOne(data08, 50, 6) to 115
            }
        }
    }
}
