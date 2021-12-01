package no.rodland.advent_2019

import no.rodland.advent.DisableSlow
import no.rodland.advent.Slow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisableSlow
internal class Day03Test {
    val wire1 = "2019/input_03.txt".readFile()[0].split(",")
    val wire2 = "2019/input_03.txt".readFile()[1].split(",")
    val test03 = listOf("1", "2")

    @Nested
    inner class `Part 1` {
        @Test
        fun `03,1,test`() {
            report {
                Day03.partOne(listOf("R8", "U5", "L5", "D3"), listOf("U7", "R6", "D4", "L4")) to 6
            }
        }

        @Test
        @Slow(117569)
        fun `03,1,live`() {
            report {
                Day03.partOne(wire1, wire2) to 266
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `03,2,test`() {
            report {
                Day03.partTwo(listOf("R8", "U5", "L5", "D3"), listOf("U7", "R6", "D4", "L4")) to 30
            }
        }

        @Test
        @Slow(119786)
        fun `03,2,live`() {
            report {
                Day03.partTwo(wire1, wire2) to 19242
            }
        }
    }
}


