package no.rodland.advent_2019

import kotlinx.coroutines.DelicateCoroutinesApi
import no.rodland.advent.DisableSlow
import no.rodland.advent.Slow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisableSlow
@DelicateCoroutinesApi
internal class Day19Test {
    val data19 = "2019/input_19.txt".readFirstLineStrings()
    val test19 = listOf("1", "2")

    @Nested
    inner class `Part 1` {
        @Test
        fun `19,1,live`() {
            report {
                Day19.partOne(data19) to 234L
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        @Slow(10000)
        fun `19,2,printMap`() {
            report {
                Day19.printMap(data19, 900, 1100, 900, 1100) to Unit
            }
        }

        @Test
        fun `19,2,live`() {
            report {
                Day19.partTwo(data19) to 9290812L
            }
        }
    }
}


