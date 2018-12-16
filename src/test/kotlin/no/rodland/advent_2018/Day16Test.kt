package no.rodland.advent_2018

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import no.rodland.advent_2018.Day16.Instruction
import no.rodland.advent_2018.Day16.OpCode.*
import no.rodland.advent_2018.Day16.Register
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@DisableSlow
internal class Day16Test {
    val samples16 = "2018/input_16_samples.txt".readFile()
    val program16 = "2018/input_16_program.txt".readFile()
    val test16 = listOf("1", "2")

    @Nested
    inner class OpCodes {
        @Test
        fun `16,1,addr`() {
            report {
                addr.run(Instruction(0, 0, 2, 3), Register(1, 2, 3, 4)) to Register(1, 2, 3, 4)
            }
        }

        @Test
        fun `16,1,addi`() {
            report {
                addi.run(Instruction(9, 2, 1, 2), Register(3, 2, 1, 1)) to Register(3, 2, 2, 1)
            }
        }

        @Test
        fun `16,1,mulr`() {
            report {
                mulr.run(Instruction(9, 2, 1, 2), Register(3, 2, 1, 1)) to Register(3, 2, 2, 1)
            }
        }

        @Test
        fun `16,1,seti`() {
            report {
                seti.run(Instruction(9, 2, 1, 2), Register(3, 2, 1, 1)) to Register(3, 2, 2, 1)
            }
        }

    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `16,1,parse`() {
            report {
                Day16.parse(
                        listOf("Before: [1, 2, 3, 2]",
                                "3 1 3 0",
                                "After:  [1, 2, 3, 2]",
                                "")) to (Instruction(3, 1, 3, 0) to (Register(1, 2, 3, 2) to Register(1, 2, 3, 2)))
            }
        }

        @Test
        fun `16,1,test`() {
            report {
                Day16.partOne(Instruction(9, 2, 1, 2), Register(3, 2, 1, 1), Register(3, 2, 2, 1)) to 3
            }
        }

        @Test
        fun `16,1,live`() {
            report {
                Day16.partOne(samples16.windowed(4, 4), 3) to 592
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `16,2,find_opcodes`() {
            report {
                Day16.findOpCodes(samples16.windowed(4, 4)) to 2
            }
        }

        @Test
        fun `16,2,live`() {
            report {
                Day16.findOpCodes(samples16.windowed(4, 4)) to 2
            }
        }
    }
}


