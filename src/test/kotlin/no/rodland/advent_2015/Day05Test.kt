package no.rodland.advent_2015

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@Suppress("ClassName")
@DisableSlow
internal class Day05Test {
    val data05 = "2015/input_05.txt".readFile()
    val test05 = listOf("1", "2")

    @Nested
    inner class Init {
        @Test
        fun `05,1,live,init`() {
            report {
                Day05.partOne(data05) to 255
            }
        }

        @Test
        fun `05,2,live,init`() {
            report {
                Day05.partTwo(data05) to 55
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `05,1,test,1`() {
            report {
                Day05.partOne(listOf("ugknbfddgicrmopn")) to 1
            }
        }

        @Test
        fun `05,1,test,2`() {
            report {
                Day05.partOne(listOf("aaa")) to 1
            }
        }

        @Test
        fun `05,1,test,3`() {
            report {
                Day05.partOne(listOf("jchzalrnumimnmhp")) to 0
            }
        }

        @Test
        fun `05,1,test,4`() {
            report {
                Day05.partOne(listOf("haegwjzuvuyypxyu")) to 0
            }
        }

        @Test
        fun `05,1,test,5`() {
            report {
                Day05.partOne(listOf("dvszwmarrgswjxmb")) to 0
            }
        }

        @Test
        fun `05,1,live,1`() {
            report {
                Day05.partOne(data05) to 255
            }
        }

        @Test
        fun `05,1,live,2`() {
            report {
                Day05.partOne(data05) to 255
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `05,2,test,1`() {
            report {
                Day05.partTwo(listOf("qjhvhtzxzqqjkmpb")) to 1
            }
        }

        @Test
        fun `05,2,test,2`() {
            report {
                Day05.partTwo(listOf("xxyxx")) to 1
            }
        }

        @Test
        fun `05,2,test,3`() {
            report {
                Day05.partTwo(listOf("uurcxstgmygtbstg")) to 0
            }
        }

        @Test
        fun `05,2,test,4`() {
            report {
                Day05.partTwo(listOf("ieodomkazucvgmuy")) to 0
            }
        }

        @Test
        fun `05,2,live,1`() {
            report {
                Day05.partTwo(data05) to 55
            }
        }

        @Test
        fun `05,2,live,2`() {
            report {
                Day05.partTwo(data05) to 55
            }
        }
    }
}
