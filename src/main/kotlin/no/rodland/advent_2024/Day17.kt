package no.rodland.advent_2024

import no.rodland.advent.Day

// template generated: 17/12/2024
// Fredrik Rødland 2024

class Day17(val input: List<String>) : Day<String, Long, Day17.Computer> {
    private val comp = input.parse()

    override fun partOne(): String {
        return comp.run()
    }

    // solution more-or-less from: https://kotlinlang.slack.com/archives/C87V9MQFK/p1734411603798899
    override fun partTwo(): Long {
        return recurse(comp.opcodes)
    }

    private fun recurse(target: List<Int>): Long {
        var a = if (target.size == 1) 0 else recurse(target.drop(1)) shl 3
        while (comp.copy(a = a).run() != target.joinToString(",")) {
            a++
        }
        return a
    }

    fun run(a: Long): String = comp.copy(a = a).run()

    data class Computer(var a: Long, var b: Long, var c: Long, val opcodes: List<Int>) {
        private var ip = 0
        private val output = mutableListOf<Int>()

        fun output() = output.joinToString(",")
        fun output(operand: Int) = output.add(operand)

        private fun combo(i: Int): Long {
            return when (i) {
                in 0..3 -> i.toLong()
                4 -> a
                5 -> b
                6 -> c
                7 -> error("Reserved combo operand: $i")
                else -> error("Invalid combo operand: $i")
            }
        }

        fun run(): String {
            while (ip < opcodes.size) {
                val opcode = opcodes[ip]
                val operand = opcodes[ip + 1]
                when (opcode) {
                    0 -> a = a shr combo(operand).toInt()
                    1 -> b = b xor operand.toLong()
                    2 -> b = combo(operand) % 8
                    3 -> if (a != 0L) ip = operand - 2
                    4 -> b = b xor c
                    5 -> output((combo(operand) % 8).toInt())
                    6 -> b = a shr combo(operand).toInt()
                    7 -> c = a shr combo(operand).toInt()
                    else -> error("Invalid opcode: $opcode")
                }
                ip += 2
            }
            return output()
        }
    }

    override fun List<String>.parse(): Computer {
        val a = get(0).substringAfter("A: ").toLong()
        val b = get(1).substringAfter("B: ").toLong()
        val c = get(2).substringAfter("C: ").toLong()
        val op = get(4).substringAfter("Program: ").split(",").map { it.toInt() }
        return Computer(a, b, c, op)
    }

    override val day = "17".toInt()
}
