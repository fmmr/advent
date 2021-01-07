package no.rodland.advent_2015

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@Suppress("ClassName")
@DisableSlow
internal class Day08Test {
    val data08 = "2015/input_08.txt".readFile()
    val test08 = listOf(
            "\"\"",
            "\"abc\"",
            "\"aaa\\\"aaa\"",
            "\"\\x27\"",
            "\"l\\\\e\""
    )
    val test08_2 = listOf(
            "\"\"",
            "\"abc\"",
            "\"aaa\\\"aaa\"",
            "\"\\x27\"",
    )

    @Nested
    inner class Init {
        @Test
        fun `08,1,live,init`() {
            report {
                Day08.partOne(data08) to 1344
            }
        }

        @Test
        fun `08,2,live,init`() {
            report {
                Day08.partTwo(data08) to 2074
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `08,1,test`() {
            report {
                Day08.partOne(test08) to 15
            }
        }

        @Test
        fun `08,1,test,2`() {
            report {
                Day08.partOne(listOf("\"kbngyfvvsdismznhar\\\\p\\\"\\\"gpryt\\\"jaeh\"")) to 6
            }
        }

        @Test
        fun `08,1,test,3`() {
            report {
                Day08.partOne(listOf("\"x\\\"\\xcaj\\\\xwwvpdldz\"")) to 7
            }
        }

        @Test
        fun `08,1,live,1`() {
            report {
                Day08.partOne(data08) to 1344
            }
        }

        @Test
        fun `08,1,live,2`() {
            report {
                Day08.partOne(data08) to 1344
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `08,2,test`() {
            report {
                Day08.partTwo(test08_2) to 19
            }
        }

        @Test
        fun `08,2,live,1`() {
            report {
                Day08.partTwo(data08) to 2074
            }
        }

        @Test
        fun `08,2,live,2`() {
            report {
                Day08.partTwo(data08) to 2074
            }
        }
    }
}
