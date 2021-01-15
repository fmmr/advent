package no.rodland.advent_2016

import no.rodland.advent.DisableSlow
import no.rodland.advent.Slow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@Suppress("ClassName")
@DisableSlow
internal class Day12Test {
    val data12 = "2016/input_12.txt".readFile()

    @Nested
    inner class Init {
        @Test
        fun `12,1,live,init`() {
            report {
                Day12.partOne(data12) to 318003
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `12,1,live,1`() {
            report {
                Day12.partOne(data12) to 318003
            }
        }

        @Test
        fun `12,1,live,2`() {
            report {
                Day12.partOne(data12) to 318003
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        @Slow(900)
        fun `12,2,live,1`() {
            report {
                Day12.partTwo(data12) to 9227657
            }
        }
    }
}
