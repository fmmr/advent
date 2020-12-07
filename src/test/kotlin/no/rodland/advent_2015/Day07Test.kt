package no.rodland.advent_2015

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@Suppress("ClassName")
@DisableSlow
internal class Day07Test {
    val data07 = "2015/input_07.txt".readFile()
    val test07 = listOf(
        "123 -> x",
        "456 -> y",
        "x AND y -> d",
        "x OR y -> e",
        "x LSHIFT 2 -> f",
        "y RSHIFT 2 -> g",
        "NOT x -> h",
        "NOT y -> i",
    )

    @Nested
    inner class Init {
        @Test
        fun `07,1,live,init`() {
            report {
                Day07.partOne(data07, "a") to 16076
            }
        }

        @Test
        fun `07,2,live,init`() {
            report {
                Day07.partTwo(data07) to 2797
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `07,1,test,g`() {
            report {
                Day07.partOne(test07, "g") to 114
            }
        }

        @Test
        fun `07,1,test,d`() {
            report {
                Day07.partOne(test07, "d") to 72
            }
        }

        @Test
        fun `07,1,test,e`() {
            report {
                Day07.partOne(test07, "e") to 507
            }
        }

        @Test
        fun `07,1,test,f`() {
            report {
                Day07.partOne(test07, "f") to 492
            }
        }

        @Test
        fun `07,1,test,h`() {
            report {
                Day07.partOne(test07, "h") to 65412
            }
        }

        @Test
        fun `07,1,test,i`() {
            report {
                Day07.partOne(test07, "i") to 65079
            }
        }

        @Test
        fun `07,1,test,x`() {
            report {

                Day07.partOne(test07, "x") to 123
            }
        }

        @Test
        fun `07,1,test,y`() {
            report {
                Day07.partOne(test07, "y") to 456
            }
        }

        @Test
        fun `07,1,live,1`() {
            report {
                Day07.partOne(data07, "a") to 16076
            }
        }

        @Test
        fun `07,1,live,2`() {
            report {
                Day07.partOne(data07, "a") to 16076
            }
        }
    }

    @Nested
    inner class `Part 2` {

        @Test
        fun `07,2,live,1`() {
            report {
                Day07.partTwo(data07) to 2797
            }
        }

        @Test
        fun `07,2,live,2`() {
            report {
                Day07.partTwo(data07) to 2797
            }
        }
    }
}
