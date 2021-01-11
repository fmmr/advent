package no.rodland.advent_2015

typealias Program = List<Pair<Day23.Instruction, List<String>>>

object Day23 {
    fun partOne(list: List<String>, register: String): Int {
        val program = parseProgram(list)
        return program.run(0)[register]!!
    }

    fun partTwo(list: List<String>): Int {
        val program = parseProgram(list)
        return program.run(1)["b"]!!
    }

    fun Program.run(initialA: Int): MutableMap<String, Int> {
        val reg = mutableMapOf("a" to initialA, "b" to 0)
        var ip = 0
        while (ip < size) {
            val (instr, arg) = this[ip]
            when (instr) {
                Instruction.hlf -> reg[arg[0]] = reg[arg[0]]!!.div(2)
                Instruction.tpl -> reg[arg[0]] = reg[arg[0]]!! * 3
                Instruction.inc -> reg[arg[0]] = reg[arg[0]]!! + 1
                Instruction.jmp -> ip += arg[0].toInt() - 1
                Instruction.jie -> ip += if (reg[arg[0]]!! % 2 == 0) (arg[1].toInt() - 1) else 0
                Instruction.jio -> ip += if (reg[arg[0]]!! == 1) (arg[1].toInt() - 1) else 0
            }
            ip++
        }
        return reg
    }

    private fun parseProgram(list: List<String>): Program {
        return list
                .map { Instruction.valueOf(it.substringBefore(" ")) to it.substringAfter(" ") }
                .map {
                    it.first to it.second.split(", ")
                }
    }


    enum class Instruction {
        hlf, tpl, inc, jmp, jie, jio
    }

}
