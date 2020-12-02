package no.rodland.advent_2020

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisableSlow
internal class Day02Test {
    val data01 = "2020/input_02.txt".readFile()
    val testData = listOf(
        "1-3 a: abcde",
        "1-3 b: cdefg",
        "2-9 c: ccccccccc"
    )

    @Nested
    inner class `Part 1` {
        @Test
        fun `02,1,test,1`() {
            report {
                Day02.partOne(testData) to 2
            }
        }

        @Test
        fun `02,1,live`() {
            report {
                Day02.partOne(data01) to 414
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `02,2,test,1`() {
            report {
                Day02.partTwo(testData) to 1
            }
        }

        @Test
        fun `02,2,live`() {
            report {
                Day02.partTwo(data01) to 413
            }
        }
    }
}
