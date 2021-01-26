package no.rodland.advent_2017.prog

class Program(list: MutableList<Pair<Instruction, List<String>>>) : MutableList<Pair<Instruction, List<String>>> by list {

    fun compile(reg: MutableMap<String, Long>, debug: Boolean = false): Sequence<Long> {
        val prog = this
        val seq = sequence<Long> {
            var ip = 0
            var lastSound = 0L
            while (ip < size) {
                val (instr, arg) = prog[ip]
                // println("reg: $reg     $instr $arg ")
                when (instr) {
                    Instruction.snd -> lastSound = getValue(reg, arg[0])
                    Instruction.set -> reg[arg[0]] = getValue(reg, arg[1])
                    Instruction.add -> reg[arg[0]] = getValue(reg, arg[0]) + getValue(reg, arg[1])
                    Instruction.mul -> reg[arg[0]] = getValue(reg, arg[0]) * getValue(reg, arg[1])
                    Instruction.mod -> reg[arg[0]] = getValue(reg, arg[0]) % getValue(reg, arg[1])
                    Instruction.rcv -> if (getValue(reg, arg[0]) != 0L) {
                        yield(lastSound)
                    }
                    Instruction.jgz -> if (getValue(reg, arg[0]) > 0L) {
                        ip += getValue(reg, arg[1]).toInt() - 1
                    }
                }
                if (debug) {
                    println("ip: $ip, instr: $instr, args: $arg, reg: $reg")
                }
                ip++
            }
        }
        return seq
    }


    fun getValue(reg: MutableMap<String, Long>, arg: String) = (if (arg[0] in ('a'..'z')) (reg.getOrPut(arg, { 0 })) else arg.toLong())

    companion object {
        fun parse(list: List<String>): Program {
            return Program(
                    list
                            .map { Instruction.valueOf(it.substringBefore(" ")) to it.substringAfter(" ") }
                            .map {
                                it.first to it.second.split(" ")
                            }.toMutableList()
            )
        }
    }
}

enum class Instruction {
    snd, set, add, mul, mod, rcv, jgz
}

fun Sequence<Long>.runProgram(): List<Long> {
    return toList()
}
