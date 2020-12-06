package no.rodland.advent_2020

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@Suppress("ClassName")
@DisableSlow
internal class Day06Test {
    val data06 = "2020/input_06.txt".readFileAsOneString()
    val test06a = """
        abcx
        abcy
        abcz
        """.trimIndent()
    val test06b = """
        abc
        
        a
        b
        c
        
        ab
        ac
        
        a
        a
        a
        a
        
        b
        """.trimIndent()

    @Nested
    inner class `Part 1` {
        @Test
        fun `06,1,test,1`() {
            report {
                Day06.partOne(test06a) to 6
            }
        }

        @Test
        fun `06,1,test,2`() {
            report {
                Day06.partOne(test06b) to 11
            }
        }

        @Test
        fun `06,1,live`() {
            report {
                Day06.partOne(data06) to 7128
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `06,2,test`() {
            report {
                Day06.partTwo(test06b) to 6
            }
        }

        @Test
        fun `06,2,live`() {
            report {
                Day06.partTwo(data06) to 3640
            }
        }
    }
}
