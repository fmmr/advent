package no.rodland.advent_2015

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@Suppress("ClassName")
@DisableSlow
internal class Day03Test {
    val data03 = "2015/input_03.txt".readFileAsOneString()
    val test03 = listOf("1", "2")

    @Nested
    inner class Init {
        @Test
        fun `03,1,live,init`() {
            report {
                Day03.partOne(data03) to 2081
            }
        }

        @Test
        fun `03,2,live,init`() {
            report {
                Day03.partTwo(data03) to 2341
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `03,1,test,1`() {
            report {
                Day03.partOne("^>v<") to 4
            }
        }

        @Test
        fun `03,1,test,2`() {
            report {
                Day03.partOne(">") to 2
            }
        }

        @Test
        fun `03,1,test,3`() {
            report {
                Day03.partOne("^v^v^v^v^v") to 2
            }
        }

        @Test
        fun `03,1,live,1`() {
            report {
                Day03.partOne(data03) to 2081
            }
        }

        @Test
        fun `03,1,live,2`() {
            report {
                Day03.partOne(data03) to 2081
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `03,2,test,1`() {
            report {
                Day03.partTwo("^v") to 3
            }
        }

        @Test
        fun `03,2,test,2`() {
            report {
                Day03.partTwo("^>v<") to 3
            }
        }

        @Test
        fun `03,2,test,3`() {
            report {
                Day03.partTwo("^v^v^v^v^v") to 11
            }
        }

        @Test
        fun `03,2,live,1`() {
            report {
                Day03.partTwo(data03) to 2341
            }
        }

        @Test
        fun `03,2,live,2`() {
            report {
                Day03.partTwo(data03) to 2341
            }
        }
    }
}
