package no.rodland.advent_2015

import no.rodland.advent.DisableSlow
import no.rodland.advent.Slow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@Suppress("ClassName")
@DisableSlow
internal class Day06Test {
    val data06 = "2015/input_06.txt".readFile()
    val test06 = listOf("1", "2")

    @Nested
    inner class `Part 1` {
        @Test
        fun `06,1,test,instr_1`() {
            report {
                Day06.Instruction.fromString("turn on 0,0 through 999,999") to Day06.Instruction(command = Day06.Cmd.ON, 0 to 0, 999 to 999)
            }
        }

        @Test
        fun `06,1,test,instr_2`() {
            report {
                Day06.Instruction.fromString("turn off 499,499 through 500,500") to Day06.Instruction(command = Day06.Cmd.OFF, 499 to 499, 500 to 500)
            }
        }

        @Test
        fun `06,1,test,instr_3`() {
            report {
                Day06.Instruction.fromString("toggle 0,0 through 999,0") to Day06.Instruction(command = Day06.Cmd.TOGGLE, 0 to 0, 999 to 0)
            }
        }

        @Test
        fun `06,1,test,1`() {
            report {
                Day06.partOne(listOf("turn on 0,0 through 999,999")) to 1000000
            }
        }

        @Test
        fun `06,1,test,2`() {
            report {
                Day06.partOne(listOf("toggle 0,0 through 999,0")) to 1000
            }
        }

        @Test
        fun `06,1,test,3`() {
            report {
                Day06.partOne(listOf("turn off 499,499 through 500,500")) to 0
            }
        }

        @Test
        @Slow(900)
        fun `06,1,live,1`() {
            report {
                Day06.partOne(data06) to 543903
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `06,2,test,1`() {
            report {
                Day06.partTwo(listOf("turn on 0,0 through 0,0")) to 1
            }
        }

        @Test
        fun `06,2,test,2`() {
            report {
                Day06.partTwo(listOf("toggle 0,0 through 999,999")) to 2000000
            }
        }

        @Test
        @Slow(800)
        fun `06,2,live,1`() {
            report {
                Day06.partTwo(data06) to 14687245
            }
        }
    }
}
