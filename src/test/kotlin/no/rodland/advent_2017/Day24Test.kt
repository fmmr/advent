package no.rodland.advent_2017

import no.rodland.advent.DisableSlow
import no.rodland.advent.Slow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@Suppress("ClassName")
@DisableSlow
internal class Day24Test {
    val data24 = "2017/input_24.txt".readFile()
    val test24 = listOf(
            "0/2",
            "2/2",
            "2/3",
            "3/4",
            "3/5",
            "0/1",
            "10/1",
            "9/10",
    )


    @Nested
    inner class `Part 1` {
        @Test
        fun `24,1,test`() {
            report {
                Day24.partOne(test24) to 31
            }
        }

        @Test
        @Slow(1800)
        fun `24,1,live,1`() {
            report {
                Day24.partOne(data24) to 2
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        @Slow(1800)
        fun `24,2,test`() {
            report {
                Day24.partTwo(test24) to 1824
            }
        }
    }
}
