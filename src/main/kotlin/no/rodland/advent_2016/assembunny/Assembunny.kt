package no.rodland.advent_2016.assembunny

typealias Program = MutableList<Pair<Instruction, List<String>>>

fun Program.runProgram(reg: MutableMap<String, Int>): MutableMap<String, Int> {
    var ip = 0
    while (ip < size) {
        val (instr, arg) = this[ip]
        // println("reg: $reg     $instr $arg ")
        when (instr) {
            Instruction.fmr -> {
                reg["a"] = reg["b"]!! * reg["d"]!!
                reg["c"] = 0
                reg["d"] = 0
            }
            Instruction.fmr2 -> {
                reg["c"] = reg["c"]!! * 2
                reg["d"] = 0
            }
            Instruction.cpy -> reg[arg[1]] = getValue(reg, arg[0])
            Instruction.inc -> reg[arg[0]] = reg[arg[0]]!! + 1
            Instruction.dec -> reg[arg[0]] = reg[arg[0]]!! - 1
            Instruction.jnz -> ip += getValue(reg, arg[0]).let { if (it != 0) (getValue(reg, arg[1]) - 1) else 0 }
            Instruction.tgl -> getValue(reg, arg[0]).let { value ->
                if ((ip + value) < size) {
                    this[ip + value] = this[ip + value].toggle()
                }
            }
        }
        ip++
    }
    return reg
}

private fun Pair<Instruction, List<String>>.toggle(): Pair<Instruction, List<String>> {
    val (instr, args) = this
    val newInstr = when (instr) {
        Instruction.inc -> Instruction.dec
        Instruction.jnz -> Instruction.cpy
        else -> if (args.size == 1) Instruction.inc else Instruction.jnz
    }
    return newInstr to args
}

private fun getValue(reg: MutableMap<String, Int>, arg: String) = (if (arg in (reg.keys)) reg[arg] else arg.toInt())!!

fun parseProgram(list: List<String>): Program {
    return list
            .map { Instruction.valueOf(it.substringBefore(" ")) to it.substringAfter(" ") }
            .map {
                it.first to it.second.split(" ")
            }.toMutableList()
}


enum class Instruction {
    cpy, inc, dec, jnz, tgl, fmr, fmr2
}

