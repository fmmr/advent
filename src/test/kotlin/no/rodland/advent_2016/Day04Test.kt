package no.rodland.advent_2016

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@Suppress("ClassName")
@DisableSlow
internal class Day04Test {
    val data04 = "2016/input_04.txt".readFile()
    val test04 = listOf(
            "aaaaa-bbb-z-y-x-123[abxyz]",
            "a-b-c-d-e-f-g-h-987[abcde]",
            "not-a-real-room-404[oarel]",
            "totally-real-room-200[decoy]",
    )

    @Nested
    inner class Init {
        @Test
        fun `04,1,live,init`() {
            report {
                Day04.partOne(data04) to 409147
            }
        }

        @Test
        fun `04,2,live,init`() {
            report {
                Day04.partTwo(data04) to 991
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `04,1,test`() {
            report {
                Day04.partOne(test04) to 1514
            }
        }

        @Test
        fun `04,1,test,sector`() {
            report {
                Day04.Room("aaaaa-bbb-z-y-x-123[abxyz]").sector to 123
            }
        }

        @Test
        fun `04,1,test,checksum`() {
            report {
                Day04.Room("aaaaa-bbb-z-y-x-123[abxyz]").checkSum to "abxyz"
            }
        }

        @Test
        fun `04,1,test,name`() {
            report {
                Day04.Room("aaaaa-bbb-z-y-x-123[abxyz]").name to "aaaaa-bbb-z-y-x-"
            }
        }

        @Test
        fun `04,1,live,1`() {
            report {
                Day04.partOne(data04) to 409147
            }
        }

        @Test
        fun `04,1,live,2`() {
            report {
                Day04.partOne(data04) to 409147
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `04,2,test,decrypt`() {
            report {
                Day04.Room("qzmt-zixmtkozy-ivhz-343[aaaaa]").decrypt() to "very encrypted name "
            }
        }

        @Test
        fun `04,2,live,1`() {
            report {
                Day04.partTwo(data04) to 991
            }
        }

        @Test
        fun `04,2,live,2`() {
            report {
                Day04.partTwo(data04) to 991
            }
        }
    }
}
