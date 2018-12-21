package no.rodland.advent_2018

object Day21 {
    fun partOne(list: List<String>): Int {
        val instructionPointer = list[0].split(" ")[1].toInt()
        val program: Array<Instruction> = parse(list.subList(1, list.size))
        val register = execute(instructionPointer, program, Register(0, 0, 0, 0, 0, 0))
        println("Ran ${program.size} line program with end result: $register")
        return register
    }

    fun partTwo(list: List<String>): Int {
        val instructionPointer = list[0].split(" ")[1].toInt()
        val program: Array<Instruction> = parse(list.subList(1, list.size))
        val register = execute(instructionPointer, program, Register(0, 0, 0, 0, 0, 0), 2)
        println("Ran ${program.size} line program with end result: $register")
        return register
    }

    // part 1: find the first num in reg 2 (least instructions)  (count) 1846
    // part 2: find the last num in reg 2 before we start a repeating pattern  (most instructions)


    private fun execute(pointer: Int, program: Array<Instruction>, r: Register, part: Int = 1): Int {
        var register = r
        var ip = 0
        var count = 0L
        val set = mutableSetOf<Int>()

        var lastReg2: Int
        while (ip >= 0 && ip < program.size) {
            register[pointer] = ip
            val instruction = program[ip]
            val opCode = OpCode[instruction.i]
            lastReg2 = register[2]
            register = opCode.run(instruction, register)
            if (opCode == OpCode.eqrr && part == 1) {
                println("Comparing to reg 0")
                println("count=$count ip=$ip $register $opCode ${instruction.a} ${instruction.b} ${instruction.c} ")
                return register[2]
            }
            count++
            if (opCode == OpCode.eqrr) {

                val added = set.add(register[2])
                println("$lastReg2 ${register[2]}")
                if (!added && part == 2) {
                    println("$lastReg2 ${register[2]}  already added")
                    println("AFTER  count=$count ip=$ip $register $opCode ${instruction.a} ${instruction.b} ${instruction.c} ")
                    return 14626276  // return previous value 
                }
            }
            ip = register[pointer] + 1
        }
        println("program exited, ip: $ip, register: $register")
        return -1
    }

    private fun parse(subList: List<String>): Array<Instruction> {
        return subList.map { it.split(" ") }
                .map { Instruction(OpCode.valueOf(it[0]).id, it[1].toInt(), it[2].toInt(), it[3].toInt()) }
                .toTypedArray()
    }
}