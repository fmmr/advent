package no.rodland.advent_2020

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import no.rodland.advent_2020.Day23.RingBuffer
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@Suppress("ClassName")
@DisableSlow
internal class Day23Test {
    val data23 = "394618527"
    val test23 = "389125467"

    @Nested
    inner class Init {
        @Test
        fun `23,1,live,init`() {
            report {
                Day23.partOne(data23) to "78569234"
            }
        }

        @Test
        fun `23,2,live,init`() {
            report {
                Day23.partTwo(data23, 1000000) to 2
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `23,1,test,10`() {
            report {
                Day23.partOne(test23, 10) to "92658374"
            }
        }

        @Test
        fun `23,1,test,100`() {
            report {
                Day23.partOne(test23) to "67384529"
            }
        }

        @Test
        fun `23,1,test,buffer_get`() {
            report {
                assertEquals(RingBuffer(listOf(1, 2, 3))[0], 1)
                assertEquals(RingBuffer(listOf(1, 2, 3))[1], 2)
                assertEquals(RingBuffer(listOf(1, 2, 3))[2], 3)
                assertEquals(RingBuffer(listOf(1, 2, 3))[3], 1)
                assertEquals(RingBuffer(listOf(1, 2, 3))[4], 2)
                assertEquals(RingBuffer(listOf(1, 2, 3))[5], 3)
                assertEquals(RingBuffer(listOf(1, 2, 3))[6], 1)
                assertEquals(RingBuffer(listOf(1, 2, 3))[7], 2)
                assertEquals(RingBuffer(listOf(1, 2, 3))[8], 3)
                1 to 1
            }
        }

        @Test
        fun `23,1,test,buffer_seq`() {
            report {
                val buffer = RingBuffer(listOf(1, 2, 3, 4, 5))
                val seq = buffer.sequence().take(8).toList()
                assertEquals(listOf(1, 2, 3, 4, 5, 1, 2, 3), seq)
                1 to 1
            }
        }

        @Test
        fun `23,1,test,buffer_seq_start`() {
            report {
                val buffer = RingBuffer(listOf(1, 2, 3, 4, 5))
                val seq = buffer.sequence(2).take(8).toList()
                assertEquals(listOf(3, 4, 5, 1, 2, 3, 4, 5), seq)
                1 to 1
            }
        }

        @Test
        fun `23,1,test,buffer_result`() {
            report {
                val buffer = RingBuffer(listOf(7, 8, 1, 3, 2, 4, 5))
                assertEquals("324578", buffer.result())
                1 to 1
            }
        }

        @Test
        fun `23,1,test,buffer_get3`() {
            report {
                val buffer = RingBuffer(listOf(7, 8, 1, 3, 2, 4, 5))
                val idx = buffer.indexOf(4)
                val three = buffer.get3After(idx)

                assertEquals(listOf(5, 7, 8), three)
                1 to 1
            }
        }

        @Test
        fun `23,1,test,buffer_indexof`() {
            report {
                val buffer = RingBuffer(listOf(1, 2, 3, 4, 5))
                assertEquals(buffer.indexOf(4), 3)
                1 to 1
            }
        }

        @Test
        fun `23,1,live,1`() {
            report {
                Day23.partOne(data23) to "78569234"
            }
        }

        @Test
        fun `23,1,live,2`() {
            report {
                Day23.partOne(data23) to "78569234"
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `23,2,test`() {
            report {
                Day23.partTwo(test23) to 2
            }
        }

        @Test
        fun `23,2,live,1`() {
            report {
                Day23.partTwo(data23) to 2
            }
        }

        @Test
        fun `23,2,live,2`() {
            report {
                Day23.partTwo(data23) to 2
            }
        }
    }
}
