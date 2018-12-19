package no.rodland.advent_2018

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import no.rodland.advent_2018.OpCode.*
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
                Day16.numberOfMatchingOpcodes(Instruction(9, 2, 1, 2), Register(3, 2, 1, 1), Register(3, 2, 2, 1)) to 3
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
                Day16.findOpCodes(samples16.windowed(4, 4)) to
                        listOf((mulr to 0),
                                (borr to 6),
                                (bori to 15),
                                (muli to 5),
                                (addi to 12),
                                (addr to 8),
                                (seti to 14),
                                (eqir to 10),
                                (eqrr to 3),
                                (gtri to 13),
                                (gtrr to 4),
                                (eqri to 1),
                                (gtir to 11),
                                (setr to 2),
                                (bani to 7),
                                (banr to 9)
                        )
            }
        }

        @Test
        fun `16,2,live`() {
            report {
                Day16.partTwo(program16) to Register(r0 = 557, r1 = 6, r2 = 0, r3 = 557)
            }
        }
    }
}


