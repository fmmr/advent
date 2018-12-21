package no.rodland.advent_2017

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@DisableSlow
internal class Day9Test {
    val data9 = "2017/input_9.txt".readFile()[0]

    @Nested
    inner class `Part 1` {
        @Test
        fun `9,1,test,1`() {
            report {
                Day9.partOne("{{<ab>},{<ab>},{<ab>},{<ab>}}") to 9
            }
        }

        @Test
        fun `9,1,test,2`() {
            report {
                Day9.partOne("{<a>,<a>,<a>,<a>}") to 1
            }
        }

        @Test
        fun `9,1,test,3`() {
            report {
                Day9.partOne("{{{},{},{{}}}}") to 16
            }
        }

        @Test
        fun `9,1,test,4`() {
            report {
                Day9.partOne("{{},{}}") to 5
            }
        }

        @Test
        fun `9,1,test,5`() {
            report {
                Day9.partOne("{{{}}}") to 6
            }
        }


        @Test
        fun `9,1,test,6`() {
            report {
                Day9.partOne("{}") to 1
            }
        }

        @Test
        fun `9,1,test,7`() {
            report {
                Day9.partOne("{{<!!>},{<!!>},{<!!>},{<!!>}}") to 9
            }
        }

        @Test
        fun `9,1,test,8`() {
            report {
                Day9.partOne("{{<a!>},{<a!>},{<a!>},{<ab>}}") to 3
            }
        }

        @Test
        fun `9,1,live`() {
            report {
                Day9.partOne(data9) to 12803
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `9,2,test`() {
            report {
                Day9.partTwo("<>") to 2
            }
        }

        @Test
        fun `9,2,live`() {
            report {
                Day9.partTwo(data9) to 2
            }
        }
    }
}


