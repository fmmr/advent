package no.rodland.advent_2016

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@Suppress("ClassName")
@DisableSlow
internal class Day20Test {
    val data20 = "2016/input_20.txt".readFile()

    @Nested
    inner class Init {
        @Test
        fun `20,1,live,init`() {
            report {
                Day20.partOne(data20, 4294967295L) to 17348574L
            }
        }

        @Test
        fun `20,2,live,test`() {
            report {
                Day20.partTwo(listOf("5-8", "0-1", "4-7"), 9) to 3 // 2,3,9
            }
        }

        @Test
        fun `20,2,live,init`() {
            report {
                Day20.partTwo(data20, 4294967295L) to 104
            }
        }
    }
}
