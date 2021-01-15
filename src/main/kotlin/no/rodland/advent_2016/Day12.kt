package no.rodland.advent_2016

typealias Program = List<Pair<Day12.Instruction, List<String>>>

@Suppress("UNUSED_PARAMETER")
object Day12 {
    fun partOne(list: List<String>): Int {
        val program = parseProgram(list)
        return program.run(0, mutableMapOf("a" to 0, "b" to 0, "c" to 0, "d" to 0))["a"]!!
    }

    fun partTwo(list: List<String>): Int {
        val program = parseProgram(list)
        return program.run(0, mutableMapOf("a" to 0, "b" to 0, "c" to 1, "d" to 0))["a"]!!
    }

    val registers = setOf("a", "b", "c", "d")
    fun Program.run(initialA: Int, reg: MutableMap<String, Int>): MutableMap<String, Int> {
        var ip = 0
        while (ip < size) {
            val (instr, arg) = this[ip]
            when (instr) {
                Instruction.cpy -> reg[arg[1]] = getValue(reg, arg[0])
                Instruction.inc -> reg[arg[0]] = reg[arg[0]]!! + 1
                Instruction.dec -> reg[arg[0]] = reg[arg[0]]!! - 1
                Instruction.jnz -> ip += getValue(reg, arg[0]).let { if (it != 0) (arg[1].toInt() - 1) else 0 }
            }
            ip++
        }
        return reg
    }

    private fun getValue(reg: MutableMap<String, Int>, arg: String) = (if (arg in (reg.keys)) reg[arg] else arg.toInt())!!

    private fun parseProgram(list: List<String>): Program {
        return list
                .map { Instruction.valueOf(it.substringBefore(" ")) to it.substringAfter(" ") }
                .map {
                    it.first to it.second.split(" ")
                }
    }


    enum class Instruction {
        cpy, inc, dec, jnz
    }

}
