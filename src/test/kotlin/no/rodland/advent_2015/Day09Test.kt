package no.rodland.advent_2015

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@Suppress("ClassName")
@DisableSlow
internal class Day09Test {
    val data09 = "2015/input_09.txt".readFile()
    val test09 = listOf(
        "London to Dublin = 464",
        "London to Belfast = 518",
        "Dublin to Belfast = 141",
    )

    @Nested
    inner class Init {
        @Test
        fun `09,1,live,init`() {
            report {
                Day09.partOne(data09) to 251
            }
        }

        @Test
        fun `09,2,live,init`() {
            report {
                Day09.partTwo(data09) to 898
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `09,1,test`() {
            report {
                Day09.partOne(test09) to 605
            }
        }

        @Test
        fun `09,1,live,1`() {
            report {
                Day09.partOne(data09) to 251
            }
        }

        @Test
        fun `09,1,live,2`() {
            report {
                Day09.partOne(data09) to 251
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `09,2,test`() {
            report {
                Day09.partTwo(test09) to 982
            }
        }

        @Test
        fun `09,2,live,1`() {
            report {
                Day09.partTwo(data09) to 898
            }
        }

        @Test
        fun `09,2,live,2`() {
            report {
                Day09.partTwo(data09) to 898
            }
        }
    }
}
