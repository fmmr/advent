package no.rodland.advent_2019

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisableSlow
internal class Day16Test {
    val data16 = "2019/input_16.txt".readFirstLineConvertToInts()
    val test16_1 = "12345678".map { it.toString().toInt() }
    val test16_2 = "80871224585914546619083218645595".map { it.toString().toInt() }
    val test16_3 = "19617804207202209144916044189917".map { it.toString().toInt() }
    val test16_4 = "69317163492948606335995924319873".map { it.toString().toInt() }


    @Nested
    inner class PatternTest {
        @Test
        fun `16,1,pattern,1`() {
            report {
                Day16.getPattern(1).take(10).toList() to listOf(1, 0, -1, 0, 1, 0, -1, 0, 1, 0)
            }
        }

        @Test
        fun `16,1,pattern,2`() {
            report {
                Day16.getPattern(2).take(15).toList() to listOf(0, 1, 1, 0, 0, -1, -1, 0, 0, 1, 1, 0, 0, -1, -1)
            }
        }

        @Test
        fun `16,1,pattern,8`() {
            report {
                Day16.getPattern(8).take(35).toList() to listOf(0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, -1, -1, -1, -1, -1, -1, -1, -1, 0, 0, 0, 0)
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `16,1,test,1_1`() {
            report {
                Day16.partOne(test16_1, 1) to "48226158"
            }
        }

        @Test
        fun `16,1,test,1_2`() {
            report {
                Day16.partOne(test16_1, 2) to "34040438"
            }
        }

        @Test
        fun `16,1,test,1_4`() {
            report {
                Day16.partOne(test16_1, 4) to "01029498"
            }
        }

        @Test
        fun `16,1,test,2`() {
            report {
                Day16.partOne(test16_2) to "24176176"
            }
        }

        @Test
        fun `16,1,test,3`() {
            report {
                Day16.partOne(test16_3) to "73745418"
            }
        }

        @Test
        fun `16,1,test,4`() {
            report {
                Day16.partOne(test16_4) to "52432133"
            }
        }

        @Test
        fun `16,1,live`() {
            report {
                Day16.partOne(data16) to "23135243"
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `16,2,test`() {
            report {
                Day16.partTwo(test16_2) to 2
            }
        }

        @Test
        fun `16,2,live`() {
            report {
                Day16.partTwo(data16) to 2
            }
        }
    }
}


